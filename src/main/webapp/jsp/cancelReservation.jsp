<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Cancel Reservation?</title>
	<link rel="stylesheet" type="text/css" href="/static/styles.css">
</head>
<body>

<p>Reservation id: ${reservation.id}</p>
<p>Checkin Date: ${reservation.startDate}</p>
<p>Checkout Date: ${reservation.endDate}</p>
<p>Room Type: ${reservation.roomType.name}</p>
<p>Rooms: ${reservation.numberOfRooms}</p>
<p>Guests: ${reservation.numberOfGuests}</p>
<p>Are you sure you want to cancel your reservation?</p>
<form action="/cancelReservation" method="post">
	<button>Yes</button>
</form>
<a href="/"><button>No</button></a>

</body>
</html>
