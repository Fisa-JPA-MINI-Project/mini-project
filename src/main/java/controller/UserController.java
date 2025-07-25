package controller;

import java.io.IOException;
import java.time.LocalDateTime;
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
	protected void service(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    HttpSession session = request.getSession();
	    request.setCharacterEncoding("UTF-8");

	    String name = request.getParameter("username");
	    if(name == null) {
	    	name = (String) session.getAttribute("userName");
	    }
	    StudentAttendance sa = null; // 여기서 초기화

	    try {
	         sa = memberDAO.getStudent(name);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    if (sa == null && session.getAttribute("userId") == null) {
	        request.setAttribute("name", name);
	        request.getRequestDispatcher("/WEB-INF/views/loginfail.jsp").forward(request, response);
	        return;
	    }
	    if (sa.getCheckIn() != null && sa.getCheckIn().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()) {
	    	request.setAttribute("checkintime", sa.getCheckIn());
	    	request.setAttribute("checkouttime", sa.getCheckOut());
	    }else {
	      	session.setAttribute("checkintime", null);
	    	session.setAttribute("checkouttime", null);
	    }
	    if(session.getAttribute("userId")== null) {
	    session.setAttribute("userId", sa.getEmpno()); 
	    session.setAttribute("userName", sa.getName()); 
	    }
	    request.getRequestDispatcher("/WEB-INF/views/usercheckin.jsp").forward(request, response); 
	}

	    
	
	}
