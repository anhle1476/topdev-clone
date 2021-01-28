package com.codegym.lqhanh.topdev_clone.controllers;

import com.codegym.lqhanh.topdev_clone.models.User;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {

    public static User getUserFromSession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }
}
