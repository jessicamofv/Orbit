/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var aiports;

$(document).ready(function(){
    
    $('#menu').load('customerMenu.html');
    
    $('#accountNo').val(localStorage.getItem("accountNo"));
    
    bidTabHideAll();

    $("#bidRadioList").click(function() {
        if ($("#currentBid").is(":checked"))
            showCurrentBidTab();
        else if ($("#bidHistory").is(":checked"))
            showBidHistoryTab();
        else if ($("#placeBid").is(":checked"))
            showPlaceBidTab();
    });

    function bidTabHideAll() {
        $("#bid-div0").hide();
        $("#bid-div1").hide();
        $("#bid-div2").hide();        
    }

    function showCurrentBidTab() {
        $("#bid-div0").show();
        $("#bid-div1").hide();
        $("#bid-div2").hide();        
    }

    function showBidHistoryTab() {
        $("#bid-div0").hide();
        $("#bid-div1").show();
        $("#bid-div2").hide();               
    }

    function showPlaceBidTab() {
        $("#bid-div0").hide();
        $("#bid-div1").hide();
        $("#bid-div2").show();                
    }
    
    flightsTabHideAll();

    $("#flightsRadioList").click(function() {
        if ($("#bestSeller").is(":checked"))
            showBestSellerTab();
        else if ($("#flightSuggestions").is(":checked"))
            showFlightSuggestionsTab();
    });

    function flightsTabHideAll() {
        $("#flights-div0").hide();
        $("#flights-div1").hide();        
    }

    function showBestSellerTab() {
        $("#flights-div0").show();
        $("#flights-div1").hide();      
    }

    function showFlightSuggestionsTab() {
        $("#flights-div0").hide();
        $("#flights-div1").show();              
    }
    
   /*$('#reservations').click(function(){
        if($('#reservations').is(':checked')){        
            $('#reservations-option').load('loadReservationsOption.html');     
        }   
    });
    
    $('#travelItinerary').click(function(){    
        if($('#travelItinerary').is(':checked')){        
            $('#reservations-option').load('loadTravelItineraryOption.html');
        }    
    });
    
    $('#bid').click(function(){    
        if($('#bid').is(':checked')){        
            $('#reservations-option').load('loadBidOption.html');
        }    
    });

    $('#flights').click(function(){    
        if($('#flights').is(':checked')){        
            $('#reservations-option').load('loadFlightsOption.html');
        }    
    });    
    
    
     $.ajax({
        type: 'post',
        url: 'airportcode',
        dataType: 'text',
        success: function(jsonData) {


            var parsedJSON = jQuery.parseJSON(jsonData);
            var data = parsedJSON.data[0];

            var data1 = [];


            for (var i = 0; i < parsedJSON.data.length; i++)
                data1.push(parsedJSON.data[i].airlineID);

            var NoResultsLabel = "No Results";

 
                    airports = data1;


        },
        error: function(jsonData) {
            alert('Error displaying flights' + jsonData);
        }
    });*/
});