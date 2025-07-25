package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.MemberDAO;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    static final MemberDAO memberDAO = MemberDAO.getModel();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 현재 세션 가져오기
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 완전 초기화
        }

        // 로그아웃 후 로그인 페이지나 메인 페이지로 이동
        response.sendRedirect("/fisa_JPAProject2/"); 
    }
}
