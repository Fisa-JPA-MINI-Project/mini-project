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

이 프로젝트는 **학생들의 출석 정보를 효율적으로 관리**하기 위한 **JSP 기반의 웹 애플리케이션**이다.
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

<p align="center"> <img src="https://img.shields.io/badge/Java-17-007396?logo=OpenJDK&logoColor=white&style=for-the-badge" alt="Java 17"/> <img src="https://img.shields.io/badge/Jakarta%20Servlet-Tomcat-2496ED?logo=apachetomcat&logoColor=white&style=for-the-badge" alt="Servlet Tomcat"/> <img src="https://img.shields.io/badge/JSP-%23007396?logo=java&logoColor=white&style=for-the-badge" alt="JSP"/> <img src="https://img.shields.io/badge/JPA-Hibernate-59666C?logo=hibernate&logoColor=white&style=for-the-badge" alt="JPA Hibernate"/> <img src="https://img.shields.io/badge/Oracle-Database-F80000?logo=oracle&logoColor=white&style=for-the-badge" alt="Oracle DB"/> <img src="https://img.shields.io/badge/Maven-%23C71A36?logo=apachemaven&logoColor=white&style=for-the-badge" alt="Maven"/> <img src="https://img.shields.io/badge/Lombok-%23007696?logo=java&logoColor=white&style=for-the-badge" alt="Lombok"/> </p>

---

## ⚙️ 프로젝트 구조

```
mini-project/
├── pom.xml                          # Maven 프로젝트 설정 파일
├── src/
│   └── main/
│       ├── java/
│       │   ├── controller/          # 컨트롤러 서블릿 클래스들
│       │   │   ├── Attendance.java
│       │   │   ├── BestDriver.java
│       │   │   ├── CheckInController.java
│       │   │   ├── CheckOutController.java
│       │   │   ├── LogoutController.java
│       │   │   └── UserController.java
│       │   ├── model/               # DB 연동 관련 클래스
│       │   │   ├── entity/              
│       │   │   │   ├── StudentAttendance.java   # 출석 Entity (JPA)
│       │   │   │   └── MemberDAO.java           # DAO 클래스 (직접 DB 접근)
│       │   └── util/
│       │       └── DBUtil.java      # JDBC 연결 도우미 클래스
│       └── webapp/
│           ├── index.html           # 루트 접속 시 첫 페이지
│           ├── resources/           # 정적 리소스 파일
│           ├── WebContent/          # 웹 리소스 보조 디렉토리
│           └── WEB-INF/
│               └── views/           # JSP 및 HTML View 페이지들
│                   ├── 404.html
│                   ├── 500.html
│                   ├── attendance.jsp
│                   ├── best.jsp
│                   ├── dateSelect.jsp
│                   ├── fail.jsp
│                   ├── loginfail.jsp
│                   └── usercheckin.jsp
└── target/                          # Maven 빌드 결과물 저장 폴더
```


---

## 💡 ERD

<img width="400" alt="image" src="https://github.com/user-attachments/assets/83d87a49-641a-4574-b4c3-f1ac36983b6c" />


---



## 🩹 트러블슈팅

### 1️⃣ `IdentifierGenerationException: ids for this class must be manually assigned before calling save()`

* **원인**: `@Id` 만 설정하고 `@GeneratedValue` 를 설정하지 않아 Oracle에서 트리거로 PK를 채우는데 Hibernate가 자동 생성 전략을 지원하지 않아 발생
* **해결**:

  * 엔티티에 `@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "...")` + `@SequenceGenerator` 추가
  * Oracle 트리거/시퀀스를 사용하여 ID 자동 주입하도록 설정

---

### 2️⃣ `IllegalArgumentException: IdentityColumnSupportImpl does not support identity key generation`

* **원인**: Oracle Dialect에서 `GenerationType.IDENTITY` 사용 시 지원하지 않아 발생
* **해결**: `GenerationType.SEQUENCE` + `@SequenceGenerator` 로 변경

---

### 3️⃣ 출근/퇴근 시 `NullPointerException`

* **원인**:

  * 출근 시 `StudentAttendance` 엔티티를 insert하지 못해 null 반환
  * JSP에서 `request.getAttribute("checkintime")` 가 null 로 내려옴
* **해결**:

  * `EntityManager` persist 로 insert 수행
  * 출근/퇴근 시 `request.setAttribute` 와 `session.setAttribute` 구분하여 관리
  * JSP에서 `requestScope.getAttribute()` 또는 `request.getAttribute()` 로 가져오도록 통일

---

### 4️⃣ 시퀀스/트리거 설정 후에도 ID 가 안들어감

* **원인**: Hibernate가 ID를 null로 판단해 insert 전에 id가 null이 아님을 보장해야 함
* **해결**:

  * Oracle 트리거로 ID가 주입되도록 DB 측 설정 확인
  * Hibernate Dialect 확인 및 `hibernate.id.new_generator_mappings=false` 로 설정

---

### 5️⃣ JSP 시간 포맷 직관적으로 표시하기

* `DateTimeFormatter.ofPattern("HH:mm")` 사용하여 `01:01` 형식으로 간결하게 표시
* UI 개선:

  * `flex`, `align-items: center` 로 시간 라벨과 값이 두 줄로 분리되지 않도록 수정
  * `time-box` 스타일 개선하여 가독성 및 시인성 향상
