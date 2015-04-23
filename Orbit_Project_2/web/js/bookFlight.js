var passCount = 0;
var totalFare;
var bookingFee;
var economyFare;
var firstFare;
var businessFare;
var economyCount;
var firstCount;
var businessCount;
var seatClassValues;
var economyMealOptions;
var firstMealOptions;
var businessMealOptions;
var mealOptions;

$(document).ready(function(){
    
    $('.book-flight').click(function(){
        var buttonId = this.id;
        var buttonIdNum = buttonId.substring(4);
        var cellIdNum = parseInt(buttonIdNum) - 2;
        var cellId = "cell" + cellIdNum;
        var airlineIDCell = document.getElementById(cellId);
        var airlineIDText = $(airlineIDCell).text();
        localStorage.setItem("airlineID", airlineIDText);
        
        cellIdNum = parseInt(buttonIdNum) - 1;
        cellId = "cell" + cellIdNum;
        var flightNoCell = document.getElementById(cellId);
        var flightNoText = $(flightNoCell).text();
        localStorage.setItem("flightNo", flightNoText);
        
        var economyIdNum = "economy" + buttonIdNum;
        var firstIdNum = "first" + buttonIdNum;
        var businessIdNum = "business" + buttonIdNum;
        var economyInput = document.getElementById(economyIdNum);
        var firstInput = document.getElementById(firstIdNum);
        var businessInput = document.getElementById(businessIdNum);
        economyFare = $(economyInput).val();
        firstFare = $(firstInput).val();
        businessFare = $(businessInput).val();
        localStorage.setItem("economyFare", economyFare);
        localStorage.setItem("firstFare", firstFare);
        localStorage.setItem("businessFare", businessFare);
    });
    
    $('#airlineID').val(localStorage.getItem("airlineID"));
    $('#flightNo').val(localStorage.getItem("flightNo"));
    
    $('#passCount').val(passCount);
    
    economyFare = localStorage.getItem("economyFare");
    firstFare = localStorage.getItem("firstFare");
    businessFare = localStorage.getItem("businessFare");
    
    economyCount = 1;
    firstCount = 0;
    businessCount = 0;
    
    seatClassValues = [];
    seatClassValues[0] = "Economy";
    
    totalFare = economyCount * economyFare + firstCount * firstFare + businessCount * businessFare;
    totalFare = totalFare.toFixed(2);
    bookingFee = totalFare * .1;
    bookingFee = bookingFee.toFixed(2);
    $('#totalFareText').text("$" + totalFare);
    $('#bookingFeeText').text("$" + bookingFee);
    $('#totalFare').val(totalFare);
    $('#bookingFee').val(bookingFee);
    
    economyMealOptions = "<option>Chips</option>\n"
			 + "<option>Chips and Salsa</option>\n"
			 + "<option>Cheese and Crackers</option>\n"
			 + "<option>Pretzels</option>\n"
			 + "<option>Pretzels and Dip</option>\n"
				   
    firstMealOptions = "<option>Fish and Chips</option>\n"
                       + "<option>Sushi</option>\n"
                       + "<option>Steak</option>\n"
                       + "<option>Chicken</option>\n"
                       + "<option>Salad</option>\n";

    businessMealOptions = "<option>Steak</option>\n"
                          + "<option>Chicken</option>\n"
                          + "<option>Salad</option>\n";
                  
    mealOptions = [];
    mealOptions["Economy"] = economyMealOptions;
    mealOptions["First"] = firstMealOptions;
    mealOptions["Business"] = businessMealOptions;
    
    $('#addPassenger').click(function(){
        if (passCount === 0)
        {
            $('#deletePassenger').toggleClass("pure-button-disabled");
        }
        
        passCount++;
        
        $('#passCount').val(passCount);
        
        seatClassValues[passCount] = "Economy";
        
        economyCount++;
        totalFare = economyCount * economyFare + firstCount * firstFare + businessCount * businessFare;
        totalFare = totalFare.toFixed(2);
        bookingFee = totalFare * .1;
        bookingFee = bookingFee.toFixed(2);
        $('#totalFareText').text("$" + totalFare);
        $('#bookingFeeText').text("$" + bookingFee);
        $('#totalFare').val(totalFare);
        $('#bookingFee').val(bookingFee);
        
        var passId = "pass" + passCount;
        var firstNameId = "firstName" + passCount;
        var lastNameId = "lastName" + passCount;
        var addressId = "address" + passCount;
        var cityId = "city" + passCount;
        var stateId = "state" + passCount;
        var zipId = "zip" + passCount;
        var seatNoId = "seatNo" + passCount;
        var seatClassId = "seatClass" + passCount;
        var mealId = "meal" + passCount;
        
        var newPassengerInfo = "<div id='" + passId + "'>";
        
        if (passCount > 1)
        {
            newPassengerInfo += "<div>&nbsp;</div>\n"
                                + "<hr>\n";
        }
        
        newPassengerInfo += "<h4>Additional Passenger</h4>\n"
                            + "<div class='pure-g-r'>\n"
                            + "<div class='pure-u-1-3'>\n"
                            + "<div class='pure-u-1 pure-u-med-1-3'>\n"
                            + "<label for='" + firstNameId + "'>First Name*:</label>\n"
                            + "<input id='" + firstNameId + "' name='" + firstNameId + "' type='text' required>\n"
                            + "</div>\n"
                            + "<div class='pure-u-1 pure-u-med-1-3'>\n"
                            + "<label for='" + lastNameId + "'>Last Name*:</label>\n"
                            + "<input id='" + lastNameId + "' name='" + lastNameId + "' type='text' required>\n"
                            + "</div>\n"
                            + "<div class='pure-u-1 pure-u-med-1-3'>\n"
                            + "<label for='" + addressId + "'>Address*:</label>\n"
                            + "<input id='" + addressId + "' name='" + addressId + "' type='text' required>\n"
                            + "</div>\n"
                            + "</div>\n"
                            + "<div class='pure-u-1-3'>\n"
                            + "<div class='pure-u-1 pure-u-med-1-3'>\n"
                            + "<label for='" + cityId + "'>City*:</label>\n"
                            + "<input id='" + cityId + "' name='" + cityId + "' type='text' required>\n"
                            + "</div>\n"
                            + "<div class='pure-u-1 pure-u-med-1-3'>\n"
                            + "<label for='" + stateId + "'>State*:</label>\n"
                            + "<input id='" + stateId + "' name='" + stateId + "' type='text' required>\n"
                            + "</div>\n"
                            + "<div class='pure-u-1 pure-u-med-1-3'>\n"
                            + "<label for='" + zipId + "'>Zip Code*:</label>\n"
                            + "<input id='" + zipId + "' name='" + zipId + "' type='text' required>\n"
                            + "</div>\n"
                            + "</div>\n"
                            + "<div class='pure-u-1-3'>\n"
                            + "<div class='pure-u-1 pure-u-med-1-3'>\n"
                            + "<label for='" + seatNoId + "'>Seat Number*:</label>\n"
                            + "<input id='" + seatNoId + "' name='" + seatNoId + "' type='text' required>\n"
                            + "</div>\n"
                            + "<div class='pure-u-1 pure-u-med-1-3'>\n"
                            + "<label for='" + seatClassId + "'>Seat Class*:</label>\n"
                            + "<select id='" + seatClassId + "' name='" + seatClassId + "' class='pure-input-1-2 seatClassSelect'>\n"
                            + "<option>Economy</option>\n"
                            + "<option>First</option>\n"
                            + "<option>Business</option>\n"
                            + "</select>\n"
                            + "</div>\n"
                            + "<div class='pure-u-1 pure-u-med-1-3 mealSelect'>\n"
                            + "<label for='" + mealId + "'>Meal*:</label>\n"
                            + "<select id='" + mealId + "' name='" + mealId + "' class='pure-input-1-2'>\n"
                            + economyMealOptions
                            + "</select>\n"
                            + "</div>\n"
                            + "</div>\n"
                            + "</div>\n"
                            + "</div>\n";
                       
        $('#passengers').append(newPassengerInfo);
    });
    
    $('#deletePassenger').click(function(){
        var passId = "pass" + passCount;
        
        var seatClassSelect = $('#' + passId).find('select');
        /*var seatClassId = "seatClass" + passCount;
        var seatClassSelect = document.getElementById(seatClassId);*/
        if (seatClassSelect.val() === "Economy")
        {
            economyCount--;
        }
        else if (seatClassSelect.val() === "First")
        {
            firstCount--;
        }
        else
        {
            businessCount--;
        }
        
        seatClassValues[passCount] = "";
        
        totalFare = economyCount * economyFare + firstCount * firstFare + businessCount * businessFare;
        totalFare = totalFare.toFixed(2);
        bookingFee = totalFare * .1;
        bookingFee = bookingFee.toFixed(2);
        $('#totalFareText').text("$" + totalFare);
        $('#bookingFeeText').text("$" + bookingFee);
        $('#totalFare').val(totalFare);
        $('#bookingFee').val(bookingFee);
        
        $('#' + passId).remove();
        
        passCount--;
        
        $('#passCount').val(passCount);
        
        if (passCount === 0)
        {
            $('#deletePassenger').toggleClass("pure-button-disabled");
        }
    });
    
    // for existing customer select box
    $('.seatClassSelect').change(function(){
        updateFareAndFee(this, 0);
        
        var mealSelectId = "cMeal";
        var meals = mealOptions["" + $(this).val()];
        $('#' + mealSelectId).html(meals);
    });
    
    // for passenger select boxes that are added later and thus
    // can't have the change event directly binded to them
    $('#passengers').on('change', '.seatClassSelect', function(){
        var index = parseInt((this.id).substring(9));
        updateFareAndFee(this, index);
        
        var mealSelectId = "meal" + index;
        var meals = mealOptions["" + $(this).val()];
        $('#' + mealSelectId).html(meals);
    });
});

function updateFareAndFee(select, index)
{
    if (seatClassValues[index] === "Economy")
    {
        economyCount--;
    }
    else if (seatClassValues[index] === "First")
    {
        firstCount--;
    }
    else
    {
        businessCount--;
    }

    if ($(select).val() === "Economy")
    {
        economyCount++;
        seatClassValues[index] = "Economy";
    }
    else if ($(select).val() === "First")
    {
        firstCount++;
        seatClassValues[index] = "First";
    }
    else
    {
        businessCount++;
        seatClassValues[index] = "Business";
    }

    totalFare = economyCount * economyFare + firstCount * firstFare + businessCount * businessFare;
    totalFare = totalFare.toFixed(2);
    bookingFee = totalFare * .1;
    bookingFee = bookingFee.toFixed(2);
    $('#totalFareText').text("$" + totalFare);
    $('#bookingFeeText').text("$" + bookingFee);
    $('#totalFare').val(totalFare);
    $('#bookingFee').val(bookingFee);
}

