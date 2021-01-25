package com.codegym.lqhanh.topdev_clone.controllers;

import com.codegym.lqhanh.topdev_clone.models.Post;
import com.codegym.lqhanh.topdev_clone.models.User;
import com.codegym.lqhanh.topdev_clone.services.PostsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/editor")
public class EditorServlet extends HttpServlet {
    private final PostsService postsService = new PostsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String edit = request.getParameter("edit");
        if (edit == null)
            edit = "";
        switch (edit) {
            case "new-post":
                forwardToNewPost(request, response);
                break;
            case "exists-post":
                forwardToExistsPost(request, response);
                break;
            default:
                request.getRequestDispatcher("/views/ui/error.jsp").forward(request, response);
        }
    }


    private void forwardToNewPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("post-id", 0);
        request.getRequestDispatcher("/views/admin/admin-editor.jsp").forward(request, response);
    }

    private void forwardToExistsPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ok");
        int postId = Integer.parseInt(request.getParameter("post-id"));
        System.out.println("edit post id: " + postId);
        User requestUser = (User) request.getSession().getAttribute("user");
        System.out.println("user: " + requestUser.getId());
        Post editingPost = postsService.getEditingPost(postId, requestUser);
        System.out.println("post: " + editingPost);
        request.setAttribute("post-id", postId);
        request.setAttribute("post-data", editingPost);
        request.getRequestDispatcher("/views/admin/admin-editor.jsp").forward(request, response);
    }
}
