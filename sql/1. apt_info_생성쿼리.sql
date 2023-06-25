
# GROUP BY 시 NULL 이 되는 컬럼을 주의하기!!!!!!!!!!!!!!
# GROUP BY 시 NULL 이 되는 컬럼을 주의하기!!!!!!!!!!!!!!
# GROUP BY 시 NULL 이 되는 컬럼을 주의하기!!!!!!!!!!!!!!

# 동코드 뷰 생성 v_dongcode
create view v_dongcode as
select dongCode, trim(concat(e.sidoName, ' ', e.gugunName, ' ', e.dongName)) `doc`
from (
	select dongCode, sidoName, gugunName, dongName
	from dongcode
	union all
	select dongCode, sidoName, concat(left(substring_index(gugunName, ' ', 1), 2), substring_index(substring_index(gugunName, ' ', 2), ' ', -1)) gugunName, dongName
	from dongcode
	union all
	select dongCode, '' as sidoName, gugunName, dongName
	from dongcode
    union all
    select dongCode, sidoName, concat(left(substring_index(gugunName, ' ', 1), 2), substring_index(substring_index(gugunName, ' ', 2), ' ', -1)) gugunName, concat(dongName, '동')
	from dongcode
    where sidoName = '충청북도'
    and gugunName = '청주시 상당구'
    and dongName like '북문로%' or dongName like '남문로%'
) e;

drop view if exists v_apt_jeonwolse_dump_dongcode;
# apt_jeonwolse_dump 에서 dongCode 컬럼을 생성 후, v_dongcode 의 정보와 모두 맵핑하기
create view v_apt_jeonwolse_dump_dongcode as
select a.`단지명`, a.`건축년도`, a.`도로명`, a.`번지`, d.dongCode, a.`전월세구분`, a.`보증금(만원)`, a.`월세(만원)`, a.`계약년월`, a.`계약일`, a.`전용면적(㎡)`, a.`층`
from apt_jeonwolse_dump a
left outer join v_dongcode d
on a.`시군구` = d.doc;

select * from v_apt_jeonwolse_dump_dongcode;

drop view if exists v_apt_jeonwolse_groupby;
# 아파트 전원세 내역에 있는 모든 아파트들 종류와 데이터 추출하여 뷰 생성 v_apt_info_jeonwolse
create view v_apt_jeonwolse_groupby
as
select *
from v_apt_jeonwolse_dump_dongcode
group by dongCode, `단지명`, `번지`, dongCode;

# 뷰 테스트.
select count(*) from v_apt_jeonwolse_groupby;
select * from v_apt_jeonwolse_groupby limit 1; 

DROP TABLE IF EXISTS `housedata2`.`apt_info`;
# apt_info 생성
CREATE TABLE `housedata2`.`apt_info` (
  `aptCode` INT AUTO_INCREMENT COMMENT '아파트코드',
  `apartmentName` VARCHAR(40) NULL COMMENT '아파트이름',
  `buildYear` VARCHAR(4) NULL COMMENT '건축년도',
  `roadName` VARCHAR(40) NULL COMMENT '도로명이름',
  `bunji` VARCHAR(15) NULL COMMENT '번지',
  `sidoCode` VARCHAR(2) NULL COMMENT '시도코드',
  `gugunCode` VARCHAR(5) NULL COMMENT '구군코드',
  `dongCode` VARCHAR(10) NULL COMMENT '동코드',
  PRIMARY KEY(aptCode)
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = '아파트건물정보(매매+전월세)';

# 임시 테이블에 CSV의 모든 내용을 저장
insert into apt_info(apartmentName, buildYear, roadName, bunji, sidoCode, gugunCode, dongCode)
select 
	a.`단지명` `apartmentName`, 
    a.`건축년도` `buildYear`, 
    a.`도로명` `roadName`, 
    a.`번지` `bunji`,
    left(a.dongCode, 2) `sidoCode`,
    left(a.dongCode, 5) `gugunCode`,
    a.dongCode `dongCode`
from v_apt_jeonwolse_groupby a;

# 임시 내용 확인
select * from apt_info limit 10;

