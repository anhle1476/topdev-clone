package com.codegym.lqhanh.topdev_clone.models;

public class Notification {
    public static String SUCCESS = "bg-success";
    public static String INFO = "bg-info";
    public static String WARNING = "bg-warning";
    public static String DANGER = "bg-success";

    private String className;
    private String message;

    public Notification(String className, String message) {
        this.className = className;
        this.message = message;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
