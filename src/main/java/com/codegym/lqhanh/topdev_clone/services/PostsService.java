package com.codegym.lqhanh.topdev_clone.services;

import com.codegym.lqhanh.topdev_clone.dao.PostsDAO;
import com.codegym.lqhanh.topdev_clone.models.Post;
import com.codegym.lqhanh.topdev_clone.models.modelcontainer.PostListingData;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class PostsService {
    PostsDAO postsDAO = new PostsDAO();

    public boolean addNewPost(Post post) {
        try {
            postsDAO.addPost(post);
            return true;
        } catch (SQLException e) {
            System.out.println("Add post failed: " + e.getMessage());
            return false;
        }
    }

//    public List<Post> getNewestPosts() {
//        try {
//            return postsDAO.getNewestPostThumbnail();
//        } catch (SQLException e) {
//            System.out.println("Fetch newest post failed: " + e.getMessage());
//            return Collections.emptyList();
//        }
//    }

    public Post getPostById(int id) {
        try {
            return postsDAO.getPostById(id);
        } catch (SQLException e) {
            System.out.println("Fetch post id " + id + " failed: " + e.getMessage());
            return null;
        }
    }

    public PostListingData getHomepagePost() {
        try {
            return postsDAO.getHomePageData();
        } catch (SQLException e) {
            System.out.println("Home page post failed: " + e.getMessage());
            return null;
        }
    }

    public List<Post> getApprovedPostsByUserId(int id) {
        try {
            return postsDAO.getApprovedPostsUnderUserAuthorized(id);
        } catch (SQLException e) {
            System.out.println("Fetch posts failed: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
