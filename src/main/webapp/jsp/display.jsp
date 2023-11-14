<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Find Your Hotel Reservation</title>
	<link rel="stylesheet" type="text/css" href="/static/styles.css">
</head>
<body>

<c:if test="${not empty reservation}">
<p>Reservation id: ${reservation.id}</p>
<p>Checkin Date: ${reservation.startDate}</p>
<p>Checkout Date: ${reservation.endDate}</p>
<p>Room Type: ${reservation.roomType}</p>
<p>Rooms: ${reservation.numberOfRooms}</p>
<p>Guests: ${reservation.numberOfGuests}</p>
</c:if>
<c:if test="${empty reservation}">
<h1>Stale request</h1>
</c:if>
</body>
</html>
