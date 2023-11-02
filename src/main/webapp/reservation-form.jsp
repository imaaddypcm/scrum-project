<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE,edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/static/styles.css">
    <style>
        body {
        display: flex;
        flex-direction: column; /* Display boxes in a column */
        justify-content: center;
        align-items: center;
        height: 175vh;
        }

        .section {
            border: 1px solid #ccc;
            padding: 20px;
            width: 400px;
            text-align: center;
            margin: 20px;
            background-color: #0073e66f;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .section h1 {
            margin: 0;
        }

        .section form {
            text-align: left;
        }

        .section label {
            display: block;
            margin-top: 10px;
        }

        .section input, .section select {
            width: 90%;
            padding: 10px;
            margin-top: 5px;
        }

        .section select {
            margin-top: 5px;
        }

        .section input[type="submit"] {
            width: auto;
            padding: 10px 20px;
            margin-top: 10px;
        }
    </style>
    <title>Reservation Form</title>
</head>
<body>


    <div class="section">
        <h1>Guest Details</h1>
        <form action="/ReservationServlet" method="post">
            <label for="roomType">Room Type:</label>
            <input type="text" id="roomType" name="roomType" value="${param.roomType}" required readonly><br>

            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name "firstName" required><br>

            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required><br>

            <label for="phoneNumber">Phone Number:</label>
            <input type="tel" id="phoneNumber" name="phoneNumber" required><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email"><br>
        </form>
    </div>

    <div class="section">
        <h1>Address Details</h1>
        <form action="/ReservationServlet" method="post">
            <label for="streetAddress">Street Address:</label>
            <input type="text" id="streetAddress" name="streetAddress" required><br>

            <label for="city">City:</label>
            <input type="text" id="city" name="city" required><br>

            <label for="zipCode">Zip Code:</label>
            <input type="text" id="zipCode" name="zipCode" required><br>

            <label for="country">Country:</label>
            <select id="country" name="country" required>
                <option value=""></option>
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
                <option value="Afghanistan">Afghanistan</option>
            </select><br>

            <label for="state">State:</label>
            <select name="state" id="state" required>
                <option value=""></option>
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
        </form>
    </div>

    <div class="section">
        <h1>Payment Details</h1>
        <form action="/ReservationServlet" method="post">
            <label for="cardType">Card Type:</label>
            <select id="cardType" name="cardType" required>
                <option value=""></option>
                <option value="Visa">Visa</option>
                <option value="MasterCard">MasterCard</option>
                <option value="American Express">American Express</option>
                <option value="Discover">Discover</option>
            </select><br>

            <label for="cardNumber">Card Number:</label>
            <input type="text" id="cardNumber" name="cardNumber" required><br>

            <label for="nameOnCard">Name on Card:</label>
            <input type="text" id="nameOnCard" name="nameOnCard" required><br>

            <label for="cardExpiration">Card Expiration (MM/YYYY):</label>
            <input type="text" id="cardExpiration" name="cardExpiration" required><br>

            <label for="cvcNumber">CVC Number:</label>
            <input type="text" id="cvcNumber" name="cvcNumber" required><br>

            <input type="submit" value="Submit">
        </form>
    </div>

    <div class="itinerary">
        <h1>Itinerary</h1>
        <p><strong>Check-In Date:</strong> ${checkin}</p>
        <p><strong>Check-Out Date:</strong> ${checkout}</p>
        <p><strong>Room Type:</strong> ${param.roomType}</p>

    </div>

</body>
</html>
