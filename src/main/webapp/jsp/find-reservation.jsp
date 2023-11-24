<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find Your Hotel Reservation</title>
    <link rel="stylesheet" type="text/css" href="/static/nav.css">
	<link rel="stylesheet" type="text/css" href="/static/find-reservation.css">

</head>
<body>
	<div class="navbar">
        <span class="navbar-logo">Four Corners</span>
        <div class="navbar-links">
            <a href="#">Home</a>
            <a href="#">About</a>
            <a href="/find-reservation">My Reservations</a>
            <a href="#">Help</a>
        </div>
    </div>

	<div class="form-container">
		<h1>Find my Reservation</h1>
		<form id="emailForm" action="" method="post">
			<c:if test="${not empty error}">
				<p>Invalid email or reservation number!</p>
			</c:if>
			<label for="email">Email:</label>
			<input type="label" id="email" name="email" required>

			<label for="reservationNumber">Reservation number:</label>
			<input type="label" id="reservationNumber" name="reservationNumber" required>

			<input type="submit" value="Submit">
		</form>
	</div>
</body>
</html>
