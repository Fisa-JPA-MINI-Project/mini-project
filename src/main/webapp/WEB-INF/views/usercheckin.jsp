<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출근/퇴근 관리</title>
<style>
body {
    font-family: 'Jua', 'Orbitron', 'Noto Sans KR', sans-serif;
    background: linear-gradient(135deg, #e0e7ef 0%, #3a7bd5 100%);
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}
.container {
    background: #f7fbff;
    padding: 2rem 2.5rem;
    border-radius: 1rem;
    box-shadow: 0 8px 24px rgba(0,0,0,0.1);
    text-align: center;
    min-width: 320px;
}
h2 {
    color: #2260a9;
    margin-bottom: 1.5rem;
}
.info {
    font-size: 1.2rem;
    color: #333;
    margin: 0.5rem 0;
    font-weight: 600;
}
.time-box {
    background: linear-gradient(90deg, #e0e7ef 0%, #c9e7ff 100%);
    padding: 1.1rem 1.5rem;
    border-radius: 1.2rem;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.25rem;
    color: #2260a9;
    gap: 0.7rem;
    box-shadow: 0 2px 8px rgba(58,123,213,0.08);
    font-weight: 500;
    letter-spacing: 1px;
    border: 1.5px solid #b6d0f7;
    margin-bottom: 0.2rem;
    transition: box-shadow 0.2s;
    position: relative;
    overflow: hidden;
}
.time-label {
    font-size: 1.08em;
    font-weight: 700;
    color: #3a7bd5;
    margin-right: 0.4em;
    display: flex;
    align-items: center;
    gap: 0.2em;
    background: linear-gradient(90deg, #e0e7ef 0%, #c9e7ff 100%);
    border-radius: 0.5em;
    padding: 0.2em 0.7em;
    box-shadow: 0 1px 4px #b6d0f733;
}
.time-value {
    font-size: 1.6em;
    font-family: 'Orbitron', 'Noto Sans KR', monospace;
    color: #fff;
    background: linear-gradient(90deg, #3a7bd5 0%, #2260a9 100%);
    padding: 0.22em 1.1em;
    border-radius: 0.8em;
    box-shadow: 0 2px 8px #3a7bd555;
    font-weight: 900;
    letter-spacing: 2.5px;
    margin-left: 0.5em;
    border: 2px solid #fff;
    text-shadow: 0 2px 8px #2260a966, 0 1px 0 #fff;
    display: flex;
    align-items: center;
    min-width: 90px;
    justify-content: center;
    transition: background 0.2s, color 0.2s;
}
.time-box .time-label .icon {
    font-size: 1.3em;
    margin-right: 0.18em;
    filter: drop-shadow(0 1px 2px #b6d0f7aa);
}
.button-container {
    display: flex;
    justify-content: space-between;
    margin-top: 1.5rem;
}
.button-container form {
    flex: 1;
    margin: 0 0.5rem;
}
button {
    width: 100%;
    padding: 0.8rem 0;
    background: #3a7bd5;
    border: none;
    border-radius: 0.5rem;
    color: white;
    font-weight: 600;
    cursor: pointer;
    font-size: 1rem;
    transition: background 0.2s;
}
button:disabled {
    background: #b0b0b0;
    cursor: not-allowed;
}
button:hover:enabled {
    background: #2260a9;
}
</style>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Orbitron:wght@700&display=swap">
</head>
<body>
    <jsp:include page="../WebContent/righttab.html" />

<div class="container">
    <h2>출근/퇴근 관리</h2>

    <%
        String username = (String) session.getAttribute("userName");
        Long userid = (Long) session.getAttribute("userId");
        LocalDateTime checkInTime = (LocalDateTime) request.getAttribute("checkintime");
        LocalDateTime checkOutTime = (LocalDateTime) request.getAttribute("checkouttime");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    %>

    <div class="info"><strong><%= username != null ? username : "정보 없음" %></strong> 님 반갑습니다!</div>

    <div class="button-container">
        <!-- 출근 버튼 또는 시간 표시 -->
        <% if (checkInTime == null) { %>
            <form action="checkin" method="post">
                <button type="submit">출근하기</button>
            </form>
        <% } else { %>
            <div class="time-box" style="flex:1; margin:0 0.5rem;">
                <span class="time-label">
                    <span class="icon">🧑‍💼</span>
                    <span>출근</span>
                </span>
                <span class="time-value"><%= checkInTime.format(formatter) %></span>
            </div>        <% } %>

        <!-- 퇴근 버튼 or 시간 표시 or disable 상태 -->
        <% if (checkOutTime != null) { %>
            <div class="time-box" style="flex:1; margin:0 0.5rem;">
                <span class="time-label">
                    <span class="icon">🏠</span>
                    <span>퇴근</span>
                </span>
                <span class="time-value"><%= checkOutTime.format(formatter) %></span>
            </div>
        <% } else if (checkInTime != null) { %>
            <form action="checkout" method="post">
                <button type="submit">퇴근하기</button>
            </form>
        <% } else { %>
            <form>
                <button type="button" disabled>퇴근하기</button>
            </form>
        <% } %>
    </div>

    <form action="logout" method="post" style="margin-top:1.5rem;">
        <button type="submit">로그아웃</button>
    </form>
</div>
</body>
</html>
