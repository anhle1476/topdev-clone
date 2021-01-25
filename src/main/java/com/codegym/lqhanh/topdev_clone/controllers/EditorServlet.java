package com.codegym.lqhanh.topdev_clone.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/editor")
public class EditorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String edit = request.getParameter("edit");
        if (edit == null)
            edit = "new-post";
        switch (edit) {
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
