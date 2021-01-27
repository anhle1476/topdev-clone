package com.codegym.lqhanh.topdev_clone.controllers;

import com.codegym.lqhanh.topdev_clone.services.PostsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminRequestServlet", value = "/admin/request")
public class AdminRequestServlet extends HttpServlet {
    public static final String RESPONSE_JSON = "{\"request\":\"%s\",\"postId\":%d,\"isAccepted\":%s}";
    private final PostsService postsService = new PostsService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "";
        switch (action) {
            case "approve-post":
                handleApprovePostRequest(request, response);
                break;
            case "delete-post":
                handleDeletePostRequest(request, response);
                break;
            default:
                handleInvalidRequest(response);
        }

    }

    private void handleApprovePostRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int postId = parsePostId(request);
            boolean result = true;
            sendResponse(response, "approve", postId, result);
        } catch (Exception e) {
            handleInvalidRequest(response);
        }
    }

    private void handleDeletePostRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int postId = parsePostId(request);
            boolean result = true;
            sendResponse(response, "delete", postId, result);
        } catch (Exception e) {
            handleInvalidRequest(response);
        }
    }

    private void handleInvalidRequest(HttpServletResponse response) throws IOException {
        sendResponse(response, "invalid", -1, false);
    }

    private void sendResponse(HttpServletResponse response, String requestType, int postId, boolean result) throws IOException {
        PrintWriter writer = response.getWriter();
        String JSONResponse = String.format(RESPONSE_JSON, requestType, postId, result);
        writer.write(JSONResponse);
    }

    private int parsePostId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("postId"));
    }
}
