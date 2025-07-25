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
import jakarta.servlet.http.HttpSession;
import model.MemberDAO;
import model.entity.StudentAttendance;

@WebServlet("/userinfo")
public class UserController extends HttpServlet {
	static final MemberDAO memberDAO = MemberDAO.getModel();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    request.setCharacterEncoding("UTF-8");

	    String name = request.getParameter("username");
	    StudentAttendance sa = null; // 여기서 초기화

	    try {
	        StudentAttendance sa = memberDAO.getStudent(name);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    if (sa == null) {
	        request.setAttribute("name", name);
	        request.getRequestDispatcher("/WEB-INF/views/loginfail.jsp").forward(request, response);
	        return;
	    }

	    HttpSession session = request.getSession();
	    session.setAttribute("username", sa.getName());
	    session.setAttribute("userid", sa.getId());

	    request.getRequestDispatcher("/WEB-INF/views/usercheckin.jsp").forward(request, response); 
	}

	    
	
	}
