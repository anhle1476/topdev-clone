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