package com.ssafy.aptinfo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.aptinfo.model.dao.AptInfoDao;
import com.ssafy.aptinfo.model.dto.AptInfoDto;

@Service
public class AptInfoServiceImpl implements AptInfoService{

	
	@Autowired
	private AptInfoDao aptInfoDao;
	
	@Override
	public List<AptInfoDto> getAptByDong(String dongCode) {
		return aptInfoDao.listAptInfo(dongCode);
	}
	
	
}
