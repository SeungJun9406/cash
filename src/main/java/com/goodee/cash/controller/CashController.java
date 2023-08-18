package com.goodee.cash.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.cash.service.CashService;
import com.goodee.cash.service.ICashService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class CashController {
	@Autowired private ICashService cashService;
	@GetMapping("/calendar")
	public String calendar(HttpSession session, Model model,
			@RequestParam(required = false, name = "targetYear") Integer targetYear,
			@RequestParam(required = false, name= "targetMonth") Integer targetMonth) {
		String memberId = "user";
		Map<String, Object> resultMap = cashService.getCalendar(memberId, targetYear, targetMonth);
		
		log.debug("CashController.calendar() resultMap : " + resultMap.toString());
		return "calendar";
	}
}
