<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="include/header.jsp"></c:import>

<!-- Main body -->
<div class="container">
    <img src="${pageContext.servletContext.contextPath}/ui-assets/image/400-error.png" class="m-auto d-block"
         alt="Error image">
    <h3 class="text-center">Trang bạn yêu cầu không tìm thấy.</h3>
    <a class="text-center d-block" href="<c:url value="/blog?site=home"/>">Trở về trang chủ</a>
</div>

<c:import url="include/footer.jsp"/>