package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.entity.StudentAttendance;

@WebServlet("/Best")
public class BestDriver extends HttpServlet {
	private static final long serialVersionUID = 1L;

// GET: 특정 날짜를 파라미터로 받거나(없으면 오늘)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dateStr = request.getParameter("date");
		if (dateStr == null || dateStr.isEmpty()) {
// 1. 날짜 선택 화면 먼저 보여주기
// request.getRequestDispatcher("/WEB-INF/views/dateSelect.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/dateSelect.jsp");
			return;
		}

// 2. 날짜로 실제 조회 시작
		LocalDate date;
		try {
			date = LocalDate.parse(dateStr);
		} catch (Exception e) {
// 잘못된 날짜면 오류 메시지나 다시 선택 화면
			request.setAttribute("msg", "날짜 형식이 잘못됐습니다.");
			request.getRequestDispatcher("dateSelect.jsp").forward(request, response);
			return;
		}

		List<StudentAttendance> list = MemberDAO.getModel().getAttendanceByDate(date);
		Optional<StudentAttendance> earliest = list.stream().filter(a -> a.getCheckIn() != null)
				.filter(a -> !a.getCheckIn().toLocalTime().equals(LocalTime.MIDNIGHT))
				.min(Comparator.comparing(StudentAttendance::getCheckIn));
		request.setAttribute("selectedDate", date);

		if (earliest.isPresent()) {
			request.setAttribute("best", earliest.get());
			request.getRequestDispatcher("/WEB-INF/views/best.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", date + "에는 아직 출석한 사람이 없습니다.");
			request.getRequestDispatcher("/WEB-INF/views/fail.jsp").forward(request, response);
		}
	}

}