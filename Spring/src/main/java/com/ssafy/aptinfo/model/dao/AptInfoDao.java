package com.ssafy.aptinfo.model.dao;

import java.util.List;

import com.ssafy.aptinfo.model.dto.AptInfoDto;

public interface AptInfoDao {

	List<AptInfoDto> listAptInfo(String dongCode);
	
	int insert(AptInfoDto houseInfoDto);
	
	int update(AptInfoDto houseInfoDto);
	
	int delete(Long aptCode);
}
