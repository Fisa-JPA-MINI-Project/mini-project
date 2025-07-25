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
import model.entity.StudentAttendance;

/**
 * Servlet implementation class Attendance
 */
@WebServlet("/Attendance")
public class Attendance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EntityManagerFactory emf;

	@Override
	public void init() throws ServletException {
		emf = Persistence.createEntityManagerFactory("dbinfo"); // persistence.xml 이름
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = null;
		try {
			em = emf.createEntityManager();

			// "홍혜원"의 출석 데이터 조회
			List<StudentAttendance> list = em
					.createQuery("SELECT s FROM StudentAttendance s WHERE s.name = :name", StudentAttendance.class)
					.setParameter("name", "홍혜원").getResultList();
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
			if (em != null)
				em.close();
		}
	}

	@Override
	public void destroy() {
		if (emf != null)
			emf.close();
	}
}
