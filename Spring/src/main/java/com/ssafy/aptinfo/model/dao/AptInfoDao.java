package com.ssafy.aptinfo.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.aptinfo.model.dto.AptInfoDto;

public interface AptInfoDao {

	List<AptInfoDto> listAptInfo(String dongCode) throws SQLException;
	
	AptInfoDto search(int aptCode) throws SQLException;
	
	int insert(AptInfoDto aptInfoDto) throws SQLException;
	
	int update(AptInfoDto aptInfoDto) throws SQLException;
	
	int delete(int aptCode) throws SQLException;
}
