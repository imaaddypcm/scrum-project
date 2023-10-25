<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="backend.Room" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Rooms</title>
</head>
<body>
    <h1>List of Rooms</h1>
    <table border="1">
        <tr>
            <th>Room Number</th>
            <th>Room Type</th>
            <th>Price</th>
            <th>Description</th>
        </tr>
        <c:forEach items="${rooms}" var="room">
            <tr>
                <td>${room.roomNumber}</td>
                <td>${room.roomType}</td>
                <td>${room.price}</td>
                <td>${room.description}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
