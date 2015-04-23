<%-- 
    Document   : employeeSignup
    Created on : May 3, 2014, 4:24:55 PM
    Author     : commy_000
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
            
            <!--<div id="menu">
                <div class="pure-menu pure-menu-open">
                    <a class="pure-menu-heading" href="#">Orbit Travel</a>


                    <!--class="menu-item-divided pure-menu-selected"-->

<!--                    <ul id="menu-options">
                        <li><a href="redirect.jsp">Home</a></li>
                        <li><a href="#">Book Flight</a></li>

                        <li><a href="loginPage.jsp">Login</a></li>

                        <li><a href="signup.jsp">Sign Up</a></li>
                    </ul>
                </div>
            </div>-->

            <div id="main">
                <div class="header">
                    <h1>Book Your Travel</h1>
                    <h2>Quick and Cheap Flights</h2>
                </div>

                <div class="content">

                    <h2 class="content-subhead">Employee Sign Up</h2>


                    <form class="pure-form pure-form-stacked" method="POST" action='signup'> <!-- TODO: add servlet to post to -->
                        <fieldset>
                            <legend>Sign up with your email</legend>



                            <div class="pure-g-r">


                                <div class="pure-u-1-3">                                                                                                                                                                                   

                                    <div class="pure-u-1 pure-u-med-1-3">
                                        <label for="username">Username/Email Address*</label>
                                        <input id="username" name="username" type="text" required>
                                    </div>

                                    <div class="pure-u-1 pure-u-med-1-8">
                                        <label for="password">Password*</label>
                                        <input id="password" name="password" type="password" required>
                                    </div>

                                    <div class="pure-u-1 pure-u-med-1-8">
                                        <label for="confirmPassword">Confirm Password*</label>
                                        <input id="confirm-password" name='confirm-password' type="password" required>
                                    </div>
                                    
                                    

                                </div>

                                <div class="pure-u-1-3">                                                                                                                                                                                   

                                    <div class="pure-u-1 pure-u-med-1-3">
                                        <label for="first-name">First Name*</label>
                                        <input id="first-name" name='first-name' type="text" required>
                                    </div>

                                    <div class="pure-u-1 pure-u-med-1-3">
                                        <label for="last-name">Last Name*</label>
                                        <input id="last-name" name='last-name' type="text" required>
                                    </div>

                                    <div class="pure-u-1 pure-u-med-1-3">
                                        <label for="address">Address*</label>
                                        <input id="address" name='address' type="text" required>
                                    </div>

                                    <div class="pure-u-1 pure-u-med-1-3">
                                        <label for="city">City*</label>
                                        <input id="city" name='city' type="text" required>
                                    </div>

                                    <div class="pure-u-1 pure-u-med-1-3">
                                        <label for="state">State*</label>
                                        <input id="state" name='state' type="text" required>
                                    </div>

                                    <div class="pure-u-1 pure-u-med-1-3">
                                        <label for="zip-code">Zip Code*</label>
                                        <input id="zip-code" name='zip' type="text" required>
                                    </div>

                                    <div class="pure-u-1 pure-u-med-1-3">
                                        <label for="telephone">Telephone</label>
                                        <input id="telephone" name='telephone' type="text">
                                    </div>

                                </div>



                                <div class="pure-u-1-3">                                                                                                                                                                                   

                                    <!--                                            <div class="pure-u-1 pure-u-med-1-3">
                                                                                    <label for="account-number">Account Number</label>
                                                                                    <input id="account-number" name='account-number' type="text">
                                                                                </div>
                                                                                
                                                                                <div class="pure-u-1 pure-u-med-1-3">
                                                                                    <label for="account-creation-date">Date Created</label>
                                                                                    <input id="account-creation-date" name="account-creation-date" type="date">
                                                                                </div>-->

                                    <div class="pure-u-1 pure-u-med-1-8">
                                        <label for="employeeType">I am a...</label>
                                        <label for="employeeTypeRep" class="pure-radio">
                                            <input id="employee-type-rep" type="radio" name="employee-type-radios" value="0" checked>
                                            Customer Representative
                                        </label>
                                        <label for="employeeTypeMgr" class="pure-radio">
                                            <input id="employee-type-mgr" type="radio" name="employee-type-radios" value="1">
                                            Manager
                                        </label>
                                    </div>
                                    
                                    <div class="pure-u-1 pure-u-med-1-3">
                                        <label for="socialSecurityNum">SSN*</label>
                                        <input id="social-security-num" name='social-security-num' type="text" required>
                                    </div>

                                    <div class="pure-u-1 pure-u-med-1-3">
                                        <label for="hourlyRate">Hourly Rate</label>
                                        <input id="hourly-rate" name='hourly-rate' type="text">
                                    </div>
                                    
                                    <div class="pure-u-1 pure-u-med-1-3">
                                        <label for="startDate">Start Date</label>
                                        <input id="start-date" name='start-date' type="date">
                                    </div>

                                    <!--                                            <div class="pure-u-1 pure-u-med-1-3">
                                                                                    <label for="rating">Rating</label>
                                                                                    <input id="rating" name='rating' type="text">
                                                                                </div>-->

                                </div>

                            </div>                                                                     

                        </fieldset>

                        <div>&nbsp</div>

                        <button type="submit" class="pure-button pure-button-primary">Create Account!</button>
                        <span id="signupError" style="color:red">${requestScope.errorMessage}</span>
                    </form>




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
