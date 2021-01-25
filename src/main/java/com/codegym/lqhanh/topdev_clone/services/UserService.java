package com.codegym.lqhanh.topdev_clone.services;

import com.codegym.lqhanh.topdev_clone.dao.UserDAO;
import com.codegym.lqhanh.topdev_clone.models.User;

import java.sql.SQLException;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public User loginAndFetchUser(String email, String password) {
        try {
            return userDAO.loginAndFetchUser(email, password);
        } catch (SQLException e) {
            System.out.println("Login faied: " + e.getMessage());
            return null;
        }
    }

    public String getUserPostStatus(int id) {
        try {
            return userDAO.getUserPostStatus(id);
        } catch (SQLException e) {
            return "Khong the lay du lieu";
        }
    }
}
