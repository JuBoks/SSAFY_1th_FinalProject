package com.ssafy.area.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.area.model.dao.AreaDao;
import com.ssafy.area.model.dto.AreaDto;

@Service
public class AreaServiceImpl implements AreaService {

	private AreaDao areaDao;
	
	public AreaServiceImpl(AreaDao areaDao) {
		this.areaDao = areaDao;
	}
	
	@Override
	public List<AreaDto> selectSidoNames() throws SQLException {
		return areaDao.selectSidoNames();
	}

	@Override
	public List<AreaDto> selectGugunNames(String sidoCode) throws SQLException {
		return areaDao.selectGugunNames(sidoCode);
	}

	@Override
	public List<AreaDto> selectDongNames(String gugunCode) throws SQLException {
		return areaDao.selectDongNames(gugunCode);
	}
}
