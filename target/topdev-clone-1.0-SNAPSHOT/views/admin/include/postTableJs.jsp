<%@page contentType="text/html" pageEncoding="UTF-8" %>

<script>

    $(function () {
        const table = $("#posts-table").DataTable({
            responsive: true,
            lengthChange: true,
            autoWidth: false,
            ordering: true,
            info: true,
            paging: true,
        });

        // shorten summary cells
        document.querySelectorAll(".shorten").forEach(cell => {
            let text = cell.innerText;
            if (text.length > 150) {
                cell.innerText = text.slice(0, 150) + "..."
            }
        });

        // handle approve & delete AJAX calls
        const requestURL = "<%= request.getContextPath() %>/admin/request";
        document.querySelectorAll(".approve-btn").forEach(btn => {
            btn.addEventListener("click", ({target}) => {
                $.post(requestURL, {
                    action: "approve-post",
                    postId: target.id.slice(8)
                }).done(handleApprovePostResponse);
            })
        })
        document.querySelectorAll(".delete-btn").forEach(btn => {
            btn.addEventListener("click", ({target}) => {
                if (!confirm("Có chắc chắn xóa bài viết này không?")) return;
                $.post(requestURL, {
                    action: "delete-post",
                    postId: target.id.slice(7)
                }).done(handleDeletePostResponse);
            })
        })


        function removePost(id) {
            table
                .row($("#post-" + id))
                .remove()
                .draw();
        }

        function toasting(bg, message) {
            $(document).Toasts('create', {
                class: bg,
                title: "Thông báo",
                body: message,
                autohide: true,
                delay: 5000,
                position: 'bottomRight',
            })
        }

        function toastSuccess(message) {
            toasting("bg-success", message);
        }

        function toastDanger(message) {
            toasting("bg-danger", message);
        }

        function handleAJAXRequest(response, requestType, successMsg, failureMsg) {
            const {request, postId, isAccepted} = JSON.parse(response);
            if (isAccepted && request === requestType) {
                removePost(postId);
                toastSuccess(successMsg);
            } else {
                toastDanger(failureMsg);
            }
        }

        function handleApprovePostResponse(response) {
            const successMsg = "Phê duyệt bài viết thành công!";
            const failedMsg = "Đã có lỗi xảy ra! Phê duyệt bài viết thất bại.";
            handleAJAXRequest(response, "approve", successMsg, failedMsg);
        }

        function handleDeletePostResponse(response) {
            const successMsg = "Xóa bài viết thành công!";
            const failedMsg = "Đã có lỗi xảy ra! Xóa bài viết thất bại.";
            handleAJAXRequest(response, "delete", successMsg, failedMsg);
        }
    });


</script>
