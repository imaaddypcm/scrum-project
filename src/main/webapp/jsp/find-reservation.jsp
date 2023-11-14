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
	<h1>Find my Reservation</h1>
	<form id="emailForm" action="" method="post">
		<c:if test = "${not empty error}">
			<p>Invalid email or reservation number!</p>
		</c:if>
		<label for="email">Email:</label>
		<input type="label" id="email" name="email" required>

		<label for="reservationNumber">Reservation number:</label>
		<input type="label" id="reservationNumber" name="reservationNumber" required>

		<input type="submit" value="Submit">
	</form>
</body>
</html>

