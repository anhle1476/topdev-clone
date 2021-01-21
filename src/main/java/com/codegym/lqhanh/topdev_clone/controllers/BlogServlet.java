package com.codegym.lqhanh.topdev_clone.controllers;

import com.codegym.lqhanh.topdev_clone.models.Post;
import com.codegym.lqhanh.topdev_clone.models.modelcontainer.PostListingData;
import com.codegym.lqhanh.topdev_clone.services.PostsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
    PostsService postsService = new PostsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String site = request.getParameter("site");
        if (site == null) site = "home";
        switch (site) {
            case "home":
                forwardToHomeBlog(request, response);
                break;
            case "post":
                forwardToPost(request, response);
                break;
            default:
                forwardToErrorPage(request, response);
        }
    }


    protected void forwardToHomeBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostListingData homepagePost = postsService.getHomepagePost();
        request.setAttribute("homepage-post", homepagePost);
        forwardToView(request, response, "/views/ui/index.jsp");
    }

    private void forwardToPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int postId = Integer.parseInt(request.getParameter("post-id"));
            Post post = postsService.getPostById(postId);
            if (post == null)
                throw new NullPointerException("No post exists");
            request.setAttribute("post", post);
            forwardToView(request, response, "/views/ui/post.jsp");
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("Failed: " + e.getMessage());
            forwardToErrorPage(request, response);
        }
    }

    private void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToView(request, response, "/views/ui/error.jsp");
    }

    private void forwardToView(HttpServletRequest request, HttpServletResponse response, String viewFile) throws ServletException, IOException {
        request.getRequestDispatcher(viewFile).forward(request, response);
    }
}
