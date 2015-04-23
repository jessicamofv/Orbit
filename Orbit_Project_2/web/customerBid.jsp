<%-- 
    Document   : customerBid
    Created on : Apr 22, 2015, 11:34:15 PM
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

        <script src="js/customerFunctions.js"></script>
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

                    <h2 class="content-subhead">Bid</h2>

                    <fieldset>

                        <div id="bidRadioList">

                            <div class="pure-g-r">

                                <div class="pure-u-1-3">
                                    <label for="currentBid" class="pure-radio">
                                        <input id="currentBid" type="radio" name="bidActions" value="currentBid">
                                         Current Bid
                                    </label>
                                </div>

                                <div class="pure-u-1-3">
                                    <label for="bidHistory" class="pure-radio">
                                        <input id="bidHistory" type="radio" name="bidActions" value="bidHistory">
                                         Bid History
                                    </label>
                                </div>

                                <div class="pure-u-1-3">
                                    <label for="placeBid" class="pure-radio">
                                        <input id="placeBid" type="radio" name="bidActions" value="placeBid">
                                         Place Bid
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="pure-g-r">

                                <div class="pure-u-1-3" id="bid-div0">

                                    <div>&nbsp;</div>

                                    <fieldset class="pure-group">

                                        <legend><strong>Enter Reverse Auction Information</strong></legend>

                                        <form id='current-bid-form' method="post" action="currentBid">

                                            <input id="accountNo" name="accountNo" type="hidden" />

                                            <label for="airlineID">Airline ID*:</label>                       
                                            <input type="text" name="airlineID" class="" placeholder="AA" required>

                                            <label for="flightNo">Flight Number*:</label>    
                                            <input type="text" name="flightNo" class="" placeholder="111" required>

                                            <label for="seatClass">Seat Class*:</label>                   
                                            <input type="text" name="seatClass" class="" placeholder="Economy" required>

                                            <button type='submit' class='pure-button'>View current bid for this reverse auction!</button>                            

                                        </form>

                                    </fieldset>
                                </div>
        
                                <div class="pure-u-1-3" id="bid-div1">

                                    <div>&nbsp;</div>

                                    <fieldset class="pure-group">

                                        <legend><strong>Enter Reverse Auction Information</strong></legend>

                                        <form id='bid-history-form' method="post" action="bidHistory">

                                            <label for="airlineID">Airline ID*:</label>                    
                                            <input type="text" name="airlineID" class="" placeholder="AA" required>

                                            <label for="flightNo">Flight Number*:</label>                    
                                            <input type="text" name="flightNo" class="" placeholder="111" required>

                                            <label for="seatClass">Seat Class*:</label>                
                                            <input type="text" name="seatClass" class="" placeholder="Economy" required>

                                            <button type='submit' class='pure-button'>View bid history for this reverse auction!</button>                            

                                        </form>

                                    </fieldset>
                                </div>

                                <div class="pure-u-1-3" id="bid-div2">

                                    <div>&nbsp;</div>

                                    <fieldset class="pure-group">

                                        <legend><strong>View Available Reverse Auctions</strong></legend>

                                        <form id='available-auctions-form' method="post" action="viewAvailableAuctions">

                                            <input id="accountNo" name="accountNo" type="hidden" />

                                            <button type='submit' class='pure-button'>View Available Reverse Auctions!</button>                            

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