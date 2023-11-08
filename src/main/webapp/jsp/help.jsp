<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FAQ</title>
    <link rel="stylesheet" type="text/css" href="/static/styles.css">
</head>

<body>
    <div>
        <h1>FAQ</h1>

        <button class="accordion">How can I create a reservation?</button>
        <div class="panel">
        <p>You can create a reservation <a href="/booking">here</a>.</p>
        </div>

        <button class="accordion">How can I view my reservation information?</button>
        <div class="panel">
        <p>You can view your reservation information <a href="/find-reservation">here</a>.</p>
        </div>

        <button class="accordion">How can I cancel my reservation?</button>
        <div class="panel">
        <p>Canceling online is unavailable at the moment. Please call (555) 555-5555 for cancelation inquiries.</p>
        </div>

        <script>
        var acc = document.getElementsByClassName("accordion");
        var i;

        for (i = 0; i < acc.length; i++) {
            acc[i].addEventListener("click", function() {
                this.classList.toggle("active");
                var panel = this.nextElementSibling;
                if (panel.style.display === "block") {
                panel.style.display = "none";
                } else {
                panel.style.display = "block";
                }//end else
            });
        }
        </script>
    </div>
</body>

</html>
