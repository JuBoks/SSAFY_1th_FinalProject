select count(1) from apt_jeonwolse_dump; # 2641747
select * from apt_jeonwolse_dump where `월세(만원)`='84.84' limit 1;
select cast(replace("22,000",',','') as unsigned) from dual;

# apt_rent 테이블 존재하면 드랍
DROP TABLE IF EXISTS `apt_rent`;
# apt_rent 테이블 생성
CREATE TABLE `apt_rent` (
  `no` BIGINT NOT NULL AUTO_INCREMENT COMMENT '거래코드',
  `type` varchar(6) DEFAULT NULL COMMENT '전월세구분',
  `deposit` int DEFAULT NULL COMMENT '보증금(만원)',
  `monthlyRent` varchar(10) DEFAULT NULL COMMENT '월세(만원)',
  `dealYear` int DEFAULT NULL COMMENT '계약년도',
  `dealMonth` int DEFAULT NULL COMMENT '계약월',
  `dealDay` int DEFAULT NULL COMMENT '계약일',
  `area` varchar(20) DEFAULT NULL COMMENT '면적',
  `floor` varchar(4) DEFAULT NULL COMMENT '층',
  `aptCode` int COMMENT '아파트코드',
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=2686936 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='아파트 전월세 내역';

# 동코드, 도로명, 단지명 으로 apt_info 와 join
insert into apt_rent(type, deposit, monthlyRent, dealYear, dealMonth, dealDay, area, floor, aptCode)
select 
	d.`전월세구분`, 
    cast(replace(d.`보증금(만원)`,',','') as unsigned), 
    d.`월세(만원)`, 
    cast(left(d.`계약년월`,4) as unsigned), 
    cast(right(d.`계약년월`,2) as unsigned), 
    d.`계약일`, 
    d.`전용면적(㎡)`, 
    d.`층`,
    i.aptCode
from v_apt_jeonwolse_dump_dongcode d
left outer join apt_info i
on d.dongCode = i.dongCode
and d.`번지` = i.bunji
and d.`단지명` = i.apartmentName;

# 내용 확인
select * from apt_rent where aptCode is null;

