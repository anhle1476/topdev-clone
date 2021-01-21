package com.codegym.lqhanh.topdev_clone.models.modelcontainer;

import com.codegym.lqhanh.topdev_clone.models.Post;

import java.util.HashMap;
import java.util.Map;

public class PostListingData {
    private final Map<String, PostList> postsMap = new HashMap<>();

    public void addPost(String key, Post post) {
        if (!postsMap.containsKey(key))
            postsMap.put(key, new PostList());

        getPostListByKeyword(key).addPost(post);
    }

    public PostList getPostListByKeyword(String key) {
        return postsMap.get(key);
    }
}
