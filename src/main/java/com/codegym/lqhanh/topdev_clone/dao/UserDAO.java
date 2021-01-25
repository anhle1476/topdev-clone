package com.codegym.lqhanh.topdev_clone.dao;

import com.codegym.lqhanh.topdev_clone.models.Role;
import com.codegym.lqhanh.topdev_clone.models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public User loginAndFetchUser(String email, String password) throws SQLException {
        try (
                Connection conn = DAOUtils.getConnection();
                CallableStatement statement = conn.prepareCall("{CALL loginAndFetchUser(?, ?)}");
        ) {
            statement.setString(1, email.trim());
            statement.setString(2, password);
            ResultSet results = statement.executeQuery();
            if (results.next())
                return extractUserFromResultSet(results);
            return null;
        }
    }

    private User extractUserFromResultSet(ResultSet results) throws SQLException {
        int userId = results.getInt("user_id");
        String userEmail = results.getString("email");
        String userName = results.getString("user_name");

        Role role = extractRoleFromResultSet(results);
        return new User(userId, userName, userEmail, role);
    }

    private Role extractRoleFromResultSet(ResultSet results) throws SQLException {
        int roleId = results.getInt("role_id");
        String roleName = results.getString("role_name");
        boolean canUpdateUser = results.getBoolean("update_users");
        boolean canUpdatePermission = results.getBoolean("update_permission");
        boolean canUpdateCategories = results.getBoolean("update_categories");
        boolean canApprovePost = results.getBoolean("approve_post");
        boolean canEditOtherPost = results.getBoolean("edit_other_post");
        boolean canWritePost = results.getBoolean("write_post");
        return new Role.RoleBuilder(roleId, roleName)
                .setCanUpdateUsers(canUpdateUser)
                .setCanUpdatePermission(canUpdatePermission)
                .setCanUpdateCategories(canUpdateCategories)
                .setCanApprovesPosts(canApprovePost)
                .setCanEditOthersPosts(canEditOtherPost)
                .setCanWritePosts(canWritePost)
                .build();
    }

    public String getUserPostStatus(int id) throws SQLException {
        try (
                Connection con = DAOUtils.getConnection();
                CallableStatement statement = con.prepareCall("{CALL getUserPostStatus(?)}");
        ) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next())
                return result.getString("posts_status");
            else
                return "Không tìm thấy dữ liệu";
        }
    }
}
