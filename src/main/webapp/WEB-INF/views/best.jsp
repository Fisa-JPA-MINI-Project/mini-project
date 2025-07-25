<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	model.entity.StudentAttendance best = (model.entity.StudentAttendance) request.getAttribute("best");
	java.time.LocalDate selectedDate = (java.time.LocalDate) request.getAttribute("selectedDate");
	%>
	오늘 제일 먼저 출석한 사람:
	<b><%=best.getName()%></b> (사번:
	<%=best.getEmpno()%>)
	<br> 출근 시각:
	<b><%=best.getCheckIn()%></b>
	<hr>
	<br>
	<form action="Best" method="get">
		<label>조회할 날짜:</label> <select name="date">
			<option value="2025-07-01">2025-07-01</option>
			<option value="2025-07-02">2025-07-02</option>
			<!-- ... 중간생략 ... -->
			<option value="2025-07-23">2025-07-23</option>
		</select>
		<button type="submit">가장 빨리 출석한 사람 찾기</button>
	</form>
</body>
</html>