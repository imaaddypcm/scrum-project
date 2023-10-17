<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Form</title>
</head>
<body>
    <h1>Reservation Form</h1>
    <form action="/process-reservation" method="post">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required><br>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required><br>

        <label for="phoneNumber">Phone Number:</label>
        <input type="tel" id="phoneNumber" name="phoneNumber" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email"><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required><br>

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
