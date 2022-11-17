package com.ssafy.area.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.area.model.dto.AreaDto;

public interface AreaService {

	List<AreaDto> selectSidoNames() throws SQLException;

	List<AreaDto> selectGugunNames(String sidoCode) throws SQLException;

	List<AreaDto> selectDongNames(String gugunCode) throws SQLException;

}
