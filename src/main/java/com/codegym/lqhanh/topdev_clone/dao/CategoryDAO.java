package com.codegym.lqhanh.topdev_clone.dao;

import com.codegym.lqhanh.topdev_clone.models.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class CategoryDAO {
    public Map<Integer, Category> getCategories() throws SQLException {
        try (
                Connection connection = DAOUtils.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet results = statement.executeQuery("SELECT * FROM categories;");
            Map<Integer, Category> categoryMap = new TreeMap<>();
            while (results.next()) {
                Category category = getCategoryFromResultSet(results);
                categoryMap.put(category.getId(), category);
            }
            return categoryMap;
        }
    }

    private Category getCategoryFromResultSet(ResultSet results) throws SQLException {
        int id = results.getInt("id");
        String name = results.getString("name");
        int parentId = results.getInt("parent_id");
        return new Category(id, name, parentId);
    }
}
