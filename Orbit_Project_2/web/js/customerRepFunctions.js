/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var aiports;

$(document).ready(function(){
    
    $('#menu').load('customerRepMenu.html');
    
    $('#customers').click(function(){    
        if($('#customers').is(':checked')){        
            $('#customers-option').load('loadCustomersOption.html');
        }    
    });
    
    $('#mailinglists').click(function(){    
        if($('#mailinglists').is(':checked')){        
            $('#customers-option').load('loadCustomerMailingListsOption.html');
        }    
    });

    $('#flightsuggestions').click(function(){    
        if($('#flightsuggestions').is(':checked')){        
            $('#customers-option').load('loadFlightSuggestionsOption.html');
        }    
    });
    
    $('#pendingBids').click(function(){    
        if($('#pendingBids').is(':checked')){        
            $('#customers-option').load('loadPendingBidsOption.html');
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
    });
     

});
