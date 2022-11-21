package com.ssafy.yeonlipinfo.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.yeonlipinfo.model.dto.YeonlipInfoDto;

public interface YeonlipInfoDao {
	
	List<YeonlipInfoDto> listYeonlipInfo(String dongCode) throws SQLException;
	
	YeonlipInfoDto search(Integer yeonlipCode) throws SQLException;
	
	int insert(YeonlipInfoDto yeonlipInfoDto) throws SQLException;
	
	int update(YeonlipInfoDto yeonlipInfoDto) throws SQLException;
	
	int delete(Integer yeonlipCode) throws SQLException;
	
}
