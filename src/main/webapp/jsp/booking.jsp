<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Form</title>
    <link rel="stylesheet" type="text/css" href="/static/styles.css">


</head>
<body>

    <div class="navbar">
        <span class="navbar-logo">Four Corners</span>
        <div class="navbar-links">
            <a href="#">Home</a>
            <a href="#">About</a>
            <a href="/find-reservation">My Reservations</a>
            <!--Need to connect help-->
            <a href="#">Help</a>
        </div>
    </div>


<div>
    <form id="bookingForm" action="/" method="get">
        <div class="form-group">
            <label for="checkin">Check In:</label>
            <input type="date" name="checkin" value="${checkin}"/>
        </div>
        <div class="form-group">
            <label for="checkout">Check Out:</label>
            <input type="date" name="checkout" value="${checkout}"/>
        </div>
        <div class="form-group">
            <label for="numGuests">Number of Guests:</label>
            <input type="number" name="numGuests" min="1" max="4" value="${numGuests}"/>
        </div>
        <div class="form-group">
            <label for="numRooms">Number of Rooms:</label>
            <input type="number" name="numRooms" min="1" value="${numRooms}"/>
        </div>
        <input type="submit" value="Modify">
    </form>
</div>

<div class="roomListHeader">
    <h3>List of Rooms</h3>
</div>
<div class="room-container">
    <c:forEach items="${roomTypes}" var="roomType">
        <div class="room">
            <c:choose>
                <c:when test="${roomType.name == 'Luxury'}">
                    <img src="/static/room1.jpg" alt="Luxury">

                </c:when>
                <c:when test="${roomType.name == 'Executive'}">
                    <img src="/static/room2.jpg" alt="Executive">
                </c:when>
                <c:when test="${roomType.name == 'Deluxe Suite'}">
                    <img src="/static/room3.jpg" alt="Family">

                </c:when>
                <c:otherwise>
                    <img src="/static/room4.jpg" alt="Studio Suite">
                </c:otherwise>
            </c:choose>
            <div class="room-content">
                <h2>${roomType.name}</h2>
                <h2>$${roomType.price}/night</h2>
                <p>${roomType.description}</p>
                <div class="book center">
                    <a href="/reserve?checkin=${checkin}&checkout=${checkout}&numGuests=${numGuests}&numRooms=${numRooms}&room=${roomType.id}"><button class="button">Book Room</button></a>
                </div>
                 </div>
             </div>
    </c:forEach>
</div>

    <table border="1">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Rules</th>
            <th>ID</th>
        </tr>
        <c:forEach items="${roomTypes}" var="roomType">
            <tr>
                <td>${roomType.id}</td>
                <td>${roomType.name}</td>
                <td>${roomType.description}</td>
                <td>${roomType.rules}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
