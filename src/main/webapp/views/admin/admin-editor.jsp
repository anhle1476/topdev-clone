<%@ page import="com.codegym.lqhanh.topdev_clone.models.modelcontainer.CategoryMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="include/baseHeader.jsp"></c:import>
    <!-- summernote -->
    <link
            rel="stylesheet"
            href="${pageContext.servletContext.contextPath}/admin-assets/plugins/summernote/summernote-bs4.min.css"
    />
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <!-- Navbar -->
    <c:import url="include/navbar.jsp"/>
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <c:import url="include/sidebar.jsp"></c:import>

    <c:set var="isNewPost" value='${ requestScope["post-id"] == 0 }'/>
    <c:set var="postData" value='${requestScope["post-data"]}'/>
    <c:if test="${!isNewPost && postData == null}">
        <c:redirect url="/admin?site=dashboard"/>
    </c:if>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <c:choose>
                            <c:when test="${isNewPost}">
                                <h1>Bài viết mới</h1>
                            </c:when>
                            <c:otherwise>
                                <h1>Chỉnh sửa bài viết</h1>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="card card-outline card-info">
                        <form action="${pageContext.servletContext.contextPath}/edit-post" method="post">
                            <input type="hidden"
                                   name="post-id"
                                   value='${requestScope["post-id"]}'>
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="title">Tiêu đề</label>
                                    <input id="title" name="title" type="text" class="form-control" required
                                           placeholder="Tiêu đề ..." value="${postData.title}">
                                </div>
                                <div class="form-group">
                                    <label for="img-link">Ảnh bìa</label>
                                    <input id="img-link" name="img-link" type="text" class="form-control" required
                                           placeholder="Ex: https://via.placeholder.com/800x500 ..."
                                           value="${postData.imgLink}"
                                    >
                                </div>
                                <div class="form-group">
                                    <label for="summary">Tóm tắt</label>
                                    <textarea id="summary" name="summary" class="form-control" rows="4" required
                                              placeholder="Tóm tắt bài viết ...">${postData.summary}</textarea>
                                </div>
                                <div class="form-group">
                                    <label for="summernote">Nội dung</label>
                                    <textarea id="summernote" name="content" required>
                                        <c:if test="${isNewPost}">
                                            Viết <em>bài</em> <u>tại</u> <strong>đây</strong>
                                        </c:if>
                                        ${postData.content}
                                    </textarea>
                                </div>
                                <div class="form-group">
                                    <label for="tags">Tag</label>
                                    <input id="tags" name="tags" type="text" class="form-control" required
                                           placeholder="Ex: lập trình viên, coding, testing (cách nhau bởi dấu phẩy) ..."
                                           value="${postData.getFormattedTagList()}"
                                    >
                                </div>
                                <div class="form-group">
                                    <label>Chủ đề</label>
                                    <div class="row categories">

                                        <c:forEach items='${sessionScope["categories"].getCategories()}' var="category">
                                            <div class="form-check col-md-3 col-sm-6">
                                                <input id="cat-${category.id}"
                                                       class="form-check-input"
                                                       type="checkbox"
                                                       name="cat-${category.id}"
                                                       value="${category.id}"
                                                       aria-checked="${postData.containsCategory(category.id)}"
                                                >
                                                <label for="cat-${category.id}"
                                                       class="form-check-label">${category.name}</label>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer">
                                <button type="submit" class="btn btn-primary float-right">
                                    Hoàn tất
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- /.col-->
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <c:import url="include/footer.jsp"/>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
<c:import url="include/baseJs.jsp"/>
<!-- Summernote -->
<script src="${pageContext.servletContext.contextPath}/admin-assets/plugins/summernote/summernote-bs4.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.servletContext.contextPath}/admin-assets/dist/js/demo.js"></script>
<!-- Page specific script -->
<script>
    $(function () {
        // Summernote
        $("#summernote").summernote();
    });
    document.querySelectorAll('.categories input[type=checkbox]')
        .forEach(checkbox => {
            if (checkbox.ariaChecked === "true")
                checkbox.checked = true
        })
</script>
</body>
</html>
