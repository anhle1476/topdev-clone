<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!-- jQuery -->
<script src="${pageContext.servletContext.contextPath}/admin-assets/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.servletContext.contextPath}/admin-assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.servletContext.contextPath}/admin-assets/dist/js/adminlte.min.js"></script>

<script>
    document.querySelectorAll(".d-none.toasts").forEach(toaster => {
        const [bg, body] = Array.from(toaster.children).map(span => span.innerText);
        $(document).Toasts('create', {
            class: bg,
            title: "Thông báo",
            body: body,
            autohide: true,
            delay: 5000,
            position: 'bottomRight',
        })
    })

</script>