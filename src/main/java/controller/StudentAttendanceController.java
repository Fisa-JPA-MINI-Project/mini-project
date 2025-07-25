package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MemberDAO;
import model.entity.StudentAttendance;
import model.repository.StudentAttendanceRepository;

@WebServlet("/attendance/list")
public class StudentAttendanceController extends HttpServlet {
	static final MemberDAO memberDAO = MemberDAO.getModel();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<StudentAttendance> list = memberDAO.getMyAttendance();
			List<String> dateList = new ArrayList<>();
			List<Integer> hourList = new ArrayList<>();

			for (StudentAttendance s : list) {
				if (s.getTrainDate() != null) {
					dateList.add(s.getTrainDate().toString());
				} else {
					dateList.add("미기록"); // 또는 continue;
				}

				if (s.getCheckIn() != null) {
					hourList.add(s.getCheckIn().getHour());
				} else {
					hourList.add(0); // 또는 continue;
				}
			}
			
			request.setAttribute("dateList", dateList);
			request.setAttribute("hourList", hourList);

			request.getRequestDispatcher("/WEB-INF/views/attendance.jsp").forward(request, response);
		} finally {
			
		}

	}
	}
