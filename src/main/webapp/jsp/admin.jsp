<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <link rel="stylesheet" type="text/css" href="/static/styles.css">
</head>
<body>

<table border="1">
	<tr>
		<th>Reservation ID</th>
		<th>Start Date</th>
		<th>End Date</th>
		<th>Customer</th>
		<th>Room Type</th>
		<th>Number of Rooms</th>
		<th>Number of Guests</th>
		<th>Billing Number</th>
	</tr>
	<c:forEach items="${reservations}" var="reservation">
		<tr>
			<td>${reservation.id}</td>
			<td>${reservation.startDate}</td>
			<td>${reservation.endDate}</td>
			<td>${reservation.customer.id}</td>
			<td>${reservation.roomType.id}</td>
			<td>${reservation.numberOfRooms}</td>
			<td>${reservation.numberOfGuests}</td>
			<td>${reservation.billing.id}</td>
		</tr>
	</c:forEach>
</table>

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
<form action="/admin/addRoomType" method="post">
	<h1>Add Room Type</h1>
	<div class="addRoomType">
		<label for="name">Name:</label>
		<input type="text" id="name" name="name" required>

		<label for="description">Description:</label>
		<input type="text" id="description" name="description" required>

		<label for="rules">Rules:</label>
		<input type="text" id="rules" name="rules" required>

		<label for="numberOfBeds">Number of Beds:</label>
		<input type="text" id="numberOfBeds" name="numberOfBeds" required>

		<label for="price">Price:</label>
		<input type="text" id="price" name="price" required>
	</div>
	<input type="submit" value="Submit">
</form>


<table border="1">
	<tr>
		<th>id</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Phone Number</th>
		<th>Email</th>
		<th>Country</th>
		<th>State</th>
		<th>City</th>
		<th>Address</th>
		<th>Zip Code</th>
	</tr>
	<c:forEach items="${customers}" var="customer">
		<tr>
			<td>${customer.id}</td>
			<td>${customer.firstName}</td>
			<td>${customer.lastName}</td>
			<td>${customer.phoneNumber}</td>
			<td>${customer.email}</td>
			<td>${customer.state}</td>
			<td>${customer.city}</td>
			<td>${customer.address}</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>
