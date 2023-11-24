document.addEventListener("DOMContentLoaded", function () {
    var navbar = document.querySelector('.navbar');
    var navbarLogo = document.querySelector('.navbar-logo');

    function updateNavbar() {
        var scrollPercentage = (window.scrollY / window.innerHeight) * 100;
        navbar.style.backgroundColor = `rgba(20, 32, 46, ${scrollPercentage / 100})`;
        navbarLogo.style.color = `rgba(255, 255, 255, ${scrollPercentage / 100})`;
    }

    window.addEventListener('scroll', function () {
        updateNavbar();
    });

    updateNavbar();
});
