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
            statement.setString(6, post.getFormattedTagList());
            statement.setString(7, parseCategoryList(post));
            statement.execute();
        }
    }

    public boolean editPost(Post post, int requestUserId) throws SQLException {
        try (
                Connection con = DAOUtils.getConnection();
                CallableStatement statement = con.prepareCall("{CALL editPost(?, ?, ?, ?, ?, ?, ?, ?)}")
        ) {
            statement.setInt(1, requestUserId);
            statement.setInt(2, post.getId());
            statement.setString(3, post.getTitle());
            statement.setString(4, post.getSummary());
            statement.setString(5, post.getImgLink());
            statement.setString(6, post.getContent());
            statement.setString(7, post.getFormattedTagList());
            statement.setString(8, parseCategoryList(post));
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next())
                throw new SQLException("Can not fetch result");
            else
                return resultSet.getBoolean("result");
        }
    }

    public boolean approvePost(int postId, int requestUserId) throws SQLException {
        return executePostTableAction(postId, requestUserId, "{CALL approvePost(?, ?)}");
    }

    public boolean deletePost(int postId, int requestUserId) throws SQLException {
        return executePostTableAction(postId, requestUserId, "{CALL deletePostById(?, ?)}");
    }

    public boolean executePostTableAction(int postId, int requestUserId, String query) throws SQLException {
        try (
                Connection con = DAOUtils.getConnection();
                CallableStatement statement = con.prepareCall(query)
        ) {
            statement.setInt(1, requestUserId);
            statement.setInt(2, postId);

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next())
                throw new SQLException("Can not fetch result");
            else
                return resultSet.getBoolean("result");
        }
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
            if (!postDetailSet.next())
                throw new SQLException("No post exists");
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
        int id = parseCategoryId(results);
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
        return getPostListUnderUserAuthorized(userId, "{CALL getApprovedPostsUnderUserAuthorized(?)}");
    }

    public List<Post> getUnapprovedPostsUnderUserAuthorized(int userId) throws SQLException {
        return getPostListUnderUserAuthorized(userId, "{CALL getUnapprovedPostsUnderUserAuthorized(?)}");
    }

    public List<Post> getPostListUnderUserAuthorized(int userId, String query) throws SQLException {
        try (
                Connection con = DAOUtils.getConnection();
                CallableStatement statement = con.prepareCall(query)
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

    public Post getEditingPost(int postId, int requestUser) throws SQLException {
        try (
                Connection con = DAOUtils.getConnection();
                CallableStatement statement = con.prepareCall("{CALL getPostToEdit(?, ?)}");
        ) {
            statement.setInt(1, postId);
            statement.setInt(2, requestUser);

            ResultSet results = statement.executeQuery();
            if (!results.next()) throw new SQLException("No post exists");
            Post editingPost = extractEditingPost(results);

            // ADD CATEGORIES
            statement.getMoreResults();
            ResultSet categorySet = statement.getResultSet();
            while (categorySet.next()) {
                int categoryId = parseCategoryId(categorySet);
                Category category = new Category(categoryId);
                editingPost.addCategory(category);
            }
            // ADD TAGS
            statement.getMoreResults();
            ResultSet tagSet = statement.getResultSet();
            addTagsToPost(tagSet, editingPost);

            return editingPost;
        }
    }

    private Post extractEditingPost(ResultSet results) throws SQLException {
        String content = parseContent(results);
        String imgLink = parseImgLink(results);
        String summary = parseSummary(results);

        return extractBasicPostBuilder(results)
                .setSummary(summary)
                .setContent(content)
                .setImgLink(imgLink)
                .build();
    }

    private Post extractPostWithContent(ResultSet results) throws SQLException {
        String content = parseContent(results);
        String imgLink = parseImgLink(results);
        User author = parseAuthor(results);

        return extractBasicPostBuilder(results)
                .setContent(content)
                .setAuthor(author)
                .setImgLink(imgLink)
                .build();
    }


    private Post extractPostWithCategoryId(ResultSet results) throws SQLException {
        String imgLink = parseImgLink(results);
        int categoryId = parseCategoryId(results);
        Category category = new Category(categoryId);

        return extractBasicPostBuilder(results)
                .addCategory(category)
                .setImgLink(imgLink)
                .build();
    }

    private Post extractPostOfAdminPage(ResultSet results) throws SQLException {
        String summary = parseSummary(results);
        User author = parseAuthor(results);
        Date lastUpdatedDate = parseLastUpdated(results);
        boolean isApproved = parseApproved(results);

        return extractBasicPostBuilder(results)
                .setSummary(summary)
                .setLastUpdated(lastUpdatedDate)
                .setApproved(isApproved)
                .setAuthor(author)
                .build();
    }

    private Post extractBasicPost(ResultSet results) throws SQLException {
        String imgLink = parseImgLink(results);
        return extractBasicPostBuilder(results)
                .setImgLink(imgLink)
                .build();
    }

    private Post.PostBuilder extractBasicPostBuilder(ResultSet results) throws SQLException {
        int id = parseId(results);
        String title = parseTitle(results);
        Date creationDate = parseCreationDate(results);

        return new Post.PostBuilder(title)
                .setId(id)
                .setCreationDate(creationDate);
    }

    private User parseAuthor(ResultSet results) throws SQLException {
        int authorId = parseAuthorId(results);
        String authorName = results.getString("authorName");
        return new User(authorId, authorName);
    }

    private void addCategoriesToPost(ResultSet categorySet, Post postWithContent) throws SQLException {
        while (categorySet.next()) {
            Category category = extractCategory(categorySet);
            postWithContent.addCategory(category);
        }
    }

    private void addTagsToPost(ResultSet tagSet, Post post) throws SQLException {
        while (tagSet.next()) {
            Tag tag = extractTag(tagSet);
            System.out.println("add tag: " + tag);
            post.addTag(tag);
        }
    }

    private String parseImgLink(ResultSet results) throws SQLException {
        return results.getString("img_link");
    }

    private String parseContent(ResultSet results) throws SQLException {
        return results.getString("content");
    }

    private String parseTitle(ResultSet results) throws SQLException {
        return results.getString("title");
    }

    private int parseAuthorId(ResultSet results) throws SQLException {
        return results.getInt("author");
    }

    private int parseId(ResultSet results) throws SQLException {
        return results.getInt("id");
    }

    private String parseSummary(ResultSet results) throws SQLException {
        return results.getString("summary");
    }

    private boolean parseApproved(ResultSet results) throws SQLException {
        return results.getBoolean("is_approved");
    }

    private Date parseCreationDate(ResultSet results) throws SQLException {
        return parseDate(results, "created");
    }

    private int parseCategoryId(ResultSet results) throws SQLException {
        return results.getInt("category_id");
    }

    private Date parseLastUpdated(ResultSet results) throws SQLException {
        return parseDate(results, "last_updated");
    }

    private Date parseDate(ResultSet results, String columnName) throws SQLException {
        String lastUpdatedStr = results.getString(columnName);
        return StringUtils.parseDateFromDatabase(lastUpdatedStr);
    }
}
