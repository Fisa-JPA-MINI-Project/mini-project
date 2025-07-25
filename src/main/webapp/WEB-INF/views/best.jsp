<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="model.entity.StudentAttendance"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%
StudentAttendance best = (StudentAttendance) request.getAttribute("best");
java.time.LocalDate selectedDate = (java.time.LocalDate) request.getAttribute("selectedDate");
String checkInTime = best.getCheckIn() != null
		? best.getCheckIn().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
		: "-";
%>
<!DOCTYPE html>
<html>
<head>
<title><%=selectedDate%> 최조 출석자 - WOORI ATTENDANCE</title>
<style>
body {
	font-family: 'Noto Sans KR', '맑은 고딕', Arial, sans-serif;
	background: linear-gradient(135deg, #355b96 0%, #00b4de 100%);
	margin: 0;
	min-height: 100vh;
}

.wrapper {
	margin: 90px auto;
	background: #fff;
	border-radius: 18px;
	min-width: 360px;
	width: 420px;
	box-shadow: 0 6px 32px rgba(36, 78, 125, 0.09);
	text-align: center;
	padding: 44px 28px 38px 28px;
}

.woori-logo {
	font-size: 2em;
	font-weight: 900;
	letter-spacing: 2px;
	color: #2174d1;
	margin-bottom: 8px;
}

.date {
	color: #1571c3;
	background: #e9f5fd;
	font-size: 1.12em;
	font-weight: bold;
	border-radius: 8px;
	display: inline-block;
	padding: 4px 15px;
	margin-bottom: 23px;
}

.desc {
	color: #375982;
	font-size: 1.05em;
	margin-bottom: 30px;
}

table {
	font-size: 1.1em;
	margin: 0 auto 28px auto;
	border-collapse: collapse;
}

th, td {
	padding: 10px 18px;
	border: 0;
	text-align: left;
}

th {
	color: #2174d1;
	font-weight: 700;
	width: 90px;
}

.best-icon {
	font-size: 2.4em;
	color: #00b1d1;
	margin-bottom: 9px;
}

.Best {
	padding: 8px 28px;
	font-size: 1em;
	border: none;
	border-radius: 6px;
	background: linear-gradient(90deg, #2174d1 0%, #00b4de 100%);
	color: #fff;
	font-weight: bold;
	box-shadow: 0 1px 12px 0 rgba(48, 78, 125, 0.11);
	cursor: pointer;
	text-decoration: none;
	margin-top: 20px;
}

.back-btn:hover {
	background: linear-gradient(90deg, #1456b7 0, #22b4ec 100%);
}
</style>
</head>
<body>
	<div class="wrapper">
		<div class="woori-logo">
			<span style="vertical-align: middle;">우리</span> <span
				style="vertical-align: middle;">FISA</span>
		</div>
		<div class="date"><%=selectedDate%></div>
		<div class="best-icon">🌅</div>
		<div class="desc">
			이 날 가장 먼저 출석한 <br> ❗출근왕❗
		</div>
		<table>
			<tr>
				<th>이름</th>
				<td><%=best.getName()%></td>
			</tr>
			<tr>
				<th>번호</th>
				<td><%=best.getEmpno()%></td>
			</tr>
			<tr>
				<th>시간</th>
				<td style="color: #218dfc;"><%= checkInTime %></td>
			</tr>
		</table>
		<a class="Best" href="Best">다른 날짜 조회</a>
	</div>
</body>
</html>