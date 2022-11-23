package com.ssafy.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.user.model.dao.TmpNumDao;
import com.ssafy.user.model.dto.TmpNumDto;

@Service
public class TmpNumServiceImpl implements TmpNumService {

	@Autowired
	TmpNumDao tmpNumDao;
	
	@Override
	public TmpNumDto select(String userId) throws Exception {
		return tmpNumDao.select(userId);
	}

	@Override
	public boolean insert(TmpNumDto tmpNumDto) throws Exception {
		return tmpNumDao.insert(tmpNumDto) == 1;
	}

	@Override
	public boolean delete(String userId) throws Exception {
		return tmpNumDao.delete(userId) == 1;
	}

}
