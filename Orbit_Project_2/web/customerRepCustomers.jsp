<%-- 
    Document   : customerRepCustomers
    Created on : Apr 23, 2015, 12:02:54 AM
    Author     : Jessica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script> 
        <script src="http://malsup.github.com/jquery.form.js"></script> 

        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        

        <script src="js/ui.js"></script>
        <!--        <script src="js/orbitfunction.js"></script>-->
        <script src="js/customerRepFunctions.js"></script>

    </head>
    <body>
        <div id="layout">
            <!-- Menu toggle -->
            <a href="#menu" id="menuLink" class="menu-link">
                <!-- Hamburger icon -->
                <span></span>
            </a>

            <div id="menu">

            </div>

            <div id="main">
                <div class="header">
                    <h1>Book Your Travel</h1>
                    <h2>Welcome, Customer Rep</h2>
                </div>

                <div class="content">

                    <h2 class="content-subhead">Customers</h2>

                    <fieldset>

                        <div class="pure-g-r">

                            <div class="pure-u-1-3">
                                <label for="add-customer-option" class="pure-radio">
                                    <input id="add-customer-option" type="radio" name="custActions" value="add">
                                    Add
                                </label>
                            </div>

                            <div class="pure-u-1-3">
                                <label for="update-customer-option" class="pure-radio">
                                    <input id="update-customer-option" type="radio" name="custActions" value="update">
                                    Update
                                </label>
                            </div>

                            <div class="pure-u-1-3">
                                <label for="delete-customer-option" class="pure-radio">
                                    <input id="delete-customer-option" type="radio" name="custActions" value="delete">
                                    Delete
                                </label>
                            </div>

                        </div>


                        <div>&nbsp</div>


                        <script>

                            $('#add-customer-option').click(function() {
                                if ($('#add-customer-option').is(':checked')) {
                                    $('#add-customer').load('loadAddCustomer.html');
                                }
                            });

                            $('#update-customer-option').click(function() {
                                 if ($('#update-customer-option').is(':checked')) {
                                    $('#add-customer').load('loadUpdateCustomer.html');
                                }
                            });

                            $('#delete-customer-option').click(function() {
                                 if ($('#delete-customer-option').is(':checked')) {
                                    $('#add-customer').load('loadDeleteCustomer.html');
                                }
                            });

                        </script>

                        <div id='add-customer'>


                        </div>

                    </fieldset>

                    <div>&nbsp</div>

                        <!--<button type="submit" class="pure-button pure-button-primary">Refresh</button>-->
                        <!--<span id="signupError" style="color:red">${requestScope.errorMessage}</span>-->

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
    </body>
</html>
