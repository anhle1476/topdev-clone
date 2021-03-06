<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="include/header.jsp"></c:import>

<c:set var="pageData" value='${requestScope["homepage-post"]}'/>
<c:set var="newestPostList" value='${pageData.getPostListByKeyword("newest")}'/>
<!-- Main post -->
<div class="row my-5 no-gutters">

    <c:forEach var="postIndex" begin="0" end="2">
        <c:set var="post" value="${newestPostList.getPostByIndex(postIndex)}"/>
    <c:choose>
    <c:when test="${postIndex == 0}">
    <div class="col-md-6 col-sm-12 fix-height-500">
        </c:when>
        <c:otherwise>
        <div class="col-md-3 col-sm-6 fix-height-500">
            </c:otherwise>
            </c:choose>
            <div class="card bg-image hover-zoom">
                <a href="<c:url value="/blog?site=post&post-id=${post.id}" />">
                    <img alt="thumbnail-img" src="${post.imgLink}"/>
                    <div class="mask card-content-overlay">
                        <h3 class="text-white">
                                ${post.title}
                        </h3>
                        <p><fmt:formatDate pattern="dd-MM-yyyy" value="${post.creationDate}"/></p>
                    </div>
                </a>
            </div>
        </div>
        <c:remove var="post"/>
        </c:forEach>
    </div>

    <!-- Newest post -->
    <h1>Mới nhất</h1>
    <div class="row">
        <c:forEach items='${newestPostList.getPostsFromIndex(3)}' var="newestPost">
            <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                <div class="card">
                    <a href="<c:url value="/blog?site=post&post-id=${newestPost.id}" />">
                        <img alt="thumbnail-img"
                             class="card-img-top"
                             src="${newestPost.imgLink}"
                        />
                        <div class="card-body">
                            <p class="font-weight-bold text-dark mb-1">
                                    ${newestPost.title}
                            </p>
                            <p class="text-small">
                                <fmt:formatDate pattern="dd-MM-yyyy" value="${newestPost.creationDate}"/>
                            </p>
                        </div>
                    </a>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- categories post -->
    <div class="row category-posts">
        <c:forEach begin="1" end="3" var="catCol">
            <div class="col-md-4 mb-4">
                <c:set var="categoryPostList"
                       value='${pageData.getPostListByKeyword(String.format("cat-%d", catCol))}'/>
                <c:set var="firstPost" value="${categoryPostList.getPostByIndex(0)}"/>

                <h1 class="py-4">${sessionScope["categories"].getFirstCategoryOfPost(firstPost)}</h1>
                <!-- category main post -->
                <div class="card bg-image hover-zoom fix-height-250">
                    <a href="<c:url value="/blog?site=post&post-id=${firstPost.id}" />">
                        <img alt="thumbnail-img" src="${firstPost.imgLink}"/>
                        <div class="mask card-content-overlay">
                            <h5 class="text-white">${firstPost.title}</h5>
                            <p>
                                <fmt:formatDate pattern="dd-MM-yyyy" value="${firstPost.creationDate}"/>
                            </p>
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
                                <p class="text-small">
                                    <fmt:formatDate pattern="dd-MM-yyyy" value="${post.creationDate}"/>
                                </p>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </c:forEach>
    </div>

    <!-- 2nd category post -->
    <div class="row">
        <!-- chuyen gia noi -->
        <div class="col-md-8">
            <c:set var="categoryPostList" value='${pageData.getPostListByKeyword("cat-4")}'/>
            <h1 class="py-4">${sessionScope["categories"].getFirstCategoryOfPost(categoryPostList.getPostByIndex(0))}</h1>
            <div class="row no-gutters">
                <c:forEach items="${categoryPostList.posts}" var="post">
                    <div class="col-6">
                        <div class="card bg-image hover-zoom fix-height-250">
                            <a href='<c:url value="/blog?site=post&post-id=${post.id}" />'>
                                <img alt="thumbnail-img" src="${post.imgLink}"/>
                                <div class="mask card-content-overlay">
                                    <h5 class="text-white">${post.title}</h5>
                                    <p><fmt:formatDate pattern="dd-MM-yyyy" value="${post.creationDate}"/></p>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- workshop -->
        <div class="col-md-4">
            <c:set var="categoryPostList" value='${pageData.getPostListByKeyword("cat-5")}'/>
            <h1 class="py-4">${sessionScope["categories"].getFirstCategoryOfPost(categoryPostList.getPostByIndex(0))}</h1>
            <div class="row">
                <c:forEach items="${categoryPostList.posts}" var="post">
                    <div class="card ">
                        <a href='<c:url value="/blog?site=post&post-id=${post.id}" />'>
                            <img alt="thumbnail-img" class="card-img-top" src="${post.imgLink}"/>
                            <div class="card-body">
                                <p class="font-weight-bold text-dark">${post.title}</p>
                                <p class="text-small">
                                    <fmt:formatDate pattern="dd-MM-yyyy" value="${post.creationDate}"/>
                                </p>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <c:import url="include/footer.jsp"/>

