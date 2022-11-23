package com.ssafy.favoritearea.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.favoritearea.model.dto.FavoriteAreaDto;

public interface FavoriteAreaDao {

	List<FavoriteAreaDto> select(String userId) throws SQLException;
	
	int insert(FavoriteAreaDto favoriteAreaDto) throws SQLException;
	
	int delete(FavoriteAreaDto favoriteAreaDto) throws SQLException;
	
}
