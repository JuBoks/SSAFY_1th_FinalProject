package com.ssafy.aptinfo.model.dao;

import java.util.List;

import com.ssafy.aptinfo.model.dto.AptInfoDto;

public interface AptInfoDao {

	List<AptInfoDto> listAptInfo(String dongCode);
	
	AptInfoDto search(int aptCode);
	
	int insert(AptInfoDto aptInfoDto);
	
	int update(AptInfoDto aptInfoDto);
	
	int delete(int aptCode);
}
