package com.ssafy.aptinfo.model.service;

import java.util.List;

import com.ssafy.aptinfo.model.dto.AptInfoDto;


public interface AptInfoService {

	List<AptInfoDto> getAptByDong(String dongCode);

}
