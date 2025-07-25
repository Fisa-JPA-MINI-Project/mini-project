<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>출석자 없음 | WOORI ATTENDANCE</title>
<style>
body {
    font-family: 'Noto Sans KR', '맑은 고딕', Arial, sans-serif;
    background: linear-gradient(135deg, #355b96 0%, #00b4de 100%);
    margin: 0; min-height: 100vh;
}
.fail-container {
    margin: 120px auto;
    background: #fff;
    border-radius: 18px;
    box-shadow: 0 4px 20px 0 rgba(48,78,125,0.16);
    width: 420px; min-width: 340px;
    padding: 48px 32px;
    text-align: center;
}
.woori-logo {
    font-size: 2em; font-weight: 900; letter-spacing: 2px; color: #2174d1; margin-bottom: 9px;
}
.fail-message {
    color: #d12132;
    font-size: 1.16em;
    margin-bottom: 22px;
}
.selected-date {
    font-weight: bold;
    color: #235ab3;
    background: #e6eef8;
    padding: 3px 12px;
    border-radius: 8px;
}
.home-btn {
    padding: 8px 28px;
    font-size: 1em;
    border: none;
    border-radius: 6px;
    background: linear-gradient(90deg, #2174d1 0%, #00b4de 100%);
    color: #fff;
    font-weight: bold;
    box-shadow: 0 1px 12px rgba(48,78,125,0.09);
    cursor: pointer;
    text-decoration: none;
    margin-top: 16px;
}
.home-btn:hover {
    background: linear-gradient(90deg, #1456b7 0, #22b4ec 100%);
}
</style>
</head>
<body>
<%
String msg = (String) request.getAttribute("msg");
java.time.LocalDate selectedDate = (java.time.LocalDate) request.getAttribute("selectedDate");
%>
<div class="fail-container">
    <div class="woori-logo">
        <span>우리</span>
        <span>FISA</span>
    </div>
    <div class="selected-date">
        <%= selectedDate == null ? "" : selectedDate %>
    </div>
    <div class="fail-message" style="margin-bottom:30px;">
        <%= msg != null ? msg : "아직 출석한 사람이 없습니다." %>
    </div>
    <a href="Best" class="home-btn">날짜 다시 선택</a>
</div>
</body>
</html>
