package com.ssafy.yeonlipinfo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.yeonlipinfo.model.dao.YeonlipInfoDao;
import com.ssafy.yeonlipinfo.model.dto.YeonlipInfoDto;

@Service
public class YeonlipInfoServiceImpl implements YeonlipInfoService {

	@Autowired
	private YeonlipInfoDao yeonlipInfoDao;
	
	@Override
	public List<YeonlipInfoDto> listYeonlipInfo(String dongCode) throws Exception {
		return yeonlipInfoDao.listYeonlipInfo(dongCode);
	}

	@Override
	public YeonlipInfoDto search(Integer yeonlipCode) throws Exception {
		return yeonlipInfoDao.search(yeonlipCode);
	}

	@Override
	public boolean insert(YeonlipInfoDto yeonlipInfoDto) throws Exception {
		// sidoCode, gugunCode 설정하기
		String dongCode = yeonlipInfoDto.getDongCode();
		if(dongCode != null) {
			yeonlipInfoDto.setSidoCode(dongCode.substring(0, 2));
			yeonlipInfoDto.setGugunCode(dongCode.substring(0, 5));
		}
		return yeonlipInfoDao.insert(yeonlipInfoDto) == 1;
	}

	@Override
	public boolean update(YeonlipInfoDto yeonlipInfoDto) throws Exception {
		// sidoCode, gugunCode 설정하기
		String dongCode = yeonlipInfoDto.getDongCode();
		if(dongCode != null) {
			yeonlipInfoDto.setSidoCode(dongCode.substring(0, 2));
			yeonlipInfoDto.setGugunCode(dongCode.substring(0, 5));
		}
		return yeonlipInfoDao.update(yeonlipInfoDto) == 1;
	}

	@Override
	public boolean delete(Integer yeonlipCode) throws Exception {
		return yeonlipInfoDao.delete(yeonlipCode) == 1;
	}

}
