package com.ssafy.yeonlipdeal.model.service;

import java.util.List;


import com.ssafy.yeonlipdeal.model.dao.YeonlipDealDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.yeonlipdeal.model.dto.YeonlipDealDto;

@Service
public class YeonlipDealServiceImpl implements YeonlipDealService {

	@Autowired
	private YeonlipDealDao yeonlipDealDao;
	
	@Override
	public List<YeonlipDealDto> listYeonlipDeal(int yeonlipCode) throws Exception {
		return yeonlipDealDao.listYeonlipDeal(yeonlipCode);
	}

	@Override
	public YeonlipDealDto search(Long no) throws Exception {
		return yeonlipDealDao.search(no);
	}

	@Override
	public boolean insert(YeonlipDealDto yeonlipDealDto) throws Exception {
		return yeonlipDealDao.insert(yeonlipDealDto) == 1;
	}

	@Override
	public boolean update(YeonlipDealDto yeonlipDealDto) throws Exception {
		return yeonlipDealDao.update(yeonlipDealDto) == 1;
	}

	@Override
	public boolean delete(Long no) throws Exception {
		return yeonlipDealDao.delete(no) == 1;
	}
	
}
