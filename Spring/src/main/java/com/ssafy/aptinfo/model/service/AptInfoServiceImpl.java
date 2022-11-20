package com.ssafy.aptinfo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.aptinfo.model.dao.AptInfoDao;
import com.ssafy.aptinfo.model.dto.AptInfoDto;

@Service
public class AptInfoServiceImpl implements AptInfoService {

	@Autowired
	private AptInfoDao aptInfoDao;
	
	@Override
	public List<AptInfoDto> getAptByDong(String dongCode) {
		return aptInfoDao.listAptInfo(dongCode);
	}
	
	@Override
	public AptInfoDto search(int aptCode) {
		return aptInfoDao.search(aptCode);
	}

	@Override
	public boolean insert(AptInfoDto aptInfoDto) {
		// sidoCode, gugunCode 설정하기
		String dongCode = aptInfoDto.getDongCode();
		if(dongCode != null) {
			aptInfoDto.setSidoCode(dongCode.substring(0, 2));
			aptInfoDto.setGugunCode(dongCode.substring(0, 5));
		}
		return aptInfoDao.insert(aptInfoDto) == 1;
	}

	@Override
	public boolean update(AptInfoDto aptInfoDto) {
		// sidoCode, gugunCode 설정하기
		String dongCode = aptInfoDto.getDongCode();
		if(dongCode != null) {
			aptInfoDto.setSidoCode(dongCode.substring(0, 2));
			aptInfoDto.setGugunCode(dongCode.substring(0, 5));
		}
		return aptInfoDao.update(aptInfoDto) == 1;
	}

	@Override
	public boolean delete(int aptCode) {
		return aptInfoDao.delete(aptCode) == 1;
	}
	
}
