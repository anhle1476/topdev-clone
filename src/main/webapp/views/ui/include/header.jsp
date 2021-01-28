<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Topdev - CLONE</title>
<%--    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/ui-assets/css/bootstrap.min.css"/>--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/ui-assets/css/custom.css"/>

    <script
            src="https://kit.fontawesome.com/a66a112bc4.js"
            crossorigin="anonymous"
    ></script>
</head>
<body>
<div class="container-fluid bg-primary">
    <a class="text-white form-control-sm" href="tel:+028.6273.3496"
    >028.6273.3496</a
    >
</div>
<div class="container">
    <!-- Main logo -->
    <a href="<c:url value="/blog?site=home"/>">
        <img src="${pageContext.servletContext.contextPath}/ui-assets/image/logo-new-1.png" alt="logo"
             class="max-height-125 py-3"/>
    </a>
    <!-- Nav-bar -->
    <nav class="navbar navbar-expand-md navbar-dark bg-primary py-2 py-md-0">
        <button
                class="navbar-toggler"
                type="button"
                data-toggle="collapse"
                data-target="#navbarColor01"
                aria-controls="navbarColor01"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarColor01">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link text-white" href="#">TÌM VIỆC IT </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="#">TẠO CV ONLINE</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="#">VĂN HÓA CÔNG TY</a>
                </li>

                ${ sessionScope["categories"].navBarHtml }

                <li class="nav-item">
                    <a class="nav-link text-white" href="#">TOPDEV X HACKERRANK</a>
                </li>
            </ul>
            <form class="form-inline my-lg-0">
                <i id="nav-search-toggle" class="fas fa-search text-white"></i>
                <div id="nav-search" style="visibility: hidden; opacity: 0">
                    <input class="form-control" type="text" placeholder="Search"/>
                    <button class="btn btn-secondary my-sm-0" type="submit">
                        Search
                    </button>
                </div>
            </form>
        </div>
    </nav>