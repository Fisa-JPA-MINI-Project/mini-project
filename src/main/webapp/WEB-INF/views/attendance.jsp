<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*, model.entity.StudentAttendance" %>

<html>
<head>
    <title>출석 시간 그래프</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <h2>출석 현황 그래프</h2>
    <canvas id="attendanceChart" width="800" height="400"></canvas>

    <script>
        // JSP에서 출석 데이터를 JS 배열로 추출
        const attendanceData = [
            <c:forEach var="att" items="${attList}" varStatus="status">
                {
                    date: '<fmt:formatDate value="${att.trainDate}" pattern="yyyy-MM-dd" />',
                    checkIn: '<fmt:formatDate value="${att.checkIn}" pattern="HH:mm" />'
                }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];

        // 디버깅: 콘솔에 데이터 확인
        console.log("attendanceData", attendanceData);

        // 시간 문자열 ("HH:mm")을 소수점 시간 (예: "09:30" → 9.5)으로 변환
        function timeStringToHourDecimal(timeStr) {
            if (!timeStr || timeStr.length < 5) return null;
            const [h, m] = timeStr.split(':');
            return parseInt(h) + parseInt(m) / 60;
        }

        const labels = attendanceData.map(item => item.date);
        const data = attendanceData.map(item => timeStringToHourDecimal(item.checkIn));

        const ctx = document.getElementById('attendanceChart').getContext('2d');
        const chart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    label: '출근 시간',
                    data: data,
                    borderColor: 'blue',
                    backgroundColor: 'lightblue',
                    tension: 0.2,
                    fill: false,
                    pointRadius: 4,
                    pointHoverRadius: 6
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: false,
                        min: 6,
                        max: 12,
                        title: {
                            display: true,
                            text: '출근 시간 (시)'
                        },
                        ticks: {
                            callback: function(value) {
                                return value + '시';
                            }
                        }
                    },
                    x: {
                        title: {
                            display: true,
                            text: '출석 날짜'
                        }
                    }
                },
                plugins: {
                    legend: {
                        display: true,
                        position: 'top'
                    }
                }
            }
        });
    </script>
</body>
</html>
