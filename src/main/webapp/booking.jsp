<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE,edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/static/styles.css">
    <title>Reservation</title>
</head>
<body>
    <div class="navbar">
        <img src="/static/hotel.png" alt="Your Logo" class="navbar-logo">
        <div class="navbar-links">
            <!--These don't really have a purpose except to fill the nav bar-->
            <a href="#">Home</a>
            <a href="#">About</a>
            <a href="#">Special Offers</a>
            <a href="#">Contact Us</a>
        </div>
    </div>

    <div class="search-form">
        <form action="/search" method="get">
            <label for="checkin">Check-In:</label>
            <input type="date" name="checkin" id="checkin" required>
            <label for="checkout">Check-Out:</label>
            <input type="date" name="checkout" id="checkout" required>
            <label for="rooms">Rooms:</label>
            <select name="rooms" id="rooms">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
            </select>
            <label for="guests">Guests:</label>
            <select name="guests" id="guests">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
            </select>
            <button type="submit" class="button">Search</button>
        </form>
    </div>

    <div class="room-container">
        <c:forEach items="${roomTypes}" var="roomType">
            <div class="room">
                <c:choose>
                    <c:when test="${roomType.name == 'Luxury'}">
                        <img src="/static/room.jpg" alt="Luxury">
                    </c:when>
                    <c:when test="${roomType.name == 'Executive'}">
                        <img src="/static/room1.jpg" alt="Executive">
                    </c:when>
                    <c:when test="${roomType.name == 'Family'}">
                        <img src="/static/room2.jpg" alt="Family">
                    </c:when>
                    <c:otherwise>
                        <img src="/static/room3.jpg" alt="Studio">
                    </c:otherwise>
                </c:choose>
                <div class="room-content">
                    <h2>${roomType.name}</h2>
                    <h2>$00.00/night</h2>
                    <p>${roomType.description}</p>

                    <a href="reservation-form.jsp?roomType=${roomType.name}&checkin=${param.checkin}&checkout=${param.checkout}">
                        <button class="button">Book Now</button>
                    </a>


                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
