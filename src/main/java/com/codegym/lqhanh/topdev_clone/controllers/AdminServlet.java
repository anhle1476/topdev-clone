package com.codegym.lqhanh.topdev_clone.controllers;

import com.codegym.lqhanh.topdev_clone.models.Post;
import com.codegym.lqhanh.topdev_clone.models.User;
import com.codegym.lqhanh.topdev_clone.services.PostsService;
import com.codegym.lqhanh.topdev_clone.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final PostsService postsService = new PostsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String siteParam = request.getParameter("site");
        if (siteParam == null)
            siteParam = "dashboard";
        switch (siteParam) {
            case "dashboard":
                forwardToDashboard(request, response);
                break;
            case "current-posts":
                forwardToCurrentPosts(request, response);
                break;
            case "unapproved-posts":
                forwardToUnapprovedPosts(request, response);
                break;
        }
    }

    private void forwardToDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ServletUtils.getUserFromSession(request);
        String postStatus = userService.getUserPostStatus(user.getId());
        request.setAttribute("postStatus", postStatus);
        request.getRequestDispatcher("/views/admin/admin-home.jsp").forward(request, response);
    }

    private void forwardToCurrentPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ServletUtils.getUserFromSession(request);
        List<Post> postList = postsService.getApprovedPostsByUserId(user.getId());
        request.setAttribute("postList", postList);
        request.getRequestDispatcher("/views/admin/admin-current-posts.jsp").forward(request, response);
    }


    private void forwardToUnapprovedPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ServletUtils.getUserFromSession(request);
        List<Post> postList = postsService.getUnapprovedPostsByUserId(user.getId());
        request.setAttribute("postList", postList);
        request.getRequestDispatcher("/views/admin/admin-unapproved-posts.jsp").forward(request, response);
    }
}
