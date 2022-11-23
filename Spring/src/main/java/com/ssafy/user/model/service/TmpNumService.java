package com.ssafy.user.model.service;

import com.ssafy.user.model.dto.TmpNumDto;

public interface TmpNumService {
	
	TmpNumDto select(String userId) throws Exception;
	
	boolean insert(TmpNumDto tmpNumDto) throws Exception;
	
	boolean delete(String userId) throws Exception;
	
}
