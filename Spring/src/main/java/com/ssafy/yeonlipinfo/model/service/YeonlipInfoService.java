package com.ssafy.yeonlipinfo.model.service;

import java.util.List;

import com.ssafy.yeonlipinfo.model.dto.YeonlipInfoDto;

public interface YeonlipInfoService {
	
	List<YeonlipInfoDto> listYeonlipInfo(String dongCode) throws Exception;
	
	YeonlipInfoDto search(Integer yeonlipCode) throws Exception;
	
	boolean insert(YeonlipInfoDto yeonlipInfoDto) throws Exception;
	
	boolean update(YeonlipInfoDto yeonlipInfoDto) throws Exception;
	
	boolean delete(Integer yeonlipCode) throws Exception;
	
}
