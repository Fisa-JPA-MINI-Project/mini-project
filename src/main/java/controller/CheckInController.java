package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.MemberDAO;

import java.io.IOException;

@WebServlet("/checkin")
public class CheckInController extends HttpServlet {
    static final MemberDAO memberDAO = MemberDAO.getModel();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session == null) {
            resp.sendRedirect("404.html");
            return;
        }
        Long id = (Long) session.getAttribute("userId");
        String name = (String) session.getAttribute("userName");

        if(id == null || name == null){
            resp.sendRedirect("view/404.html");
            return;
        }

        memberDAO.getCheckIn(id, name);

        // 출근 처리 후 userinfo GET으로 리다이렉트
        resp.sendRedirect("userinfo");
    }
}
