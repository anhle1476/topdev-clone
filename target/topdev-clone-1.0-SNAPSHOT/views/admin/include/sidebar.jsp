<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="${pageContext.servletContext.contextPath}/admin?site=dashboard" class="brand-link">
        <img
                src="${pageContext.servletContext.contextPath}/admin-assets/dist/img/AdminLTELogo.png"
                alt="AdminLTE Logo"
                class="brand-image img-circle elevation-3"
                style="opacity: 0.8"
        />
        <span class="brand-text font-weight-light">TOPDEV clone</span>
    </a>

    <c:set var="user" value='${sessionScope["user"]}'/>
    <c:set var="userRole" value='${user.role}'/>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="info">
                <a href="${pageContext.servletContext.contextPath}/admin?site=dashboard"
                   class="d-block">${user.name}</a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul
                    class="nav nav-pills nav-sidebar flex-column"
                    data-widget="treeview"
                    role="menu"
                    data-accordion="false"
            >
                <!-- Account controller -->
                <li class="nav-item">
                    <a href="${pageContext.servletContext.contextPath}/admin?site=dashboard" class="nav-link active">
                        <i class="nav-icon fas fa-user"></i>
                        <p>Tài khoản</p>
                    </a>
                </li>
                <!-- /account-controller -->

                <c:if test="${userRole.canWritePosts || userRole.canEditOthersPosts || userRole.canApprovesPosts}">
                    <!-- Posts controller -->
                    <li class="nav-item menu-open">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fas fa-file-alt"></i>
                            <p>
                                Quản lý bài viết
                                <i class="right fas fa-angle-left"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <c:if test="${userRole.canWritePosts}">
                                <!-- Add new post -->
                                <li class="nav-item">
                                    <a href="${pageContext.servletContext.contextPath}/admin/editor?edit=new-post"
                                       class="nav-link">
                                        <i class="fas fa-pen-nib nav-icon"></i>
                                        <p>Thêm bài viết mới</p>
                                    </a>
                                </li>
                                <!-- /add-new-post -->
                            </c:if>

                            <c:if test="${userRole.canWritePosts || userRole.canEditOthersPosts}">
                                <!-- Current posts -->
                                <li class="nav-item">
                                    <a href="${pageContext.servletContext.contextPath}/admin?site=current-posts"
                                       class="nav-link">
                                        <i class="fas fa-list nav-icon"></i>
                                        <p>Bài viết hiện tại</p>
                                    </a>
                                </li>
                                <!-- /current-posts -->
                            </c:if>

                            <c:if test="${userRole.canApprovesPosts}">
                                <!-- Approve posts -->
                                <li class="nav-item">
                                    <a href="#" class="nav-link">
                                        <i class="fas fa-check-square nav-icon"></i>
                                        <p>Phê duyệt bài viết</p>
                                    </a>
                                </li>
                                <!-- /approve-posts -->
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${userRole.canUpdateUsers || userRole.canUpdatePermission || userRole.canUpdateCategories }">
                    <!-- Page controller -->
                    <li class="nav-item menu-open">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fas fa-tachometer-alt"></i>
                            <p>
                                Quản lý trang
                                <i class="right fas fa-angle-left"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <c:if test="${userRole.canUpdateUsers}">
                                <!-- users controller -->
                                <li class="nav-item">
                                    <a href="#" class="nav-link">
                                        <i class="fas fa-users-cog nav-icon"></i>
                                        <p>Người dùng</p>
                                    </a>
                                </li>
                                <!-- /users-controller -->
                            </c:if>

                            <c:if test="${userRole.canUpdatePermission}">
                                <!-- Role and permission -->
                                <li class="nav-item">
                                    <a href="#" class="nav-link">
                                        <i class="fas fa-unlock-alt nav-icon"></i>
                                        <p>Quyền hạn</p>
                                    </a>
                                </li>
                                <!-- /role-and-permission -->
                            </c:if>

                            <c:if test="${userRole.canUpdateCategories}">
                                <!-- Tags controller -->
                                <li class="nav-item">
                                    <a href="#" class="nav-link">
                                        <i class="fas fa-tags nav-icon"></i>
                                        <p>Chủ đề</p>
                                    </a>
                                </li>
                                <!-- /tags-controller -->
                            </c:if>
                        </ul>
                    </li>
                    <!-- /posts-controller -->
                </c:if>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>