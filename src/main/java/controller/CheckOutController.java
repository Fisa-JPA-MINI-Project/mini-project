package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.MemberDAO;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Servlet implementation class CheckOutController
 */
@WebServlet("/checkout")
public class CheckOutController extends HttpServlet {
	static final MemberDAO memberDAO = MemberDAO.getModel();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		memberDAO.getCheckOut((Long) session.getAttribute("userId"),(String)session.getAttribute("userName"));

        response.sendRedirect("userinfo");
		
	}

}
