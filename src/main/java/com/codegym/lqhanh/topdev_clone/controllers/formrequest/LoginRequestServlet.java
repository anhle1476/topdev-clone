package com.codegym.lqhanh.topdev_clone.controllers.formrequest;

import com.codegym.lqhanh.topdev_clone.models.User;
import com.codegym.lqhanh.topdev_clone.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginRequestServlet", value = "/login/request")
public class LoginRequestServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = userService.loginAndFetchUser(email, password);
            if (user != null) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/admin?site=dashboard");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            request.getSession().setAttribute("login-failed", true);
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
