package com.codegym.lqhanh.topdev_clone.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginSuccessFilter")
public class LoginSuccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Object userObj = req.getSession().getAttribute("user");
        if (userObj != null)
            res.sendRedirect(req.getContextPath() + "/admin?site=dashboard");
        else
            chain.doFilter(request, response);
    }
}
