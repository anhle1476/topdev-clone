package com.codegym.lqhanh.topdev_clone.filters;

import com.codegym.lqhanh.topdev_clone.models.modelcontainer.CategoryMap;
import com.codegym.lqhanh.topdev_clone.services.CategoryService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "FetchCategoryFilter")
public class FetchCategoryFilter implements Filter {
    public static final CategoryService CATEGORY_SERVICE = new CategoryService();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        Object categories = session.getAttribute("categories");
        if (categories == null) {
            CategoryMap manager = CATEGORY_SERVICE.getCategoryMap();
            session.setAttribute("categories", manager);
        }

        chain.doFilter(request, response);
    }
}
