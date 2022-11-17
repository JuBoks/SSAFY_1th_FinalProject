package com.ssafy.util;

public class PageInfo {
	
	public static int startPage = 1;
	public static int endPage = SizeConstant.SIZE_PER_LIST;
	
	public static void setPageInfo(int curPage, int totalRowCount) {		
		int listSize = SizeConstant.LIST_SIZE;
		int spl = SizeConstant.SIZE_PER_LIST;
		
		//전체 페이지수 		
		int pageCnt = (int) Math.ceil(1.0*totalRowCount/listSize);
		
		//시작 페이지		
		startPage = ((curPage - 1) / listSize) * listSize + 1 ;	
		
		//끝 페이지		
		endPage = startPage + listSize - 1;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
	
	}
}
