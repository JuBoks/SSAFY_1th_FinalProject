package com.ssafy.aptdeal.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.aptdeal.model.dao.AptDealDao;
import com.ssafy.aptdeal.model.dto.AptDealDto;

@Service
public class AptDealServiceImpl implements AptDealService {

	@Autowired
	private AptDealDao aptDealDao;
	
	@Override
	public List<AptDealDto> listAptDeal(int aptCode) {
		return aptDealDao.listAptDeal(aptCode);
	}

	@Override
	public AptDealDto search(Long no) {
		return aptDealDao.search(no);
	}

	@Override
	public boolean insert(AptDealDto aptDealDto) {
		return aptDealDao.insert(aptDealDto) == 1;
	}

	@Override
	public boolean update(AptDealDto aptDealDto) {
		return aptDealDao.update(aptDealDto) == 1;
	}

	@Override
	public boolean delete(Long no) {
		return aptDealDao.delete(no) == 1;
	}

}
