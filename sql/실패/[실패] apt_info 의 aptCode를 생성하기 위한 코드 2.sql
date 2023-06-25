# 프로시저 생성
delimiter $$
DROP PROCEDURE IF EXISTS housedata2.`PROC_WHILE_APT_INFO`$$ 
CREATE PROCEDURE housedata2.`PROC_WHILE_APT_INFO` (
)
BEGIN
	DECLARE v_id INT; # 인덱스 접근
    DECLARE v_length INT; # 총 row의 개수
	DECLARE v_apartmentName VARCHAR(40);
    DECLARE v_roadName VARCHAR(40);
    DECLARE v_gugunCode VARCHAR(5);
    DECLARE v_dongCode VARCHAR(10);
    
    SET v_id := 1;
    SET v_length := (SELECT count(1) FROM apt_info_tmp);
		
    WHILE v_id <= v_length DO
		# v_id 의 정보를 가져와서 변수에 저장
		SELECT apartmentName, roadName, gugunCode, dongCode
        INTO v_apartmentName, v_roadName, v_gugunCode, v_dongCode
		FROM apt_info_tmp a
		WHERE no = v_id;
        
        # 해당 변수로 프로시저 호출
        CALL PROC_INSERT_APT_INFO(v_apartmentName, v_roadName, v_gugunCode, v_dongCode);
        
        # v_id 증가
        SET v_id = v_id + 1;
    END WHILE;
END $$ 
DELIMITER ;