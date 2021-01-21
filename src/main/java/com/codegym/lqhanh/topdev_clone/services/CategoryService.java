package com.codegym.lqhanh.topdev_clone.services;

import com.codegym.lqhanh.topdev_clone.dao.CategoryDAO;
import com.codegym.lqhanh.topdev_clone.models.Category;
import com.codegym.lqhanh.topdev_clone.models.modelcontainer.CategoryMap;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CategoryService {
    private final CategoryDAO categoryDAO = new CategoryDAO();

    public static String generateNavbarHtml(CategoryMap categoryMap) {
        List<Category> baseCategories = categoryMap.getBaseCategories();
        StringBuilder html = new StringBuilder(1300);
        for (Category baseCategory : baseCategories) {
            List<Category> childCategories = categoryMap.getChildCategories(baseCategory);
            if (childCategories.isEmpty()) {
                html.append("<li class=\"nav-item\"><a class=\"nav-link text-white\" href=\"#\">").append(baseCategory.getName()).append("</a></li>");
            } else {
                html.append("<li class=\"nav-item dropdown\"><a class=\"nav-link dropdown-toggle text-white\" data-toggle=\"dropdown\" href=\"#\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">")
                        .append(baseCategory.getName())
                        .append("</a><ul class=\"dropdown-menu\" aria-labelledby=\"navbarDropdownMenuLink\">");
                for (Category childCategory : childCategories) {
                    generateSubmenuHtml(html, categoryMap, childCategory);
                }
                html.append("</ul></li>");
            }
        }
        return html.toString();
    }

    private static void generateSubmenuHtml(StringBuilder result, CategoryMap categoryMap, Category category) {
        List<Category> childCategories = categoryMap.getChildCategories(category);
        if (childCategories.isEmpty()) {
            result.append("<li><a class=\"dropdown-item\" href=\"#\">")
                    .append(category.getName())
                    .append("</a></li>");
            return;
        }
        result.append("<li><a class=\"dropdown-item dropdown-toggle\" href=\"#\">")
                .append(category.getName())
                .append("</a><ul class=\"dropdown-menu\">");
        for (Category childCategory : childCategories) {
            generateSubmenuHtml(result, categoryMap, childCategory);
        }
        result.append("</ul></li>");
    }

    public CategoryMap getCategoryMap() {
        try {
            Map<Integer, Category> categories = categoryDAO.getCategories();
            return new CategoryMap(categories);
        } catch (SQLException e) {
            System.out.println("Get Categories Failed: " + e.getMessage());
            e.printStackTrace();
            return new CategoryMap();
        }
    }

}
