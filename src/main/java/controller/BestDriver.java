package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.MemberDAO;
import model.entity.StudentAttendance;
import model.repository.StudentAttendanceRepository;

/**
 * Servlet implementation class BestDriver
 * 오늘 어제 가장 빨리온 사람을 
 * 오늘 출석한 사람이 없으면 아직 출석한 사람이 없습니다.
 * 출석하기 퇴근하기 버튼도 누르고 
 * 없으면 failview
 */
@WebServlet("/Best")
public class BestDriver extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String dateStr = request.getParameter("date");
    	LocalDate date;
    	if (dateStr != null && !dateStr.isEmpty()) {
            date = LocalDate.parse(dateStr); // "2025-07-07" 식
        } else {
            date = LocalDate.now();
        }
    	List<StudentAttendance> list = MemberDAO.getModel().getAttendanceByDate(date);
    	System.out.println(list);
        // DAO 호출로 오늘 출석자 리스트 조회
//        List<StudentAttendance> list =
//            MemberDAO.getModel().getTodayAttendance();

        // 스트림/람다로 제일 빨리 출석한 사람 찾기
        Optional<StudentAttendance> earliest = list.stream()
            .filter(a -> a.getCheckIn() != null)
            .min(Comparator.comparing(StudentAttendance::getCheckIn));

        if (earliest.isPresent()) {
            request.setAttribute("best", earliest.get());
            request.setAttribute("selectedDate", date);
            request.getRequestDispatcher("/WEB-INF/views/best.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", date + "에는 아직 출석한 사람이 없습니다.");
            request.setAttribute("selectedDate", date);
            request.getRequestDispatcher("/WEB-INF/views/fail.jsp").forward(request, response);
        }
    }

    // ... doPost 등


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
