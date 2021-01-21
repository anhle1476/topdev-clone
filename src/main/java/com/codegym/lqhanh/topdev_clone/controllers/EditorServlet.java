package com.codegym.lqhanh.topdev_clone.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin-editor")
public class EditorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String writing = request.getParameter("writing");
        if (writing == null)
            writing = "new-post";
        switch (writing) {
            case "new-post":
                forwardToNewPost(request, response);
                break;
            default:
                request.getRequestDispatcher("/views/ui/error.jsp").forward(request, response);
        }
    }

    private void forwardToNewPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("post-id", 0);
        request.getRequestDispatcher("/views/admin/admin-editor.jsp").forward(request, response);
    }
}
