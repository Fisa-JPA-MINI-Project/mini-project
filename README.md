# 🚀 Fisa JPA Mini Project

> JPA + JSP 기반 **출결 관리 미니 프로젝트**

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/yunkihong-dev.png" width="120" /><br/>
      <a href="https://github.com/yunkihong-dev">홍윤기</a>
    </td>
    <td align="center">
      <img src="https://github.com/hyewon8245.png" width="120" /><br/>
      <a href="https://github.com/hyewon8245">홍혜원</a>
    </td>
    <td align="center">
      <img src="https://github.com/Gill010147.png" width="120" /><br/>
      <a href="https://github.com/Gill010147">황병길</a>
    </td>
  </tr>
</table>


## 📝 프로젝트 개요

**학생들의 출석 정보를 효율적으로 관리**하기 위한 **JSP 기반의 웹 애플리케이션**이다.
Oracle 데이터베이스와 연동하여 출석 데이터를 저장 및 조회하고, 관리자가 출석현황을 직관적으로 확인하고 조작할 수 있도록 UI와 기능을 제공한다.

---
<img width="1296" height="740" alt="image" src="https://github.com/user-attachments/assets/b7038103-ae09-4106-bfce-041e3d34bcd0" />

## 📌 주요 기능

* **출결 관리**

- **학생 출석 등록**
    - 학생별 출석 여부(출석, 퇴근)
    - 현재 날짜 기준으로 자동 처리
- **출석 그래프 조회**
    - 일자별/학생별 출석시간을 그래프로 출력
- **출석왕 조회**
    - 일자별 가장 출근 시간이 빠른 학생 조회

* JPA + Oracle DB 기반 Entity 관리
* JSP, Servlet 기반 MVC 구조
* Oracle 시퀀스 및 트리거를 통한 자동 ID 관리

---

## 🛠️ 사용 기술

<p align="center"> <img src="https://img.shields.io/badge/Java-17-007396?logo=OpenJDK&logoColor=white&style=for-t로 표시하기

* `DateTimeFormatter.ofPattern("HH:mm")` 사용하여 `01:01` 형식으로 간결하게 표시
* UI 개선:

  * `flex`, `align-items: center` 로 시간 라벨과 값이 두 줄로 분리되지 않도록 수정
  * `time-box` 스타일 개선하여 가독성 및 시인성 향상
