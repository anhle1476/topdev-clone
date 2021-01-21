package com.codegym.lqhanh.topdev_clone.models.modelcontainer;

import com.codegym.lqhanh.topdev_clone.models.Post;

import java.util.ArrayList;
import java.util.List;

public class PostList {
    private List<Post> posts = new ArrayList<>();

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public Post getPostByIndex(int index) {
        return posts.get(index);
    }

    public List<Post> getFirstNthPosts(int n) {
        return getPostsWithinRange(0, n);
    }

    public List<Post> getPostsFromIndex(int n) {
        return getPostsWithinRange(n, posts.size());
    }

    public List<Post> getPostsWithinRange(int from, int to) {
        List<Post> result = new ArrayList<>();
        for (int i = from; i < to && i < posts.size(); i++) {
            result.add(posts.get(i));
        }
        return result;
    }

    public String getFirstPostCategory(CategoryMap map) {
        if (posts.isEmpty())
            return "No category";
        int firstId = posts.get(0).getId();
        return map.getCategoryById(firstId).getName();
    }
}
