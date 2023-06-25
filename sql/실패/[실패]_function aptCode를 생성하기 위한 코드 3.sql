# 함수 생성시 다음 쿼리를 실행해야 함수 생성가능 (1회만)
SET GLOBAL log_bin_trust_function_creators = 1;

# 같은 구군의 아파트 개수들을 가져와서 총개수 + 1 한 값이 key 값이 되도록 하는 함수 (max 대신)
# 하지만 시간이 많이 걸림
# aptCode 만드는 함수 생성 (따로 sql 페이지 만들어서 단독으로 실행해야 함)
delimiter $$
DROP FUNCTION IF EXISTS housedata2.`GET_APT_CODE`$$ 
CREATE FUNCTION housedata2.`GET_APT_CODE`(
	var_gugunCode VARCHAR(10)
) RETURNS int
BEGIN
	DECLARE aptCode VARCHAR(14);
    DECLARE cnt int;
    
    # 같은 동에 있는 아파트들 개수 리턴
    SELECT count(*)
	FROM apt_info_tmp2
    GROUP BY gugunCode
    HAVING gugunCode = var_gugunCode
		INTO cnt;
    
    # 아파트들 개수 + 1
    IF(cnt is null) THEN
		SET cnt = 1;
	ELSE
		SET cnt = cnt + 1;
	END IF;
    
    RETURN cnt;
END $$ 
DELIMITER ;