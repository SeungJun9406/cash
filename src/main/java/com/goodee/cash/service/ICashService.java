package com.goodee.cash.service;

import java.util.Map;

public interface ICashService {
	public Map<String, Object> getCalendar(String memberId, Integer targetYear, Integer targetMonth); 
	// 디커플링을 위한 추가
	// 인터페이스 없을 시 
	// 구현체가 더이상 없어 서비스를 추가하지않았다.
}
