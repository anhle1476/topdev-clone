<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-4 mb-4">
    <c:set var="categoryPostList" value='${requestScope["homepage-post"].getPostListByKeyword(param.categoryKey)}' />
    <c:set var="firstPost" value="${categoryPostList.getPostByIndex(0)}"/>

    <h1 class="py-4">${categoryPostList.getFirstPostCategory(sessionScope["categories"])}</h1>
    <!-- category main post -->
    <div class="card bg-image hover-zoom max-height-200">
        <a href="<c:url value="/blog?site=post&post-id=${firstPost.id}" />">
            <img alt="thumbnail-img" src="${firstPost.imgLink}"/>
            <div class="mask card-content-overlay">
                <h5 class="text-white">${firstPost.title}</h5>
                <p>${firstPost.creationDate}</p>
            </div>
        </a>
    </div>

    <!-- other post -->
    <c:forEach items="${categoryPostList.getPostsFromIndex(1)}" var="post">
        <a href="<c:url value="/blog?site=post&post-id=${post.id}" />">
            <div class="row no-gutters py-2">
                <div class="col-4">
                    <img alt="thumbnail-img" class="card-img" src="${post.imgLink}"/>
                </div>
                <div class="col-8 pl-1">
                    <p class="font-weight-bold text-dark">${post.title}</p>
                    <p class="text-small">${post.creationDate}</p>
                </div>
            </div>
        </a>
    </c:forEach>
</div>
