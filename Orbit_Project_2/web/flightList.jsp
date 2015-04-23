<%-- 
    Document   : loginPage
    Created on : Apr 27, 2014, 5:16:31 AM
    Author     : RahulSarna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Orbit Travel Reservation System Website for CSE 305">

    <title>Orbit Travel Reservation &ndash; CSE 305 Project</title>

        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.4.2/pure.css">
        <link rel="stylesheet" href="css/layouts/side-menu.css">  
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>      
        <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
        <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

        <script src="js/ui.js"></script>
        <script src="js/orbitfunction.js"></script>  
        
</head>
    <body>

              
        <div id="layout">
            <!-- Menu toggle -->
            <a href="#menu" id="menuLink" class="menu-link">
            <!-- Hamburger icon -->
                <span></span>
            </a>

     <div id="menu"></div>

    <div id="main">
        <div class="header">
            <h1>Book Your Travel</h1>
            <h2>Quick and Cheap Flights</h2>
        </div>

        <div class="content">
            
            <h2 class="content-subhead">Sign in or Select an option</h2>
            
            
            <div>
    <style scoped>

        .button-success,
        .button-error,
        .button-warning,
        .button-secondary {
            color: white;
            border-radius: 4px;
            text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
        }

        .button-success {
            background: rgb(28, 184, 65); /* this is a green */
        }

        .button-error {
            background: rgb(202, 60, 60); /* this is a maroon */
        }

        .button-warning {
            background: rgb(223, 117, 20); /* this is an orange */
        }

        .button-secondary {
            background: rgb(66, 184, 221); /* this is a light blue */
        }

    </style>

    <button class="button-success pure-button">Success Button</button>
    <button class="button-error pure-button">Error Button</button>
    <button class="button-warning pure-button">Warning Button</button>
    
             <table id="test-data" class="pure-table">
             </table>           
            
</div>

            
            <h2 class="content-subhead">We Love Travel</h2>
            <p>
                     Welcome to Orbit Travel - Your Online Travel Agency!
                     As one of the Btony Brook's leading online travel student website, we make it easy to plan your next business or leisure trip.                     
                     Explore our site on mobile and Facebook page to discover destination ideas, get information about flights , then book and purchase your trip.
                     Wherever you go, we're with you every step of the way!
                     
            </p>

            <div class="pure-g">
                <div class="pure-u-1-4">
                    <img class="pure-img-responsive" src="http://farm3.staticflickr.com/2875/9069037713_1752f5daeb.jpg" alt="Peyto Lake">
                </div>
                <div class="pure-u-1-4">
                    <img class="pure-img-responsive" src="http://farm3.staticflickr.com/2813/9069585985_80da8db54f.jpg" alt="Train">
                </div>
                <div class="pure-u-1-4">
                    <img class="pure-img-responsive" src="http://farm6.staticflickr.com/5456/9121446012_c1640e42d0.jpg" alt="T-Shirt Store">
                </div>
                <div class="pure-u-1-4">
                    <img class="pure-img-responsive" src="http://farm8.staticflickr.com/7357/9086701425_fda3024927.jpg" alt="Mountain">
                </div>
            </div>

            <h2 class="content-subhead">Project Specification</h2>
            <p>
                The basic idea behind your on-line travel reservation system is that it will allow customers to use the web to browse/search the contents of your database (at least that part you want the customer to see) and to make flight reservations over the web. Your web site should allow users to make both domestic and international reservations. It should also allow users to query the database for available flights (direct or indirect) between a pair of cities for a given date and "approximate" time.

                Your system should also support reverse auction, in which individuals specify the price they are willing to pay for a seat and the airlines either agree to sell it at that price or not. Reverse auction sites include priceline.com and expedia.com, a Microsoft-owned travel site that has a feature enabling customers to name their price.

                Actual travel sites allow you to do a lot more than simply make flight reservations. For example, you can book a rental car or a hotel room. Due to time limitations, we will stick to flight reservations only this semester.

                Your database system must be based on the specifications and requirements that follow.     
            </p>
        </div>
    </div>
</div>

<script src="js/ui.js"></script>

</body>
</html>
