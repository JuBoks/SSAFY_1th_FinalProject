package com.ssafy.yeonlipinfo.model.dao;

import java.util.List;

import com.ssafy.yeonlipinfo.model.dto.YeonlipInfoDto;

public interface YeonlipInfoDao {
	
	List<YeonlipInfoDto> listYeonlipInfo(String dongCode);
	
	YeonlipInfoDto search(Integer yeonlipCode);
	
	int insert(YeonlipInfoDto yeonlipInfoDto);
	
	int update(YeonlipInfoDto yeonlipInfoDto);
	
	int delete(Integer yeonlipCode);
	
}
