<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reserve Room</title>
    <link rel="stylesheet" type="text/css" href="/static/styles.css">
</head>
<body>
    <div class="navbar">
        <span class="navbar-logo">Four Corners</span>
        <div class="navbar-links">
            <a href="/">Return</a>
            <a href="/find-reservation">My Reservations</a>
        </div>
    </div>
<h1>Step 2</h1>
	<div style="width: 100%;">
        <div style="width: 30%; height: 100px; float: left;">
            <h1>Reservation Details:</h1>
            <p>Room type: ${type.name}</p>
            <p>Number of rooms: ${param.numRooms}</p>
            <p>Number of guests: ${param.numGuests}</p>
            <p>Start date: ${param.checkin}</p>
            <p>End date: ${param.checkout}</p>
            <p>Price: $${type.price}</p>
        </div>
        <div style="margin-left: 30%; height: 100px;">
    	<form action="" method="post">
            <input type="hidden" id="roomTypeName" name="roomType" value="${param.room}" readonly>
            <input type="hidden" id="numRooms" name="numRooms" value="${param.numRooms}" readonly>
            <input type="hidden" id="numGuests" name="numGuests" value="${param.numGuests}" readonly>
            <input type="hidden" id="checkin" name="checkin" value="${param.checkin}" readonly>
            <input type="hidden" id="checkout" name="checkout" value="${param.checkout}" readonly>
            <h1>Guest Details</h1>

            <div class="band">
                <label for="firstName">First Name:</label>
                <input type="text" id="firstName" name="firstName" required>

                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" required>

                <label for="phoneNumber">Phone Number:</label>
                <input type="tel" id="phoneNumber" name="phoneNumber" required>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
    		</div>

    		<h1>Payment Details</h1>
    		<div class="band">
                <label for="cardType">Card Type:</label>
                <select id="cardType" name="cardType" required>
                    <option value=""></option>
                    <option value="Visa">Visa</option>
                    <option value="MasterCard">MasterCard</option>
                    <option value="American Express">American Express</option>
                    <option value="Discover">Discover</option>
                </select>

                <label for="cardNumber">Card Number:</label>
                <input type="text" id="cardNumber" name="cardNumber" required>

                <label for="nameOnCard">Name on Card:</label>
                <input type="text" id="nameOnCard" name="nameOnCard" required>

                <label for="cardExpiration">Card Expiration (MM/YYYY):</label>
                <input type="text" id="cardExpiration" name="cardExpiration" required>

                <label for="cvcNumber">CVC Number:</label>
                <input type="text" id="cvcNumber" name="cvcNumber" required>

    			<label for="postalCode">Postal Code:</label>
                <input type="text" id="postalCode" name="postalCode" required>
    		</div>
    		<input type="submit" value="Submit">
    	</form>
        </div>
    </div>
</body>
</html>
