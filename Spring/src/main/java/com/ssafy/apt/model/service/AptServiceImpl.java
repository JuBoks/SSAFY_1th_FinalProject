package com.ssafy.apt.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.apt.model.dao.AptDao;
import com.ssafy.apt.model.dto.AptDto;
import com.ssafy.aptinfo.model.dao.AptInfoDao;
import com.ssafy.aptinfo.model.dto.AptInfoDto;
import com.ssafy.housedeal.model.dao.HouseDealDao;
import com.ssafy.housedeal.model.dto.HouseDealDto;
import com.ssafy.util.SizeConstant;

@Service
public class AptServiceImpl implements AptService {
	
	private AptDao aptdao;
	private AptInfoDao houseInfoDao;
	private HouseDealDao houseDealDao;
	
	@Autowired
	public AptServiceImpl(AptDao aptdao, AptInfoDao houseInfoDao, HouseDealDao houseDealDao) {
		this.aptdao = aptdao;
		this.houseInfoDao = houseInfoDao;
		this.houseDealDao = houseDealDao;
	}

	@Override
	public List<AptDto> searchAptList(Map<String, Object> map) throws SQLException {
		// LIMIT ?, ?
		Object pgno = map.get("pgno");
		Object key = map.get("key");
		Object word = map.get("word");

		map.put("pgno", pgno == null ? 1 : pgno);
		map.put("key", key == null ? "" : key);
		map.put("word", word == null ? "" : word);
		
		int pgNo = Integer.parseInt(map.get("pgno")+"");
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		
		map.put("start", start);
		map.put("spl", SizeConstant.SIZE_PER_LIST);
		
		// aptDealYear, aptDealMonth 는 숫자로
		Object dealYear = map.get("dealYear");
		Object dealMonth = map.get("dealMonth");
		if(dealYear != null) map.put("dealYear", Integer.parseInt(dealYear.toString()));
		if(dealMonth != null) map.put("dealMonth", Integer.parseInt(dealMonth.toString()));
		
		// aptAddr 갱신
		ArrayList<AptDto> list = (ArrayList<AptDto>) aptdao.searchAptList(map);
		for(AptDto apt : list) {
			apt.setAptAddr();
		}
		
		return list;
	}
	
	@Override
	public int getAptSearchCount(Map<String, Object> map) throws SQLException {
		return aptdao.selectAptSearchCount(map);
	}
	
	@Transactional
	@Override
	public void registApt(AptInfoDto houseInfoDto, HouseDealDto houseDealDto) throws SQLException {
		houseInfoDao.insert(houseInfoDto);
		houseDealDao.insert(houseDealDto);
	}
	
	@Transactional
	@Override
	public void updateApt(AptInfoDto houseInfoDto, HouseDealDto houseDealDto) throws SQLException {
		houseInfoDao.update(houseInfoDto);
		houseDealDao.update(houseDealDto);
	}
	
	@Transactional
	@Override
	public void deleteApt(Long aptCode) throws SQLException {
		houseDealDao.delete(aptCode);
		houseInfoDao.delete(aptCode);
	}
	
	
}
