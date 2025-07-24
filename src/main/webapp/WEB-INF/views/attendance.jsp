<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>출석 시간 그래프</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <h2>홍혜원 출석 시간 그래프</h2>
    <canvas id="attendanceChart" width="600" height="300"></canvas>

    <%
        List<String> dateList = (List<String>) request.getAttribute("dateList");
        List<Integer> hourList = (List<Integer>) request.getAttribute("hourList");
    %>

    <script>
        const labels = <%= dateList.toString().replace("[", "['").replace("]", "']").replace(", ", "', '") %>;
        const data = <%= hourList.toString() %>;

        new Chart(document.getElementById('attendanceChart'), {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    label: '출석 시간 (시)',
                    data: data,
                    borderColor: 'blue',
                    borderWidth: 2,
                    fill: false,
                    tension: 0.1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: '출석 시간'
                        }
                    },
                    x: {
                        title: {
                            display: true,
                            text: '출석 날짜'
                        }
                    }
                }
            }
        });
    </script>
</body>
</html>
