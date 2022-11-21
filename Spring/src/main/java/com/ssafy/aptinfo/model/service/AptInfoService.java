package com.ssafy.aptinfo.model.service;

import java.util.List;

import com.ssafy.aptinfo.model.dto.AptInfoDto;

public interface AptInfoService {

	List<AptInfoDto> getAptByDong(String dongCode) throws Exception;
	
	AptInfoDto search(int aptCode) throws Exception;
	
	boolean insert(AptInfoDto aptInfoDto) throws Exception;
	
	boolean update(AptInfoDto aptInfoDto) throws Exception;
	
	boolean delete(int aptCode) throws Exception;

}
