package controller;

import java.io.IOException;
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
import model.repository.StudentAttendanceRepository;

@WebServlet("/attendance/list")
public class StudentAttendanceController extends HttpServlet {

    private EntityManagerFactory emf;
    private StudentAttendanceRepository repository;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("dbinfo");
        EntityManager em = emf.createEntityManager();
        repository = new StudentAttendanceRepository();
        repository.getClass(); // 트릭으로 초기화 유도
        em.getTransaction().begin();
        repository = new StudentAttendanceRepository();
        em.getTransaction().commit();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();
        StudentAttendanceRepository repo = new StudentAttendanceRepository();
        em.getTransaction().begin();
        List<StudentAttendance> list = em
                .createQuery("SELECT s FROM StudentAttendance s", StudentAttendance.class)
                .getResultList();
        System.out.println("attList size: " + list.size());
        for (StudentAttendance s : list) {
            System.out.println("출석 데이터: " + s);
        }
        em.getTransaction().commit();
        em.close();

        request.setAttribute("attList", list);
        request.getRequestDispatcher("/WEB-INF/views/attendance.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        if (emf != null) emf.close();
    }
}
