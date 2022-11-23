package com.ssafy.favoritearea.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.favoritearea.model.dao.FavoriteAreaDao;
import com.ssafy.favoritearea.model.dto.FavoriteAreaDto;

@Service
public class FavoriteAreaServiceImpl implements FavoriteAreaService {

	@Autowired
	private FavoriteAreaDao favoriteAreaDao;
	
	@Override
	public List<FavoriteAreaDto> select(String userId) throws SQLException {
		return favoriteAreaDao.select(userId);
	}

	@Override
	public boolean insert(FavoriteAreaDto favoriteAreaDto) throws SQLException {
		return favoriteAreaDao.insert(favoriteAreaDto) == 1;
	}

	@Override
	public boolean delete(FavoriteAreaDto favoriteAreaDto) throws SQLException {
		return favoriteAreaDao.delete(favoriteAreaDto) == 1;
	}

}
