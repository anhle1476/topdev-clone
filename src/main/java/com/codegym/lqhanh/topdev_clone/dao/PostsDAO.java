package com.codegym.lqhanh.topdev_clone.dao;

import com.codegym.lqhanh.topdev_clone.models.Category;
import com.codegym.lqhanh.topdev_clone.models.Post;
import com.codegym.lqhanh.topdev_clone.models.Tag;
import com.codegym.lqhanh.topdev_clone.models.User;
import com.codegym.lqhanh.topdev_clone.models.modelcontainer.PostListingData;
import com.codegym.lqhanh.topdev_clone.services.StringUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PostsDAO {

    public void addPost(Post post) throws SQLException {
        try (
                Connection con = DAOUtils.getConnection();
                CallableStatement statement = con.prepareCall("{CALL addPost(?, ?, ?, ?, ?, ?, ?)}")
        ) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getSummary());
            statement.setString(3, post.getImgLink());
            statement.setString(4, post.getContent());
            statement.setInt(5, post.getAuthor().getId());
            statement.setString(6, parseTagList(post));
            statement.setString(7, parseCategoryList(post));
            statement.execute();
        }
    }

    private String parseTagList(Post post) {
        return post.getTagList()
                .stream()
                .map(Tag::getName)
                .collect(Collectors.joining(","));
    }

    private String parseCategoryList(Post post) {
        return post.getCategories()
                .stream()
                .map(category -> category.getId() + "")
                .collect(Collectors.joining(","));
    }

    public Post getPostById(int id) throws SQLException {
        try (
                Connection con = DAOUtils.getConnection();
                CallableStatement statement = con.prepareCall("{CALL getPostDetailsById(?)}")
        ) {
            statement.setInt(1, id);
            // GET POST DETAILS
            ResultSet postDetailSet = statement.executeQuery();
            if (!postDetailSet.next()) return null;
            Post postWithContent = extractPostWithContent(postDetailSet);
            // ADD CATEGORIES
            statement.getMoreResults();
            ResultSet categorySet = statement.getResultSet();
            addCategoriesToPost(categorySet, postWithContent);
            // ADD TAGS
            statement.getMoreResults();
            ResultSet tagSet = statement.getResultSet();
            addTagsToPost(tagSet, postWithContent);

            return postWithContent;
        }
    }

    private Category extractCategory(ResultSet results) throws SQLException {
        int id = results.getInt("category_id");
        String name = results.getString("name");
        int parentId = results.getInt("parent_id");
        return new Category(id, name, parentId);
    }

    private Tag extractTag(ResultSet results) throws SQLException {
        int id = results.getInt("tag_id");
        String name = results.getString("name");
        return new Tag(id, name);
    }

    public PostListingData getHomePageData() throws SQLException {
        try (
                Connection con = DAOUtils.getConnection();
                CallableStatement stm = con.prepareCall("{ CALL getHomePagePosts()}")
        ) {
            PostListingData data = new PostListingData();

            // fetch newest posts
            ResultSet newestPostSet = stm.executeQuery();
            while (newestPostSet.next()) {
                Post newestPost = extractBasicPost(newestPostSet);
                data.addPost("newest", newestPost);
            }

            // fetch main category posts
            int categoryCount = 1;
            while (stm.getMoreResults()) {
                ResultSet categoryPostSet = stm.getResultSet();
                String categoryKey = "cat-" + categoryCount++;
                while (categoryPostSet.next()) {
                    Post categoryPost = extractPostWithCategoryId(categoryPostSet);
                    data.addPost(categoryKey, categoryPost);
                }
            }

            return data;
        }
    }

    public List<Post> getApprovedPostsUnderUserAuthorized(int userId) throws SQLException {
        try (
                Connection con = DAOUtils.getConnection();
                CallableStatement statement = con.prepareCall("{CALL getApprovedPostsUnderUserAuthorized(?)}")
        ) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                Post post = extractPostOfAdminPage(resultSet);
                posts.add(post);
            }
            return posts;
        }
    }

    private Post extractPostWithContent(ResultSet results) throws SQLException {
        String content = results.getString("content");
        String imgLink = results.getString("img_link");
        User author = parseAuthor(results);

        return extractBasicPostBuilder(results)
                .setContent(content)
                .setAuthor(author)
                .setImgLink(imgLink)
                .build();
    }

    private Post extractPostWithCategoryId(ResultSet results) throws SQLException {
        String imgLink = results.getString("img_link");
        int categoryId = results.getInt("category_id");
        Category category = new Category(categoryId);

        return extractBasicPostBuilder(results)
                .addCategory(category)
                .setImgLink(imgLink)
                .build();
    }

    private Post extractPostOfAdminPage(ResultSet results) throws SQLException {
        String summary = results.getString("summary");
        User author = parseAuthor(results);
        Date lastUpdatedDate = parseDate(results, "last_updated");
        boolean isApproved = results.getBoolean("is_approved");

        return extractBasicPostBuilder(results)
                .setSummary(summary)
                .setLastUpdated(lastUpdatedDate)
                .setApproved(isApproved)
                .setAuthor(author)
                .build();
    }

    private Post extractBasicPost(ResultSet results) throws SQLException {
        String imgLink = results.getString("img_link");
        return extractBasicPostBuilder(results)
                .setImgLink(imgLink)
                .build();
    }

    private Post.PostBuilder extractBasicPostBuilder(ResultSet results) throws SQLException {
        int id = results.getInt("id");
        String title = results.getString("title");
        Date creationDate = parseDate(results, "created");

        return new Post.PostBuilder(title)
                .setId(id)
                .setCreationDate(creationDate);
    }

    private User parseAuthor(ResultSet results) throws SQLException {
        int authorId = results.getInt("author");
        String authorName = results.getString("authorName");
        return new User(authorId, authorName);
    }

    private Date parseDate(ResultSet results, String columnName) throws SQLException {
        String lastUpdatedStr = results.getString(columnName);
        return StringUtils.parseDateFromDatabase(lastUpdatedStr);
    }

    private void addCategoriesToPost(ResultSet categorySet, Post postWithContent) throws SQLException {
        while (categorySet.next()) {
            Category category = extractCategory(categorySet);
            postWithContent.addCategory(category);
        }
    }

    private void addTagsToPost(ResultSet tagSet, Post postWithContent) throws SQLException {
        while (tagSet.next()) {
            Tag category = extractTag(tagSet);
            postWithContent.addTag(category);
        }
    }
}
