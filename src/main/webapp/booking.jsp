<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Form</title>
    <link rel="stylesheet" type="text/css" href="/static/styles.css">
</head>
<body>
<!--
COMMENTS:
    Different forms may need to be implemented for better organization of inputs:
For example, the form for payment information could have a different structure than the one for reservation details
-->

<div>
    <form id="bookingForm" action="/" method="get">
        <div class="form-group">
            <label for="checkin">Check In:</label>
            <input type="date" name="checkin" value="${checkin}"/>
        </div>
        <div class="form-group">
            <label for="checkout">Check Out:</label>
            <input type="date" name="checkout" value="${checkout}"/>
        </div>
        <div class="form-group">
            <label for="numGuests">Number of Guests:</label>
            <input type="number" name="numGuests" min="1" max="4" value="${numGuests}"/>
        </div>
        <div class="form-group">
            <label for="numRooms">Number of Rooms:</label>
            <input type="number" name="numRooms" min="1" value="${numRooms}"/>
        </div>
        <input type="submit" value="Search">
    </form>
</div>

<div class="roomListHeader">
    <h3>List of Rooms</h3>
</div>
<div class="support-grid"></div>
<div class="band">
<c:forEach items="${roomTypes}" var="roomType">
    <div class="item-4">
        <div class="card">
            <img src="/static/roomTest.jpg" alt="Room Image">
            <article>
                <h1>${roomType.name}</h1>
                <p></p>
                <span>${roomType.description}</span>
            </article>
            <div class="book center">
                <a href="/reserve?checkin=${checkin}&checkout=${checkout}&numGuests=${numGuests}&numRooms=${numRooms}&room=${roomType.id}"><button class="button">Book Room</button></a>
            </div>
        </div>
    </div>
</c:forEach>
</div>

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

    <h1>Reservation Form</h1>

    <h1>Personal Information</h1>
    <form action="/process-reservation" method="post">
        <label for="roomType">RoomType:</label>
        <select name="roomType" id="roomType" required>
            <option value="Standard">Standard</option>
            <option value="Deluxe">Deluxe</option>
            <option value="Executive">Executive</option>
            <option value="Family">Family</option>
            <option value="Studio">Studio</option>
        </select><br>

        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required><br>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required><br>

        <label for="phoneNumber">Phone Number:</label>
        <input type="tel" id="phoneNumber" name="phoneNumber" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email"><br>

    <h1>Address Information</h1>
        <!--Street Address, city, state, zip code, country-->
    <form action="/process-Address" method="post">
        <label for="streetAddress">Street Address:</label>
        <input type="text" id="streetAddress" name="streetAddress" required><br>

        <label for="city">City:</label>
        <input type="text" id="city" name="city" required><br>

        <label for="zipCode">Zip Code:</label>
        <input type="text" id="zipCode" name="zipCode" required>

        <label for="country">Country:</label>
        <select id="country" name="country" required>
            <option value="United States">United States</option>
            <option value="Canada">Canada</option>
            <option value="United Kingdom">United Kingdom</option>
            <option value="Wakanda">Wakanda</option>
            <option value="Australia">Australia</option>
            <option value="Germany">Germany</option>
            <option value="China">China</option>
            <option value="Brazil">Brazil</option>
            <option value="France">France</option>
            <option value="India">India</option>
            <option value="Japan">Japan</option>
            <option value="Italy">Italy</option>
            <option value="Greece">Greece</option>
            <option value="Belgium">Belgium</option>
            <option value="Colombia">Colombia</option>
            <option value="Indonesia">Indonesia</option>
            <option value="Denmark">Denmark</option>
            <option value="Algeria">Algeria</option>
            <option value="Chile">Chile</option>
            <option value="Austria">Austria</option>
            <option value="Bangladesh">Bangladesh</option>
            <option value="Kazakhstan">Kazakhstan</option>
            <option value="Czechia">Czechia</option>
            <option value="Afhganistan">Afghanistan</option>
            <!-- Add more if needed -->
        </select><br>

        <label for="state">State:</label>
        <select name="state" id="state" required>
            <option value="AL">Alabama</option>
            <option value="AK">Alaska</option>
            <option value="AZ">Arizona</option>
            <option value="AR">Arkansas</option>
            <option value="CA">California</option>
            <option value="CO">Colorado</option>
            <option value="CT">Connecticut</option>
            <option value="DE">Delaware</option>
            <option value="FL">Florida</option>
            <option value="GA">Georgia</option>
            <option value="HI">Hawaii</option>
            <option value="ID">Idaho</option>
            <option value="IL">Illinois</option>
            <option value="IN">Indiana</option>
            <option value="IA">Iowa</option>
            <option value="KS">Kansas</option>
            <option value="KY">Kentucky</option>
            <option value="LA">Louisiana</option>
            <option value="ME">Maine</option>
            <option value="MD">Maryland</option>
            <option value="MA">Massachusetts</option>
            <option value="MI">Michigan</option>
            <option value="MN">Minnesota</option>
            <option value="MS">Mississippi</option>
            <option value="MO">Missouri</option>
            <option value="MT">Montana</option>
            <option value="NE">Nebraska</option>
            <option value="NV">Nevada</option>
            <option value="NH">New Hampshire</option>
            <option value="NJ">New Jersey</option>
            <option value="NM">New Mexico</option>
            <option value="NY">New York</option>
            <option value="NC">North Carolina</option>
            <option value="ND">North Dakota</option>
            <option value="OH">Ohio</option>
            <option value="OK">Oklahoma</option>
            <option value="OR">Oregon</option>
            <option value="PA">Pennsylvania</option>
            <option value="RI">Rhode Island</option>
            <option value="SC">South Carolina</option>
            <option value="SD">South Dakota</option>
            <option value="TN">Tennessee</option>
            <option value="TX">Texas</option>
            <option value="UT">Utah</option>
            <option value="VT">Vermont</option>
            <option value="VA">Virginia</option>
            <option value="WA">Washington</option>
            <option value="WV">West Virginia</option>
            <option value="WI">Wisconsin</option>
            <option value="WY">Wyoming</option>
        </select><br>

        <h1>Reservation Details</h1>
        <label for="numAdults">Number of Adults</label>
            <select name="numAdults" id="numAdults" required>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
            </select><br>
        <input type="number" id="numAdults" name="numAdults" ><br>
        <label for="numChild">Number of Children</label>
            <select name="numChild" id="numChild">
                <option value="0">0</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                </select><br>
            <input type="number" id="numChild" name="numChild" ><br>
        <label for="numSeniors">Number of Seniors</label>
            <select name="numSeniors" id = "numSeniors">
                <option value="0">0</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                </select><br>
            <input type="number" id="numSeniors" name="numSeniors" ><br>

        <input type="submit" value="Next">
    </form>




    <h1>Payment Information</h1>
    <form action="/process-payment" method="post">
        <label for="cardNumber">Card Number:</label>
        <input type="text" id="cardNumber" name="cardNumber" required><br>

        <label for="cardExpiration">Card Expiration (MM/YYYY):</label>
        <input type="text" id="cardExpiration" name="cardExpiration" required><br>

        <label for="ccvNumber">CCV Number:</label>
        <input type="text" id="ccvNumber" name="ccvNumber"><br>

        <label for="nameOnCard">Name on Card:</label>
        <input type="text" id="nameOnCard" name="nameOnCard" required><br>

        <label for="cardType">Card Type:</label>
        <select id="cardType" name="cardType">
            <option value="Visa">Visa</option>
            <option value="MasterCard">MasterCard</option>
            <option value="American Express">American Express</option>
            <option value="Discover">Discover</option>
        </select><br>

        <label for="zipCode">ZIP Code:</label>
        <input type="text" id="zipCode" name="zipCode"><br>

        <input type="submit" value="Complete Reservation">
    </form>
</body>
</html>
