<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <c:import url="include/baseHeader.jsp"></c:import>
    <title>Unapproved Posts - Topdev CLONE</title>

    <!-- DataTables -->
    <link
            rel="stylesheet"
            href="${pageContext.servletContext.contextPath}/admin-assets/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css"
    />
    <link
            rel="stylesheet"
            href="${pageContext.servletContext.contextPath}/admin-assets/plugins/datatables-responsive/css/responsive.bootstrap4.min.css"
    />

</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <!-- Navbar -->
    <c:import url="include/navbar.jsp"></c:import>
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <c:import url="include/sidebar.jsp"></c:import>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>Bài viết chưa được duyệt</h1>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h3 class="card-title">
                                    Quản lý các bài viết chưa được phê duyệt
                                </h3>
                            </div>
                            <!-- /.card-header -->
                            <div class="card-body">
                                <table
                                        id="posts-table"
                                        class="table table-bordered table-hover"
                                >
                                    <thead>
                                    <tr>
                                        <th>Tiêu đề</th>
                                        <th>Tóm tắt</th>
                                        <th>Tác giả</th>
                                        <th>Ngày tạo</th>
                                        <th>Ngày cập nhật</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="user" value='${sessionScope["user"]}'/>
                                    <c:set var="userRole" value='${user.role}'/>

                                    <c:forEach items='${requestScope["postList"]}' var="post">
                                        <tr id="post-${post.id}">
                                            <td>${post.title}</td>
                                            <td class="shorten">${post.summary}</td>
                                            <td>${post.author.name}</td>
                                            <td><fmt:formatDate pattern="dd-MM-yyyy" value="${post.creationDate}"/></td>
                                            <td><fmt:formatDate pattern="dd-MM-yyyy" value="${post.lastUpdated}"/></td>
                                            <td>
                                                <c:if test="${user.id == post.author.id || userRole.canEditOthersPosts}">
                                                    <a
                                                            class="btn btn-info btn-sm btn-edit"
                                                            title="Chỉnh sửa"
                                                            href="${pageContext.servletContext.contextPath}/admin/editor?edit=exists-post&post-id=${post.id}"
                                                    >
                                                        <i class="fas fa-pencil-alt"></i>
                                                    </a>
                                                    <button
                                                            class="btn btn-danger btn-sm btn-edit delete-btn"
                                                            title="Xóa"
                                                            id="delete-${post.id}"
                                                    >
                                                        <i class="fas fa-trash"></i>
                                                    </button>
                                                </c:if>
                                                <c:if test="${userRole.canApprovesPosts}">
                                                    <button
                                                            class="btn btn-success btn-sm btn-edit approve-btn"
                                                            title="Phê duyệt"
                                                            id="approve-${post.id}"
                                                    >
                                                        <i class="fas fa-thumbs-up"></i>
                                                    </button>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <th>Tiêu đề</th>
                                        <th>Tóm tắt</th>
                                        <th>Tác giả</th>
                                        <th>Ngày tạo</th>
                                        <th>Ngày cập nhật</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
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
<!-- DataTables  & Plugins -->
<script src="${pageContext.servletContext.contextPath}/admin-assets/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/admin-assets/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/admin-assets/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/admin-assets/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>

<!-- Page specific script -->
<c:import url="include/postTableJs.jsp" />
</body>
</html>
