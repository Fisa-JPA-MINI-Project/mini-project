package controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.MemberDAO;
import model.entity.StudentAttendance;

@WebServlet("/Attendance")
public class Attendance extends HttpServlet {

	static final MemberDAO memberDAO = MemberDAO.getModel();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String name = null;
		Long id = null;

//		if (session != null) {
//		    name = (String) session.getAttribute("username"); // ✔️ 변수 선언 없이 대입만 함
//		    id = (Long) session.getAttribute("userid");
//
//		    if (name != null && id != null) {
//		        // name, id 정상적으로 사용 가능
//		    } else {
//		        //response.sendRedirect("login.jsp");
//		    	System.out.println("answ-");
//		        return;
//		    }
//		} else {
//		    //response.sendRedirect("login.jsp");
//			System.out.println("answp");
//		    return;
//		}
		
		name="홍혜원";

		List<StudentAttendance> list = memberDAO.getMyAttendance(name);
		List<String> dateList = new ArrayList<>();
		List<Double> hourList = new ArrayList<>();

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d");

		for (StudentAttendance s : list) {
			if (s.getTrainDate() != null) {
				dateList.add(s.getTrainDate().format(dateFormatter));
			} else {
				dateList.add("미기록"); // 또는 continue;
			}

			if (s.getCheckIn() != null) {
				int hour = s.getCheckIn().getHour();
				int minute = s.getCheckIn().getMinute();
				double hourValue = hour + minute / 60.0;
				hourList.add(hourValue);
			} else {
				hourList.add(null);
			}
		}
		System.out.println("dateList size: " + (dateList != null ? dateList.size() : "null"));
		System.out.println("hourList size: " + (hourList != null ? hourList.size() : "null"));
		System.out.println("dateList: " + dateList);
		System.out.println("hourList: " + hourList);

		request.setAttribute("dateList", dateList);
		request.setAttribute("hourList", hourList);

		request.getRequestDispatcher("/WEB-INF/views/attendance.jsp").forward(request, response);
	}

}
