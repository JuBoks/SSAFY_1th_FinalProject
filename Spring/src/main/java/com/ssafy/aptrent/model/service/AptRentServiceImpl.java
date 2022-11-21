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
	public List<AptRentDto> listAptRent(Integer aptCode) throws Exception {
		return aptRentDao.listAptRent(aptCode);
	}

	@Override
	public AptRentDto search(Long no) throws Exception {
		return aptRentDao.search(no);
	}

	@Override
	public boolean insert(AptRentDto aptRentDto) throws Exception {
		return aptRentDao.insert(aptRentDto) == 1;
	}

	@Override
	public boolean update(AptRentDto aptRentDto) throws Exception {
		return aptRentDao.update(aptRentDto) == 1;
	}

	@Override
	public boolean delete(Long no) throws Exception {
		return aptRentDao.delete(no) == 1;
	}

}
