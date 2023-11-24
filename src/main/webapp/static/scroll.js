function slowScrollTo(element, duration, offset) {
    var targetElement = document.getElementById(element);
    var targetPosition = targetElement.offsetTop - offset; // Subtract the offset
    var startPosition = window.pageYOffset;
    var distance = targetPosition - startPosition;
    var startTime = null;

    function animation(currentTime) {
        if (startTime === null) startTime = currentTime;
        var timeElapsed = currentTime - startTime;
        var scrollProgress = easeInOutQuad(timeElapsed, startPosition, distance, duration);
        window.scrollTo(0, scrollProgress);
        if (timeElapsed < duration) requestAnimationFrame(animation);
    }

    function easeInOutQuad(t, b, c, d) {
        t /= d / 2;
        if (t < 1) return c / 2 * t * t + b;
        t--;
        return -c / 2 * (t * (t - 2) - 1) + b;
    }

    requestAnimationFrame(animation);
}

function scrollToRooms() {
    slowScrollTo('roomList', 1000, 67);
}
