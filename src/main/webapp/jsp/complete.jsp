<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Form</title>
    <link rel="stylesheet" type="text/css" href="/static/nav.css">

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

<h1>Your reservation is complete!</h1>
<p>Your confirmation code is: ${sessionScope.confirmationId}</p>

</body>
</html>
