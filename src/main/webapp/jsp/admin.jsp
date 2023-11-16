<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <link rel="stylesheet" type="text/css" href="/static/styles.css">
</head>
<body>

	<form action="" method="post">
		<label for="roomType">Room Type:</label>
            <select id="roomType" name="roomType" required>
				<c:forEach items="${roomTypes}" var="roomType">
					<option value=${roomType}>${roomType.name}</option>
				</c:forEach>
            </select>
	</form>

</body>
</html>
