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
import java.util.Date;
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

//    public List<Post> getNewestPostThumbnail() throws SQLException {
//        try (
//                Connection connection = DAOUtils.getConnection();
//                Statement statement = connection.createStatement()
//        ) {
//            ResultSet results = statement.executeQuery("SELECT id, title, summary, img_link, last_updated " +
//                    "FROM posts " +
//                    "ORDER BY last_updated DESC " +
//                    "LIMIT 20;");
//            List<Post> posts = new ArrayList<>();
//            while (results.next()) {
//                Post currPost = extractBasicPostFromResultSet(results);
//                posts.add(currPost);
//            }
//            return posts;
//        }
//    }

    public Post getPostById(int id) throws SQLException {
        try (
                Connection con = DAOUtils.getConnection();
                CallableStatement statement = con.prepareCall("{CALL getPostDetailsById(?)}")
        ) {
            statement.setInt(1, id);
            // GET POST DETAILS
            ResultSet postDetailSet = statement.executeQuery();
            if (!postDetailSet.next()) return null;
            Post postWithContent = extractPostWithContentFromResultSet(postDetailSet);
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

    private Category extractCategoryFromResultSet(ResultSet results) throws SQLException {
        int id = results.getInt("category_id");
        String name = results.getString("name");
        int parentId = results.getInt("parent_id");
        return new Category(id, name, parentId);
    }

    private Tag extractTagFromResultSet(ResultSet results) throws SQLException {
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
                Post newestPost = extractBasicPostFromResultSet(newestPostSet);
                data.addPost("newest", newestPost);
            }

            // fetch main category posts
            int categoryCount = 1;
            while (stm.getMoreResults()) {
                ResultSet categoryPostSet = stm.getResultSet();
                String categoryKey = "cat-" + categoryCount++;
                while (categoryPostSet.next()) {
                    Post categoryPost = extractPostWithCategoryIdFromResultSet(categoryPostSet);
                    data.addPost(categoryKey, categoryPost);
                }
            }

            return data;
        }
    }

    private Post extractPostWithContentFromResultSet(ResultSet results) throws SQLException {
        String content = results.getString("content");

        int authorId = results.getInt("author");
        String authorName = results.getString("name");
        User user = new User(authorId, authorName);

        return extractBasicPostBuilderFromResultSet(results)
                .setContent(content)
                .setAuthor(user)
                .build();
    }

    private Post extractPostWithCategoryIdFromResultSet(ResultSet results) throws SQLException {
        int categoryId = results.getInt("category_id");
        Category category = new Category(categoryId);

        return extractBasicPostBuilderFromResultSet(results)
                .addCategory(category)
                .build();
    }

    private Post extractBasicPostFromResultSet(ResultSet results) throws SQLException {
        return extractBasicPostBuilderFromResultSet(results).build();
    }

    private Post.PostBuilder extractBasicPostBuilderFromResultSet(ResultSet results) throws SQLException {
        int id = results.getInt("id");
        String title = results.getString("title");
        String imgLink = results.getString("img_link");
        String creationDateStr = results.getString("created");
        Date creationDate = StringUtils.parseDateFromDatabase(creationDateStr);

        return new Post.PostBuilder(title)
                .setId(id)
                .setImgLink(imgLink)
                .setCreationDate(creationDate);
    }


    private void addCategoriesToPost(ResultSet categorySet, Post postWithContent) throws SQLException {
        while (categorySet.next()) {
            Category category = extractCategoryFromResultSet(categorySet);
            postWithContent.addCategory(category);
        }
    }

    private void addTagsToPost(ResultSet tagSet, Post postWithContent) throws SQLException {
        while (tagSet.next()) {
            Tag category = extractTagFromResultSet(tagSet);
            postWithContent.addTag(category);
        }
    }
}
