<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="include/header.jsp"></c:import>

<c:set var="post" value='${requestScope["post"]}'/>
<!-- Main -->
<div class="row">
    <!-- Main content -->
    <div class="col-md-8">
        <!-- Tags -->
        <div class="tags-group">
            <c:forEach items="${post.categories}" var="postCategory">
                <a href='<c:url value="/blog?site=category&category-id=${postCategory.id}" />' class="tag tag-dark">
                        ${postCategory.name}
                </a>
            </c:forEach>
        </div>

        <!-- post title -->
        <h1 id="post-title" class="mb-4">
            ${post.title}
        </h1>

        <!-- post content -->
        <div id="post-content" class="text-primary">
            ${post.content}
        </div>

        <div class="tags-group">
            <p class="tag tag-dark font-weight-bolder">TAGS</p>
            <c:forEach items="${post.tagList}" var="postTag">
                <a href='<c:url value="/blog?site=tag&tag-id=${postTag.id}" />' class="tag tag-light">
                        ${postTag.name}
                </a>
            </c:forEach>
        </div>

        <!-- related post -->
        <div id="related-post">
            <div class="row bg-primary text-white p-3 my-5">
                <p class="text-center m-0 w-100">BÀI VIẾT LIÊN QUAN</p>
            </div>
            <div class="row">
                <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                    <div class="card">
                        <img
                                class="card-img-top"
                                src="https://via.placeholder.com/800x500"
                        />
                        <div class="card-body">
                            <p class="font-weight-bold text-dark mb-1">
                                Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                Totam, odit.
                            </p>
                            <p class="text-small">Lorem ipsum dolor sit.</p>
                        </div>
                    </div>
                </div>

                <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                    <div class="card">
                        <img
                                class="card-img-top"
                                src="https://via.placeholder.com/800x500"
                        />
                        <div class="card-body">
                            <p class="font-weight-bold text-dark mb-1">
                                Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                Totam, odit.
                            </p>
                            <p class="text-small">Lorem ipsum dolor sit.</p>
                        </div>
                    </div>
                </div>

                <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                    <div class="card">
                        <img
                                class="card-img-top"
                                src="https://via.placeholder.com/800x500"
                        />
                        <div class="card-body">
                            <p class="font-weight-bold text-dark mb-1">
                                Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                Totam, odit.
                            </p>
                            <p class="text-small">Lorem ipsum dolor sit.</p>
                        </div>
                    </div>
                </div>

                <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                    <div class="card">
                        <img
                                class="card-img-top"
                                src="https://via.placeholder.com/800x500"
                        />
                        <div class="card-body">
                            <p class="font-weight-bold text-dark mb-1">
                                Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                Totam, odit.
                            </p>
                            <p class="text-small">Lorem ipsum dolor sit.</p>
                        </div>
                    </div>
                </div>

                <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                    <div class="card">
                        <img
                                class="card-img-top"
                                src="https://via.placeholder.com/800x500"
                        />
                        <div class="card-body">
                            <p class="font-weight-bold text-dark mb-1">
                                Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                Totam, odit.
                            </p>
                            <p class="text-small">Lorem ipsum dolor sit.</p>
                        </div>
                    </div>
                </div>

                <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                    <div class="card">
                        <img
                                class="card-img-top"
                                src="https://via.placeholder.com/800x500"
                        />
                        <div class="card-body">
                            <p class="font-weight-bold text-dark mb-1">
                                Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                Totam, odit.
                            </p>
                            <p class="text-small">Lorem ipsum dolor sit.</p>
                        </div>
                    </div>
                </div>

                <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                    <div class="card">
                        <img
                                class="card-img-top"
                                src="https://via.placeholder.com/800x500"
                        />
                        <div class="card-body">
                            <p class="font-weight-bold text-dark mb-1">
                                Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                Totam, odit.
                            </p>
                            <p class="text-small">Lorem ipsum dolor sit.</p>
                        </div>
                    </div>
                </div>

                <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                    <div class="card">
                        <img
                                class="card-img-top"
                                src="https://via.placeholder.com/800x500"
                        />
                        <div class="card-body">
                            <p class="font-weight-bold text-dark mb-1">
                                Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                Totam, odit.
                            </p>
                            <p class="text-small">Lorem ipsum dolor sit.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Side bar -->
    <div class="col-md-4">
        <c:import url="include/sidebar.jsp"></c:import>
    </div>
</div>
</div>

<c:import url="include/footer.jsp"></c:import>