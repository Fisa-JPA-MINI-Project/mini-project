<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>출석 시간 그래프</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            font-family: 'Noto Sans KR', '맑은 고딕', Arial, sans-serif;
            background: linear-gradient(135deg, #355b96 0%, #00b4de 100%);
            min-height: 100vh;
            margin: 0;
            padding: 0;
        }
        .container {
            background: #fff;
            border-radius: 18px;
            box-shadow: 0 4px 24px 0 rgba(48,78,125,0.14);
            width: 480px;
            margin: 70px auto 0;
            padding: 38px 30px 32px 30px;
            text-align: center;
        }
        h2 {
            color: #184777;
            margin-bottom: 25px;
            font-weight: 700;
        }
        #attendanceChart {
            background: #f5f9fe;
            border-radius: 12px;
            box-shadow: 0 2px 8px rgba(48,78,125,0.09);
            padding: 12px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>출석 시간 그래프</h2>
        <canvas id="attendanceChart" width="400" height="300"></canvas>
        <%-- JSP에서 배열을 안전하게 JS로 넘기기 --%>
        <%
            List<String> dateList = (List<String>) request.getAttribute("dateList");
            List<Double> hourList = (List<Double>) request.getAttribute("hourList");
            StringBuilder labelStr = new StringBuilder("[");
            if (dateList != null && !dateList.isEmpty()) {
                for (int i = 0; i < dateList.size(); i++) {
                    labelStr.append("'").append(dateList.get(i)).append("'");
                    if (i < dateList.size() - 1) labelStr.append(",");
                }
            }
            labelStr.append("]");
            StringBuilder dataStr = new StringBuilder("[");
            if (hourList != null && !hourList.isEmpty()) {
                for (int i = 0; i < hourList.size(); i++) {
                    dataStr.append(hourList.get(i));
                    if (i < hourList.size() - 1) dataStr.append(",");
                }
            }
            dataStr.append("]");
        %>
        <script>
        function hourToTimeLabel(value) {
            if (value == null || isNaN(value)) return '';
            const hour = Math.floor(value);
            const minute = Math.round((value - hour) * 60);
            return hour + ':' + (minute < 10 ? '0' : '') + minute;
        }
        const labels = <%= labelStr %>;
        const data = <%= dataStr %>;
        new Chart(document.getElementById('attendanceChart'), {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    label: '출석 시각(시간)',
                    data: data,
                    borderColor: '#2174d1',
                    backgroundColor: 'rgba(33, 116, 209, 0.13)',
                    borderWidth: 3,
                    pointBackgroundColor: '#355b96',
                    pointBorderColor: '#fff',
                    pointRadius: 5,
                    fill: true,
                    tension: 0.25
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        labels: {
                            color: '#184777',
                            font: { size: 16 }
                        }
                    },
                    tooltip: {
                        callbacks: {
                            label: function(context) {
                                const v = context.parsed.y;
                                return '출석 시각: ' + hourToTimeLabel(v);
                            }
                        }
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: '출석 시각(시간)',
                            color: '#184777',
                            font: { size: 15 }
                        },
                        ticks: {
                            color: '#184777',
                            font: { size: 13 },
                            callback: function(value) {
                                return hourToTimeLabel(value);
                            }
                        },
                        grid: {
                            color: '#d9e8fc'
                        }
                    },
                    x: {
                        title: {
                            display: true,
                            text: '출석 날짜',
                            color: '#184777',
                            font: { size: 15 }
                        },
                        ticks: {
                            color: '#184777',
                            font: { size: 13 }
                        },
                        grid: {
                            color: '#d9e8fc'
                        }
                    }
                }
            }
        });
        </script>
    </div>
</body>
</html>
