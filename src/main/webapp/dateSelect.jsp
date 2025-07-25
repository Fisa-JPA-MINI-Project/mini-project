<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>날짜 선택 | WOORI ATTENDANCE</title>
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
            width: 380px;
            margin: 100px auto 0;
            padding: 38px 30px 32px 30px;
            text-align: center;
        }
        .woori-logo {
            font-size: 2em;
            font-weight: 900;
            letter-spacing: 2px;
            color: #2174d1;
            margin-bottom: 14px;
        }
        h2 {
            color: #184777;
            margin-bottom: 25px;
            font-weight: 700;
        }
        select, button {
            font-size: 1.1em;
            padding: 8px 16px;
            margin: 0 0 30px 0;
            width: 80%;
            border-radius: 9px;
            border: 1.3px solid #d9e8fc;
            transition: border 0.35s;
            background: #f5f9fe;
            color: #184777;
        }
        select:focus {
            outline: none;
            border: 1.6px solid #2174d1;
        }
        .woori-btn {
            padding: 11px 0;
            width: 80%;
            background: linear-gradient(90deg, #1456b7 0, #00aeff 100%);
            color: #fff;
            font-weight: bold;
            border: none;
            border-radius: 9px;
            font-size: 1.1em;
            box-shadow: 0 2px 10px 0 rgba(48,78,125,0.11);
            cursor: pointer;
            margin-bottom: 0;
        }
        .woori-btn:hover {
            background: linear-gradient(90deg, #2174d1 10%, #00b4de 90%);
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="woori-logo">
            <span style="vertical-align: middle;">우리</span>
            <span style="vertical-align: middle;">FISA</span>
        </div>
        <h2>출석 조회 날짜 선택</h2>
        <form action="Best" method="get">
            <select name="date" required>
                <option value="" disabled selected>날짜를 선택하세요</option>
                <option value="2025-07-01">2025-07-01</option>
                <option value="2025-07-02">2025-07-02</option>
                <option value="2025-07-03">2025-07-03</option>
                <option value="2025-07-04">2025-07-04</option>
                <option value="2025-07-05">2025-07-05</option>
                <option value="2025-07-06">2025-07-06</option>
                <option value="2025-07-07">2025-07-07</option>
                <option value="2025-07-08">2025-07-08</option>
                <option value="2025-07-09">2025-07-09</option>
                <option value="2025-07-10">2025-07-10</option>
                <option value="2025-07-11">2025-07-11</option>
                <option value="2025-07-12">2025-07-12</option>
                <option value="2025-07-13">2025-07-13</option>
                <option value="2025-07-14">2025-07-14</option>
                <option value="2025-07-15">2025-07-15</option>
                <option value="2025-07-16">2025-07-16</option>
                <option value="2025-07-17">2025-07-17</option>
                <option value="2025-07-18">2025-07-18</option>
                <option value="2025-07-19">2025-07-19</option>
                <option value="2025-07-20">2025-07-20</option>
                <option value="2025-07-21">2025-07-21</option>
                <option value="2025-07-22">2025-07-22</option>
                <option value="2025-07-23">2025-07-23</option>
                <option value="2025-07-24">2025-07-24</option>
                <option value="2025-07-25">2025-07-25</option>
                <option value="2025-07-26">2025-07-26</option>
            </select>
            <br>
            <button type="submit" class="woori-btn">가장 빠른 출석자 확인</button>
        </form>
    </div>
</body>
</html>
