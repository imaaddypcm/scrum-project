<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reservation Details</title>
</head>
<body>

    <h1>Reservation Details</h1>

    <c:if test="${not empty reservation}">
        <p>Reservation id: ${reservation.id}</p>
        <p>Guest: ${reservation.customer.firstName} ${reservation.customer.lastName}</p>
        <p>Room Type: ${reservation.roomType.name}</p>
        <p>Start Date: ${reservation.startDate}</p>
        <p>End Date: ${reservation.endDate}</p>
        <p>Number of Rooms: ${reservation.numberOfRooms}</p>
        <p>Number of Guests: ${reservation.numberOfGuests}</p>
        <p>Billing Number: ${reservation.billing.id}</p>
    </c:if>


</body>
</html>
