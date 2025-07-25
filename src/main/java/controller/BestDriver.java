package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

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
    	        // 1. 날짜 옵션 목록 DB에서 select
    	        List<LocalDate> dateList = MemberDAO.getModel().getAttendanceDates();
    	        request.setAttribute("dateList", dateList);
    	        // 2. 무조건 forward! (redirect 대신)
    	        request.getRequestDispatcher("/WEB-INF/views/dateSelect.jsp").forward(request, response);
    	        return;
    	    }

    	    // 2. date 값이 있을 때만 조회 로직
    	    LocalDate date;
    	    try {
    	        date = LocalDate.parse(dateStr);
    	    } catch (Exception e) {
    	        // 잘못된 날짜면 다시 날짜 선택화면으로 돌림
    	        List<LocalDate> dateList = MemberDAO.getModel().getAttendanceDates();
    	        request.setAttribute("dateList", dateList);
    	        request.setAttribute("msg", "날짜 형식이 잘못됐습니다.");
    	        request.getRequestDispatcher("/WEB-INF/views/dateSelect.jsp").forward(request, response);
    	        return;
    	    }

    	    List<StudentAttendance> list = MemberDAO.getModel().getAttendanceByDate(date);
    	    Optional<StudentAttendance> earliest = list.stream()
    	        .filter(a -> a.getCheckIn() != null)
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
