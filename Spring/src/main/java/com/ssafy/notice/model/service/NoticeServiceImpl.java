package com.ssafy.notice.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.notice.model.dao.NoticeDao;
import com.ssafy.notice.model.dto.NoticeDto;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.SizeConstant;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDao noticeDao;
	

	@Override
	public List<NoticeDto> listArticle(Map<String, Object> map) throws Exception {
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
		
		List<NoticeDto> list = noticeDao.listArticle(map);
		
		if("precise".equals(map.get("key"))) {
			List<NoticeDto> result = new ArrayList<>(list.size());
			NoticeDto[] arr = new NoticeDto[list.size()];
			int arrSize = 0;
			
			// 알고리즘
			// 1. KMP로 content 에 나온 key 값들이 얼마나 나왔는지 확인하기.
			String word1 = map.get("word") + "";
			for(NoticeDto board : list) {
				String content = board.getContent();
				int cnt = KMP(content, word1);
				board.setWordCnt(cnt);
				// 2. 삽입 정렬
				int insertPos;
				for(insertPos = 0 ; insertPos < arrSize; insertPos++) {
					NoticeDto comp = arr[insertPos];
					if(comp.getWordCnt() < board.getWordCnt()) {
						break;
					}
				}
				
				// insertPos 이후의 요소들을 정렬해야 함
				for(int i = arrSize - 1 ; i >= insertPos ; i--) {
					arr[i+1] = arr[i];
				}
				arr[insertPos] = board;
				arrSize++;
				
			}
			for(NoticeDto board : arr) {
				result.add(board);
			}
			
			list = result;
		}
		
		return list;
	}

	// content 안에 word 가 몇 번 들어가있는지
	private int KMP(String content, String word) {
		
		char[] text = content.toCharArray();
		char[] pattern = word.toCharArray();
		
		int tLength = text.length, pLength = pattern.length;
		
		// 부분일치 테이블
		int[] pi = new int[pLength];
	    for(int i = 1, j = 0 ; i < pLength ; i++){
	        while(j > 0 && pattern[i] != pattern[j]) {
	        	j = pi[j-1]; 
	        }
	        
	        if(pattern[i] == pattern[j]) {
	        	 pi[i] = ++j;
	        }
	        else pi[i] = 0;
	    }
		
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		// i : 텍스트 포인터 , j: 패턴 포인터 
		for(int i = 0, j = 0 ; i < tLength ; i++) { 
			
			while( j > 0 && text[i] != pattern[j]) {
				j = pi[j-1];
			}
			
			if(text[i] == pattern[j]) { //두 글자 일치한다면
				if(j == pLength-1) { // j가 패턴의 마지막 인덱스라면 
					cnt++; // 카운트 증가
					list.add(i-j);  
					j = pi[j]; 
				} else { 
					j++;
				}
			}
		}
		
		return cnt;
	}
	
	@Override
	public PageNavigation makePageNavigation(Map<String, Object> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.SIZE_PER_LIST;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno") + "");

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("word"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int totalCount = noticeDao.getCountNoticeList(param);
		
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

	@Override
	public NoticeDto getArticle(int articleNo) throws Exception {
		return noticeDao.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		noticeDao.updateHit(articleNo);
	}

	@Override
	public boolean modifyArticle(NoticeDto noticeDto) throws Exception {
		return noticeDao.modifyArticle(noticeDto) == 1;
	}

	@Override
//	@Transactional
	public boolean deleteArticle(int articleNo) throws Exception {
		return noticeDao.deleteArticle(articleNo) == 1;
	}
	
	@Override
	public boolean writeArticle(NoticeDto noticeDto) throws Exception {
		return noticeDao.writeArticle(noticeDto) == 1;
	}

	@Override
	public int getCountNoticeList(Map<String, Object> map) throws Exception {
		return noticeDao.getCountNoticeList(map);
	}

}
