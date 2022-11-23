package com.ssafy.favoritearea.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.favoritearea.model.dto.FavoriteAreaDto;

public interface FavoriteAreaService {
	
	List<FavoriteAreaDto> select(String userId) throws SQLException;
	
	boolean insert(FavoriteAreaDto favoriteAreaDto) throws SQLException;
	
	boolean delete(FavoriteAreaDto favoriteAreaDto) throws SQLException;
}
