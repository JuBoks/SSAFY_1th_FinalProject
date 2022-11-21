package com.ssafy.yeonliprent.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.yeonliprent.model.dao.YeonlipRentDao;
import com.ssafy.yeonliprent.model.dto.YeonlipRentDto;

@Service
public class YeonlipRentServiceImpl implements YeonlipRentService {

	@Autowired
	private YeonlipRentDao yeonlipRentDao;
	
	
	@Override
	public List<YeonlipRentDto> listYeonlipRent(Integer yeonlipCode) throws Exception {
		return yeonlipRentDao.listYeonlipRent(yeonlipCode);
	}

	@Override
	public YeonlipRentDto search(Long no) throws Exception {
		return yeonlipRentDao.search(no);
	}

	@Override
	public boolean insert(YeonlipRentDto yeonlipRentDto) throws Exception {
		return yeonlipRentDao.insert(yeonlipRentDto) == 1;
	}

	@Override
	public boolean update(YeonlipRentDto yeonlipRentDto) throws Exception {
		return yeonlipRentDao.update(yeonlipRentDto) == 1;
	}

	@Override
	public boolean delete(Long no) throws Exception {
		return yeonlipRentDao.delete(no) == 1;
	}
	
	
}
