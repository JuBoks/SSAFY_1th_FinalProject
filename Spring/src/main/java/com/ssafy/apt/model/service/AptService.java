package com.ssafy.apt.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.apt.model.dto.AptDto;
import com.ssafy.aptinfo.model.dto.AptInfoDto;
import com.ssafy.housedeal.model.dto.HouseDealDto;

public interface AptService {

	List<AptDto> searchAptList(Map<String, Object> map) throws SQLException;

	int getAptSearchCount(Map<String, Object> map) throws SQLException;

	void registApt(AptInfoDto houseInfoDto, HouseDealDto houseDealDto) throws SQLException;

	void updateApt(AptInfoDto houseInfoDto, HouseDealDto houseDealDto) throws SQLException;

	void deleteApt(Long aptCode) throws SQLException;

}
