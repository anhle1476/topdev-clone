<%@page contentType="text/html" pageEncoding="UTF-8" %>
<script>
    const requestURL = "<%= request.getContextPath() %>/admin/request";
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

    document.querySelectorAll(".logout-btn").forEach(btn => btn.addEventListener("click", logoutBtnHandler));

    function logoutBtnHandler() {
        $.post("<%= request.getContextPath() %>/admin/request", {
            action: "logout"
        }).done(response => {
            const {action, isAccepted} = JSON.parse(response);
            console.log(action, isAccepted);
            if (action === "logout" && isAccepted)
                location.reload();
            else
                alert("Đăng xuất thất bại!");
        });
    }
</script>
