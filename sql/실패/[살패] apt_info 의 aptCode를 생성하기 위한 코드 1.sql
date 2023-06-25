# 프로시저 생성
delimiter $$
DROP PROCEDURE IF EXISTS housedata2.`PROC_INSERT_APT_INFO`$$ 
CREATE PROCEDURE housedata2.`PROC_INSERT_APT_INFO` (
    p_apartmentName VARCHAR(40),
    p_roadName VARCHAR(40),
    p_gugunCode VARCHAR(5),
    p_dongCode VARCHAR(10)
)
BEGIN
	# 1. aptcode_tmp 에서 조회한 no 의 +1 을하여 aptCode 로 넣음
    INSERT apt_info_tmp2(aptCode, apartmentName, buildYear, roadName, bunji, sidoCode, gugunCode, dongCode)
	SELECT (SELECT no+1 FROM aptcode_tmp WHERE gugunCode = p_gugunCode) aptCode,
		apartmentName, buildYear, roadName, bunji, sidoCode, gugunCode, dongCode
	FROM apt_info_tmp a
    WHERE gugunCode = p_gugunCode
    and dongCOde = p_dongCode 
    and roadName = p_roadName
    and apartmentName = p_apartmentName;
    
    # 2. aptCode 업데이트
    UPDATE aptcode_tmp
    SET no = no+1
    WHERE gugunCode = p_gugunCode;
    
END $$ 
DELIMITER ;