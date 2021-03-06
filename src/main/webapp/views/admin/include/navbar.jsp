<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" data-widget="pushmenu" href="#" role="button"
            ><i class="fas fa-bars"></i
            ></a>
        </li>
        <li class="nav-item d-none d-sm-inline-block">
            <a href="index3.html" class="nav-link">Home</a>
        </li>
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <a class="nav-link" data-widget="fullscreen" href="#" role="button">
                <i class="fas fa-expand-arrows-alt"></i>
            </a>
        </li>
        <li class="nav-item">
            <button class="nav-link logout-btn btn">
                Đăng xuất <i class="fas fa-sign-out-alt ml-2"></i>
            </button>
        </li>
    </ul>
</nav>
<c:if test='${sessionScope["notifications"] != null}' >
    <c:forEach items='${sessionScope["notifications"]}' var="notification">
        <div class="d-none toasts">
            <span class="toast-bg">${notification.className}</span>
            <span class="toast-body">${notification.message}</span>
        </div>
    </c:forEach>
    <c:remove var="notifications" scope="session" />
</c:if>

