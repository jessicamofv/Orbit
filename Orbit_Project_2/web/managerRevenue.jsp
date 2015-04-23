<%-- 
    Document   : managerRevenue
    Created on : Apr 23, 2015, 1:05:27 AM
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

        <script src="js/managerFunctions.js"></script>

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

                    <h2 class="content-subhead">Revenue</h2>

                    <fieldset>
    <legend><strong>Revenue</strong></legend>

    <div id="revRadioList">

        <div class="pure-g-r">

            <div class="pure-u-1-3">


                <label for="revByFlight" class="pure-radio">
                    <input id="revByFlight" type="radio" name="revActions" value="revByFlight">
                     Revenue by flight
                </label>
            </div>
            <div class="pure-u-1-3">


                <label for="revByDest" class="pure-radio">
                    <input id="revByDest" type="radio" name="revActions" value="revByDest">
                     Revenue by Destination City
                </label>
            </div>
            <div class="pure-u-1-3">
                <label for="revByCust" class="pure-radio">
                    <input id="revByCust" type="radio" name="revActions" value="revByCust">
                     Revenue by Customer
                </label>
            </div>
            <div class="pure-u-1-3">
                <label for="custRepRev" class="pure-radio">
                    <input id="custRepRev" type="radio" name="revActions" value="custRepRev">
                   Customer Rep Max Revenue
                </label>
            </div>
            <div class="pure-u-1-3">
                <label for="custRev" class="pure-radio">
                    <input id="custRev" type="radio" name="revActions" value="custRev">
                     Customer Max Revenue
                </label>
            </div>
        </div>
    </div>

    <div class="pure-g-r">

            <div class="pure-u-1-3">
                <!-- display search-specific stuff here depending on what radio button is selected -->
                
            <div>&nbsp;</div>
                
                
                <div class="pure-u-1-4" id="rev-div0">
                    <!--                    <br>Enter flight ID and number: <br>-->

                    <fieldset class="pure-group">


                        <legend><strong>Enter Flight ID and Number</strong></legend>

                        <form id='findByFlight' method="post" action="revenueByFlight">
                            
                            <label for="resAirlineID">                        
                                Flight ID:   <input type="text" name="resAirlineID" class="" placeholder="AA">                        
                            </label>
                            
                            <label for="resFlightNo">                        
                                Flight Number: <input type="text" name="resFlightNo" class="" placeholder="111">
                            </label>
                            
                        <button type='submit' class='pure-button'>Find!</button>                            

                        </form>
                    </fieldset>
                </div>
            </div>

        
        
            <div class="pure-u-1-3" id="rev-div1">

                <div>&nbsp;</div>
                
                <!--                    <br>Enter customer name: <br>-->
                <fieldset class="pure-group">

                    <legend><strong>Revenue By Destination City</strong></legend>


                    <form id='findByDestination' method="post" action="revenueByDestination">

                    <label for='resrDestination'>
                        Enter Destination: <input type="text" name="destination" placeholder="London">
                    </label>

                        
                        <button type='submit' class='pure-button'>Find!</button>
                        
                    </form>

                </fieldset>
            </div>

        <div class="pure-u-1-3" id="rev-div2">

                <div>&nbsp;</div>
                
                <!--                    <br>Enter customer name: <br>-->
                <fieldset class="pure-group">

                    <legend><strong>Revenue by Customer</strong></legend>


                    <form id='findByCustomer' method="post" action="revenueByCustomer">

                    <label for='resrFirstName'>
                        Enter Customer Account Number: <input type="text" name="accountNo" placeholder="2001">
                    </label>

                        
                        <button type='submit' class='pure-button'>Find!</button>
                        
                    </form>

                </fieldset>
            </div>
        
        <div class="pure-u-1-3" id="rev-div3">

                <div>&nbsp;</div>
                
                <!--                    <br>Enter customer name: <br>-->
                <fieldset class="pure-group">

                    <legend><strong>Maximum Revenue by Customer Rep</strong></legend>


                    <form id='findMaxCustRep' method="post" action="findMaxCustRepRevenue">

                        
                        <button type='submit' class='pure-button'>Find!</button>
                        
                    </form>

                </fieldset>
            </div>
        
        <div class="pure-u-1-3" id="rev-div4">

                <div>&nbsp;</div>
                
                <!--                    <br>Enter customer name: <br>-->
                <fieldset class="pure-group">

                    <legend><strong>Maximum Revenue by Customer</strong></legend>


                    <form id='findMaxCustomer' method="post" action="findMaxCustomerRevenue">

                        
                        <button type='submit' class='pure-button'>Find!</button>
                        
                    </form>

                </fieldset>
            </div>
        

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
