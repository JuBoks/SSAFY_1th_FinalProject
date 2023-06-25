# 1. yeonlip_deal 테이블 생성
drop table if exists yeonlip_deal
;
CREATE TABLE `housedata2`.`yeonlip_deal` (
  `no` BIGINT NOT NULL AUTO_INCREMENT COMMENT '거래코드',
  `dealAmount` VARCHAR(20) NULL COMMENT '거래금액',
  `dealYear` INT NULL COMMENT '거래년도',
  `dealMonth` INT NULL COMMENT '거래월',
  `dealDay` INT NULL COMMENT '거래일',
  `area` VARCHAR(20) NULL COMMENT '전용면적(㎡)',
  `landArea` VARCHAR(20) NULL COMMENT '대지권면적(㎡)',
  `floor` VARCHAR(4) NULL COMMENT '층',
  `cancelDealDay` VARCHAR(8) NULL COMMENT '해제사유발생일',
  `yeonlipCode` INT NOT NULL COMMENT '연립다세대코드',
  PRIMARY KEY (`no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = '연립다세대 매매 내역'
;

select * from test limit 1;

# 2. 모든 test row 에 대해 dongcode 를 가진 view를 만든다.
drop view if exists v_test_dongcode
;
create view v_test_dongcode as 
select 건물명, 건축년도, 도로명, 번지, `거래금액(만원)`, 계약년월, 계약일, `전용면적(㎡)`, `대지권면적(㎡)`, 층, 해제사유발생일, dongcode
from test t
join v_dongcode d
on t.시군구 = d.doc ;

# 2-1. row 개수 확인
select count(*) from test ; # 492537
select count(*) from v_test_dongcode ; # 492537

# 3. v_test_dongcode 와 yeonlip_info 를 조인한 view 테이블을 만든다.
drop view if exists v_test_dongcode_join_yeonlip
;
create view v_test_dongcode_join_yeonlip as
select
	건물명,
    건축년도,
    도로명,
    번지,
	`거래금액(만원)`, 
	cast(left(`계약년월`,4) as unsigned), 
	cast(right(`계약년월`,2) as unsigned),  
	cast(`계약일` as unsigned), 
	`전용면적(㎡)`, 
	`대지권면적(㎡)`, 
	층, 
	해제사유발생일,
    v.dongcode,
	yeonlipCode
from v_test_dongcode v
left outer join yeonlip_info y
on v.dongcode = y.dongCode
and v.건물명 = y.yeonlipName
and v.번지 = y.bunji
;
# 3-1. 데이터 확인
select count(*) from v_test_dongcode_join_yeonlip ; # 492537

# 4. v_test_dongcode_join_yeonlip 에서 yeonlipCode 가 null 이거나 '' 인 데이터들을 모아
#    dongcode, 건물명, 번지로 groupby 를 한 view를 만든다.
drop view if exists v_test_dongcode_join_yeonlip_null
;
create view v_test_dongcode_join_yeonlip_null as
select *
from v_test_dongcode_join_yeonlip
where yeonlipCode is null or yeonlipCode = ''
group by dongcode, 건물명, 번지
;

# 4-1. 데이터 확인
select count(*) from v_test_dongcode_join_yeonlip_null ; # 53396
select * from v_test_dongcode_join_yeonlip_null limit 10;

# 5. v_test_dongcode_join_yeonlip_null 아파트 정보들을 yeonlip_info 에 넣는다.
insert into yeonlip_info(yeonlipName, buildYear, roadName, bunji, sidoCode, gugunCode, dongCode)
select 건물명, 건축년도, 도로명, 번지, left(dongcode, 2), left(dongcode, 5), dongcode
from v_test_dongcode_join_yeonlip_null
;

# 6. v_test_dongcode 과 yeonlip_info 를 dongcode, 건물명, 번지로 join 해서 yeonlipCode를 가져온다.
insert into yeonlip_deal(dealAmount, dealYear, dealMonth, dealDay, area, landArea, floor, cancelDealDay, yeonlipCode)
select 
	`거래금액(만원)`, 
	cast(left(`계약년월`,4) as unsigned), 
	cast(right(`계약년월`,2) as unsigned),  
	cast(`계약일` as unsigned), 
	`전용면적(㎡)`, 
	`대지권면적(㎡)`, 
	층, 
	해제사유발생일, 
	yeonlipCode
from v_test_dongcode v
join yeonlip_info y
on v.dongcode = y.dongCode
and v.건물명 = y.yeonlipName
and v.번지 = y.bunji
;

# 4. yeonlip_deal 의 데이터가 잘 들어갔는지 확인한다.
# 4-1. yeonlip_deal 의 데이터의 개수와 test 의 데이터의 개수가 맞는가?
select count(1) from test ; # 492537
select count(1) from yeonlip_deal ; # 492537
# 4-2. yeonlip_rent 의 yeonlipCode 가 null 이거나 '' 인 경우가 없는가?
select * from yeonlip_rent where yeonlipCode is null or yeonlipCode = '';



