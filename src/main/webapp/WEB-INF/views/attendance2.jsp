<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>출석 현황 목록</title>
</head>
<body>
    <h2>출석 현황</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>사번(EMPNO)</th>
                <th>이름</th>
                <th>날짜</th>
                <th>출근</th>
                <th>퇴근</th>
                <th>외출시간</th>
                <th>상태</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="att" items="${attList}">
                <tr>
                    <td>${att.id}</td>
                    <td>${att.empno}</td>
                    <td>${att.name}</td>
                    <td>${att.trainDate}</td>
                    <td>${att.checkIn}</td>
                    <td>${att.checkOut}</td>
                    <td>${att.outtingStartTime}</td>
                    <td>${att.status}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
