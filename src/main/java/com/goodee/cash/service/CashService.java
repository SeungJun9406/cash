package com.goodee.cash.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.cach.mapper.CashMapper;
import com.goodee.cash.vo.Cashbook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CashService implements ICashService{
	@Autowired
	private CashMapper cashMapper;
	public Map<String, Object> getCalendar(String memberId, Integer targetYear, Integer targetMonth){
		
		Calendar firstDate = Calendar.getInstance();
		firstDate.set(Calendar.DATE, 1);
		// 첫번째 날짜
		firstDate.set(Calendar.DATE, 1);
		// 원하는 년월이 요청 매개값으로 넘어왔다면
		if(targetYear != null && targetMonth != null) {
			firstDate.set(Calendar.YEAR, targetYear);
			firstDate.set(Calendar.MONTH, targetMonth);
		}
		
		targetYear = firstDate.get(Calendar.YEAR);
		targetMonth = firstDate.get(Calendar.MONTH);
		
		// 마지막 날짜
		int lastDate = firstDate.getActualMaximum(Calendar.DATE);
		
		// 시작할 공백의 수  -> 요일 맵핑 수 -1
		int beginBlank = firstDate.get(Calendar.DAY_OF_WEEK)-1;
		
		
		// 마지막 날짜 이후 출력할 공백의 수
		int endBlank = 0;
		if ((beginBlank + lastDate + endBlank) % 7 != 0) {
			endBlank = 7-((beginBlank + lastDate + endBlank) %7);
		}
		int totalTd = beginBlank + lastDate + endBlank;
		
		//mapper에 들어갈 param
		Map<String, Object> mapperParam = new HashMap<>();
		mapperParam.put("memberId", memberId);
		mapperParam.put("targetYear", targetYear);
		mapperParam.put("targetMonth", targetMonth + 1);
		
		List<Cashbook> cashbookList = cashMapper.selectCashbookListByMonth(mapperParam);
		
		//map에 갑 입력
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("targetYear", targetYear);
		resultMap.put("targetMonth", targetMonth);
		resultMap.put("lastDate", lastDate);
		resultMap.put("beginBlank", beginBlank);
		resultMap.put("endBlank", endBlank);
		resultMap.put("totalTd", totalTd);
		resultMap.put("cashbookList", cashbookList);
		
		log.debug("CashService.getCalendar() resultMap " + resultMap.toString());
		log.debug("CashService.getCalendar() cashbookList " + cashbookList.toString());
		return resultMap;
	}
}
