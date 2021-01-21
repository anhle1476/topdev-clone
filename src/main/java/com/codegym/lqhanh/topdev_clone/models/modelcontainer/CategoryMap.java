package com.codegym.lqhanh.topdev_clone.models.modelcontainer;

import com.codegym.lqhanh.topdev_clone.models.Category;
import com.codegym.lqhanh.topdev_clone.services.CategoryService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CategoryMap {
    private Map<Integer, Category> categoryMap;
    private String navBarHtml;

    public CategoryMap() {
        this.categoryMap = new TreeMap<>();
        this.navBarHtml = "";
    }

    public CategoryMap(Map<Integer, Category> categoryMap) {
        setCategoryMap(categoryMap);
    }

    public Map<Integer, Category> getCategoryMap() {
        return categoryMap;
    }

    public void setCategoryMap(Map<Integer, Category> categoryMap) {
        this.categoryMap = categoryMap;
        navBarHtml = CategoryService.generateNavbarHtml(this);
    }

    public Set<Integer> getCategoryIds() {
        return categoryMap.keySet();
    }

    public Category getCategoryById(int id) {
        return categoryMap.get(id);
    }

    public Collection<Category> getCategories() {
        return categoryMap.values();
    }

    public List<Category> getChildCategories(Category parentCategory) {
        int parentId = parentCategory.getId();
        return getChildCategoriesWithParentId(parentId);
    }

    public List<Category> getBaseCategories() {
        return getChildCategoriesWithParentId(0);
    }

    private List<Category> getChildCategoriesWithParentId(int parentId) {
        return getCategories()
                .stream()
                .filter(category -> category.getParentId() == parentId)
                .collect(Collectors.toList());
    }

    public String getNavBarHtml() {
        return navBarHtml;
    }
}
