<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="m" value="${resultMap}"></c:set>
	<h1>
		<a href="/cash/calendar?targetYear=${m.targetYear}&targetMonth=${m.targetMonth - 1}">이전달</a>
		${m.targetYear}년 ${m.targetMonth + 1}월 달력
		<a href="/cash/calendar?targetYear=${m.targetYear}&targetMonth=${m.targetMonth + 1}">다음달</a>
	</h1>
	
	<table>
		<tr>
			<th style="width: 14%">일</th>
			<th style="width: 14%">월</th>
			<th style="width: 14%">화</th>
			<th style="width: 14%">수</th>
			<th style="width: 14%">목</th>
			<th style="width: 14%">금</th>
			<th style="width: 14%">토</th>
		</tr>
		<tr>
		<!-- calendar 출력 -->
		<c:forEach var="i" begin="0" end="${m.totalTd - 1}">
		<!-- 행바꿈 -->
		<c:if test="${i % 7 == 0 && i != 0}">
			</tr>
			<tr>
		</c:if>
		<!-- beginBlank -->
		<c:if test="${i < m.beginBlank}">
			<td>&nbsp;</td>
		</c:if>
		<!-- 날짜 출력 -->
		<c:if test="${i >= m.beginBlank && (i - m.beginBlank) < m.lastDate}">
			<td>
				<div>
					${i - m.beginBlank + 1}
				</div>
			<c:forEach var="l" items="${m.cashbookList}">
				<c:if test="${fn:substring(l.cashbookDate,8,10) == (i - m.beginBlank + 1)}">
					<c:if test="${l.category == \"지출\"}">
					<div style="color: red;">
						-${l.price}
					</div>
					</c:if>
					<c:if test="${l.category == \"수입\"}">
					<div style="color: blue;">
						+${l.price}
					</div>
					</c:if>
				</c:if>
			</c:forEach>
			</td>
		</c:if>
		<!-- endBlank -->
		<c:if test="${(i - m.beginBlank) >= m.lastDate}">
			<td>&nbsp;</td>
		</c:if>
		</c:forEach>
		</tr>
	</table>
</body>
</html>