select * from building limit 1;
select count(1) from building; #644256

# 1. 연립다세대 전월세 정보의 모든 건물 정보를 groupby 한다.
# 1-1. group by 할 컬럼이 null 인지 확인
select * from building where 시군구 is null;
select * from building where 건물명 is null;
select * from building where 번지 is null;
# 1-2. group by 시 총 컬럼의 수 : 160988
select 건물명, 건축년도, 도로명, 번지, 시군구, 전월세구분, `보증금(만원)`, `월세(만원)`, 계약년월, 계약일, `전용면적(㎡)`, 층
from building
group by 시군구, 건물명, 번지 
;
# 1-3. group by 한 정보를 view 로 만든다.
drop view if exists v_yeonlip_jws_groupby;
create view v_yeonlip_jws_groupby as
select 건물명, 건축년도, 도로명, 번지, 시군구, 전월세구분, `보증금(만원)`, `월세(만원)`, 계약년월, 계약일, `전용면적(㎡)`, 층
from building
group by 시군구, 건물명, 번지 
;


# 2. v_yeonlip_jws_groupby 의 시군구 컬럼과 v_dongcode 의 doc 컬럼을 일치 조건으로 조인을 하고 dongCode 를 
#    조회하는 view 를 만든다.0
create view v_yeonlip_jws_groupby_dongcode as
select 건물명, 건축년도, 도로명, 번지, 시군구, 전월세구분, `보증금(만원)`, `월세(만원)`, 계약년월, 계약일, `전용면적(㎡)`, 층, dongcode
from v_yeonlip_jws_groupby y
join v_dongcode d
on y.시군구 = d.doc ;
# dongcode가 null 이 있는지 확인한다.
select * from v_yeonlip_jws_groupby_dongcode where dongcode is null or dongcode = ''
;

# 3. yeonlip_info 테이블을 만든다.
CREATE TABLE `yeonlip_info` (
  `yeonlipCode` int NOT NULL AUTO_INCREMENT COMMENT '연립다세대코드',
  `yeonlipName` varchar(40) DEFAULT NULL COMMENT '연립다세대이름',
  `buildYear` varchar(4) DEFAULT NULL COMMENT '건축년도',
  `roadName` varchar(40) DEFAULT NULL COMMENT '도로명이름',
  `bunji` varchar(15) DEFAULT NULL COMMENT '번지',
  `sidoCode` varchar(2) DEFAULT NULL COMMENT '시도코드',
  `gugunCode` varchar(5) DEFAULT NULL COMMENT '구군코드',
  `dongCode` varchar(10) DEFAULT NULL COMMENT '동코드',
  PRIMARY KEY (`yeonlipCode`)
) ENGINE=InnoDB AUTO_INCREMENT=39439 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='연립다세대건물정보(매매+전월세)'
;

# 4. v_yeonlip_jws_groupby_dongcode 를 통해 yeonlip_info 에 데이터를 넣는다.
insert into yeonlip_info(yeonlipName, buildYear, roadName, bunji, sidoCode, gugunCode, dongCode)
select 건물명, 건축년도, 도로명, 번지, left(dongcode, 2), left(dongcode, 5), dongcode
from v_yeonlip_jws_groupby_dongcode 
;

# 5. 데이터들이 잘 들어갔는지 확인한다.
# 5-1. v_yeonlip_jws_groupby_dongcode 의 row 수가 yeonlip_info 와 같는가
select count(1) from v_yeonlip_jws_groupby_dongcode; # 160988
select count(1) from yeonlip_info; # 160988
# 5-2. yeonlip_info에 dongcode 가 null인가 
select * from yeonlip_info where dongcode is null or dongcode = '';
# 5-3. 데이터 샘플 확인
select * from yeonlip_info limit 1;












