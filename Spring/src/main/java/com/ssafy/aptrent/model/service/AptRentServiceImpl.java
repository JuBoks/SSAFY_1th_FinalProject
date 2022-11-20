package com.ssafy.aptrent.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.aptrent.model.dao.AptRentDao;
import com.ssafy.aptrent.model.dto.AptRentDto;

@Service
public class AptRentServiceImpl implements AptRentService {
	
	@Autowired
	private AptRentDao aptRentDao;

	@Override
	public List<AptRentDto> listAptRent(Integer aptCode) {
		return aptRentDao.listAptRent(aptCode);
	}

	@Override
	public AptRentDto search(Long no) {
		return aptRentDao.search(no);
	}

	@Override
	public boolean insert(AptRentDto aptRentDto) {
		return aptRentDao.insert(aptRentDto) == 1;
	}

	@Override
	public boolean update(AptRentDto aptRentDto) {
		return aptRentDao.update(aptRentDto) == 1;
	}

	@Override
	public boolean delete(Long no) {
		return aptRentDao.delete(no) == 1;
	}

}
