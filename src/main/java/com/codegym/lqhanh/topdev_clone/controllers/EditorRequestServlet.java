package com.codegym.lqhanh.topdev_clone.controllers;

import com.codegym.lqhanh.topdev_clone.models.Notification;
import com.codegym.lqhanh.topdev_clone.models.Post;
import com.codegym.lqhanh.topdev_clone.models.Tag;
import com.codegym.lqhanh.topdev_clone.models.User;
import com.codegym.lqhanh.topdev_clone.models.modelcontainer.CategoryMap;
import com.codegym.lqhanh.topdev_clone.services.PostsService;
import com.codegym.lqhanh.topdev_clone.services.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@WebServlet("/edit-post")
public class EditorRequestServlet extends HttpServlet {
    private final PostsService postsService = new PostsService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("to post edit");

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        int postId = Integer.parseInt(request.getParameter("post-id"));
        if (postId == 0)
            createNewPost(request, response);
        else
            editExistsPost(request, response);
    }


    private void createNewPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Post newPost = parsePostFromEditor(request);
        boolean result = postsService.addNewPost(newPost);
        String notificationType = result ? Notification.SUCCESS : Notification.WARNING;
        String message = result ? "Thêm bài viết thành công." : "Thêm bài viết thất bại!";
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification(notificationType, message));
        request.getSession().setAttribute("notifications", notifications);
        response.sendRedirect(request.getContextPath() + "/admin?site=dashboard");
    }


    private void editExistsPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Post post = parsePostFromEditor(request);
        User user = (User) request.getSession().getAttribute("user");
        boolean result = postsService.editPost(post, user);

        String notificationType = result ? Notification.SUCCESS : Notification.WARNING;
        String message = result ? "Chỉnh sửa bài viết thành công" : "Chỉnh sửa bài viết thất bại";
        List<Notification> notifications = Arrays.asList(new Notification(notificationType, message));

        request.getSession().setAttribute("notifications", notifications);
        response.sendRedirect(request.getContextPath() + "/admin?site=current-posts");
    }

    private Post parsePostFromEditor(HttpServletRequest request) {
        CategoryMap categoryMap = (CategoryMap) request.getSession().getAttribute("categories");
        Set<Integer> categoryIds = categoryMap.getCategoryIds();

        int postId = Integer.parseInt(request.getParameter("post-id"));
        String title = request.getParameter("title");
        String imgLink = request.getParameter("img-link");
        String summary = request.getParameter("summary");
        String content = StringUtils.parsePostContent(request.getParameter("content"));
        List<String> tagNames = StringUtils.parseTagsInput(request.getParameter("tags"));

        List<Integer> postCategoryIds = new ArrayList<>();
        for (Integer id : categoryIds) {
            String categoryParam = "cat-" + id;
            if (request.getParameter(categoryParam) != null)
                postCategoryIds.add(id);
        }

        User author = new User(1, "Root User");

        Post post = new Post.PostBuilder(title)
                .setId(postId)
                .setImgLink(imgLink)
                .setSummary(summary)
                .setContent(content)
                .setAuthor(author)
                .build();

        for (String tagName : tagNames) {
            post.addTag(new Tag(tagName));
        }

        for (Integer categoryId : postCategoryIds) {
            post.addCategory(categoryMap.getCategoryById(categoryId));
        }
        return post;
    }

}
