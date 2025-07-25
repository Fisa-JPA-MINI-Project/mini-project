<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>출석자 없음</title>
<style>
body {
	font-family: '맑은 고딕', Arial, sans-serif;
	background: #f5f5f5;
	margin: 0;
	padding: 0;
}

.fail-container {
	margin: 100px auto;
	background: #fff;
	border-radius: 10px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.07);
	width: 360px;
	padding: 36px 30px;
	text-align: center;
}

.fail-message {
	color: #e74c3c;
	font-size: 1.3em;
	margin-bottom: 20px;
}

.home-btn {
	padding: 8px 25px;
	font-size: 1em;
	border: none;
	border-radius: 5px;
	background: #626fff;
	color: #fff;
	cursor: pointer;
	text-decoration: none;
}

.home-btn:hover {
	background: #4957CC;
}
</style>
</head>
<body>
	<%
	String msg = (String) request.getAttribute("msg");
	java.time.LocalDate selectedDate = (java.time.LocalDate) request.getAttribute("selectedDate");
	%>
	<div class="fail-container">
		<div class="fail-message">
			<%=request.getAttribute("msg") != null ? request.getAttribute("msg") : "아직 출석한 사람이 없습니다."%>
		</div>

		<a href="index.html" class="home-btn">메인으로</a>
	</div>
</body>
</html>
