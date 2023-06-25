# 1. apt_deal 테이블 생성
drop table if exists apt_deal
;
CREATE TABLE `housedata2`.`apt_deal` (
  `no` BIGINT NOT NULL AUTO_INCREMENT COMMENT '거래코드',
  `dealAmount` VARCHAR(20) NULL COMMENT '거래금액',
  `dealYear` INT NULL COMMENT '거래년도',
  `dealMonth` INT NULL COMMENT '거래월',
  `dealDay` INT NULL COMMENT '거래일',
  `area` VARCHAR(20) NULL COMMENT '전용면적(㎡)',
  `floor` VARCHAR(4) NULL COMMENT '층',
  `cancelDealDay` VARCHAR(8) NULL COMMENT '해제사유발생일',
  `aptCode` INT NOT NULL COMMENT '아파트코드',
  PRIMARY KEY (`no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = '아파트 매매 내역'
;

select * from apt_deal_dump limit 1;

# 2. 모든 apt_deal_dump row 에 대해 dongcode 를 가진 view를 만든다.
drop view if exists v_apt_deal_dongcode
;
create view v_apt_deal_dongcode as 
select 단지명, 건축년도, 도로명, 번지, `거래금액(만원)`, 계약년월, 계약일, `전용면적(㎡)`, 층, 해제사유발생일, dongcode
from apt_deal_dump a
join v_dongcode d
on a.시군구 = d.doc ;

# 2-1. row 개수 확인
select count(*) from apt_deal_dump ; # 1670562
select count(*) from v_apt_deal_dongcode ; # 1670562

# 3. v_apt_deal_dongcode 와 apt_info 를 조인한 view 테이블을 만든다.
drop view if exists v_apt_deal_dongcode_join_apt
;
create view v_apt_deal_dongcode_join_apt as
select
	단지명,
    건축년도,
    도로명,
    번지,
	`거래금액(만원)`, 
	cast(left(`계약년월`,4) as unsigned), 
	cast(right(`계약년월`,2) as unsigned),  
	cast(`계약일` as unsigned), 
	`전용면적(㎡)`, 
	층, 
	해제사유발생일,
    v.dongcode,
	aptCode
from v_apt_deal_dongcode v
left outer join apt_info a
on v.dongcode = a.dongCode
and v.단지명 = a.apartmentName
and v.번지 = a.bunji
;
# 3-1. 데이터 확인
select count(*) from v_apt_deal_dongcode_join_apt ; # 1670562

# 4. v_apt_deal_dongcode_join_apt 에서 aptCode 가 null 이거나 '' 인 데이터들을 모아
#    dongcode, 건물명, 번지로 groupby 를 한 view를 만든다.
drop view if exists v_apt_deal_dongcode_join_apt_null
;
create view v_apt_deal_dongcode_join_apt_null as
select *
from v_apt_deal_dongcode_join_apt
where aptCode is null or aptCode = ''
group by dongcode, 단지명, 번지
;

# 4-1. 데이터 확인
select count(*) from v_apt_deal_dongcode_join_apt_null ; # 4448
select * from v_apt_deal_dongcode_join_apt_null limit 10;

# 5. v_apt_deal_dongcode_join_apt_null 아파트 정보들을 apt_info 에 넣는다.
insert into apt_info(apartmentName, buildYear, roadName, bunji, sidoCode, gugunCode, dongCode)
select 단지명, 건축년도, 도로명, 번지, left(dongcode, 2), left(dongcode, 5), dongcode
from v_apt_deal_dongcode_join_apt_null
;

# 6. v_apt_deal_dongcode 과 apt_info 를 dongcode, 단지명, 번지로 join 해서 aptCode를 가져온다.
insert into apt_deal(dealAmount, dealYear, dealMonth, dealDay, area, floor, cancelDealDay, aptCode)
select 
	`거래금액(만원)`, 
	cast(left(`계약년월`,4) as unsigned), 
	cast(right(`계약년월`,2) as unsigned),  
	cast(`계약일` as unsigned), 
	`전용면적(㎡)`, 
	층, 
	해제사유발생일, 
	aptCode
from v_apt_deal_dongcode v
join apt_info a
on v.dongcode = a.dongCode
and v.단지명 = a.apartmentName
and v.번지 = a.bunji
;

# 4. apt_deal 의 데이터가 잘 들어갔는지 확인한다.
# 4-1. apt_deal 의 데이터의 개수와 apt_deal_dump 의 데이터의 개수가 맞는가?
select count(1) from apt_deal_dump ; # 1670562
select count(1) from apt_deal ; # 1670562
# 4-2. apt_deal 의 aptCode 가 null 이거나 '' 인 경우가 없는가?
select * from apt_deal where aptCode is null or aptCode = '';
