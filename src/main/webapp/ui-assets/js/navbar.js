$(document).ready(function () {
    $('.dropdown-menu a.dropdown-toggle').on('click', function (e) {
        var $el = $(this);
        var $parent = $(this).offsetParent(".dropdown-menu");
        if (!$(this).next().hasClass('show')) {
            $(this).parents('.dropdown-menu').first().find('.show').removeClass("show");
        }
        var $subMenu = $(this).next(".dropdown-menu");
        $subMenu.toggleClass('show');

        $(this).parent("li").toggleClass('show');

        $(this).parents('li.nav-item.dropdown.show').on('hidden.bs.dropdown', function (e) {
            $('.dropdown-menu .show').removeClass("show");
        });

        if (!$parent.parent().hasClass('navbar-nav')) {
            $el.next().css({"top": $el[0].offsetTop, "left": $parent.outerWidth() - 4});
        }

        return false;
    });
});

const navbarSearchToggle = document.getElementById("nav-search-toggle");
const navbarSearchForm = document.getElementById("nav-search");

navbarSearchToggle.addEventListener("click", () => {
    const style = navbarSearchForm.style;
    if (style.visibility === "hidden") {
        style.visibility = "visible";
        style.opacity = 1;
    } else {
        style.visibility = "hidden";
        style.opacity = 0;
    }
});



