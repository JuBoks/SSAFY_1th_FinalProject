# 1. yeonlip_rent 테이블 생성
drop table if exists yeonlip_rent ;
CREATE TABLE `yeonlip_rent` (
  `no` bigint NOT NULL AUTO_INCREMENT COMMENT '거래코드',
  `type` varchar(6) DEFAULT NULL COMMENT '전월세구분',
  `deposit` int DEFAULT NULL COMMENT '보증금(만원)',
  `monthlyRent` varchar(10) DEFAULT NULL COMMENT '월세(만원)',
  `dealYear` int DEFAULT NULL COMMENT '계약년도',
  `dealMonth` int DEFAULT NULL COMMENT '계약월',
  `dealDay` int DEFAULT NULL COMMENT '계약일',
  `area` varchar(20) DEFAULT NULL COMMENT '면적',
  `floor` varchar(4) DEFAULT NULL COMMENT '층',
  `yeonlipCode` int DEFAULT NULL COMMENT '연립다세대코드',
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=5328683 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='연립다세대 전월세 내역'
;

# 2. 모든 building row 에 대해 dongcode 를 가진 view를 만든다.
create view v_building_dongcode as 
select 건물명, 건축년도, 도로명, 번지, 시군구, 전월세구분, `보증금(만원)`, `월세(만원)`, 계약년월, 계약일, `전용면적(㎡)`, 층, dongcode
from building b
join v_dongcode d
on b.시군구 = d.doc ;

# 2-1. row 개수 확인
select count(*) from building ; # 644256
select count(*) from v_building_dongcode ; # 644256

# 3. v_building_dongcode 과 yeonlip_info 를 dongcode, 건물명, 번지로 join 해서 yeonlipCode를 가져온다.
insert into yeonlip_rent(type, deposit, monthlyRent, dealYear, dealMonth, dealDay, area, floor, yeonlipCode)
select 
	전월세구분, 
    cast(replace(`보증금(만원)`,',','') as unsigned), 
    `월세(만원)`, 
    cast(left(`계약년월`,4) as unsigned), 
    cast(right(`계약년월`,2) as unsigned),  
    계약일, 
    `전용면적(㎡)`, 
    층, 
    yeonlipCode
from v_building_dongcode v
join yeonlip_info y
on v.dongcode = y.dongCode
and v.건물명 = y.yeonlipName
and v.번지 = y.bunji
;

# 4. yeonlip_rent 의 데이터가 잘 들어갔는지 확인한다.
# 4-1. yeonlip_rent 의 데이터의 개수와 building 의 데이터의 개수가 맞는가?
select count(1) from building ; # 644256
select count(1) from yeonlip_rent ; # 644256
# 4-2. yeonlip_rent 의 yeonlipCode 가 null 이거나 '' 인 경우가 없는가?
select * from yeonlip_rent where yeonlipCode is null or yeonlipCode = '';







