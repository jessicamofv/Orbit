/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var aiports;

$(document).ready(function(){
    
    $('#menu').load('managerMenu.html');
    
    resTabHideAll();

    $("#res-tab-radio-list").click(function() {
        if ($("#res-flight-no").is(":checked"))
            resTabShowByFlightNum();
        else if ($("#res-cust-name").is(":checked"))
            resTabShowByCustName();
        else if ($("#res-all").is(":checked"))
            resTabHideAll();
    });

    function resTabHideAll() {
        $("#res-div0").hide();
        $("#res-div1").hide();
    }

    function resTabShowByFlightNum() {
        $("#res-div0").show();
        $("#res-div1").hide();
    }

    function resTabShowByCustName() {
        $("#res-div0").hide();
        $("#res-div1").show();
    }
    
    revTabHideAll();

    $("#revRadioList").click(function() {
        if ($("#revByFlight").is(":checked"))
            revTabByFlightNum();
        else if ($("#revByDest").is(":checked"))
            revTabByDestCity();
        else if ($("#revByCust").is(":checked"))
            revTabByCust();
        else if ($("#custRepRev").is(":checked"))
            revTabByCustRep();
        else if ($("#custRev").is(":checked"))
            revTabByCustRev();
        else if ($("#rev-all").is(":checked"))
            revTabHideAll();
    });

    function revTabHideAll() {
        $("#rev-div0").hide();
        $("#rev-div1").hide();
        $("#rev-div2").hide();
        $("#rev-div3").hide();
        $("#rev-div4").hide();        
    }

    function revTabByFlightNum() {
        $("#rev-div0").show();
        $("#rev-div1").hide();
        $("#rev-div2").hide(); 
        $("#rev-div3").hide(); 
        $("#rev-div4").hide();        
    }

    function revTabByDestCity() {
        $("#rev-div0").hide();
        $("#rev-div1").show();
        $("#rev-div2").hide();
        $("#rev-div3").hide();
        $("#rev-div4").hide();               
    }
    function revTabByCust() {
        $("#rev-div0").hide();
        $("#rev-div1").hide();
        $("#rev-div2").show();
        $("#rev-div3").hide();
        $("#rev-div4").hide();                
    }
    function revTabByCustRep() {
        $("#rev-div0").hide();
        $("#rev-div1").hide();
        $("#rev-div2").hide();
        $("#rev-div3").show();
        $("#rev-div4").hide();      
    }
    function revTabByCustRev() {
        $("#rev-div0").hide();
        $("#rev-div1").hide();
        $("#rev-div2").hide();
        $("#rev-div3").hide();
        $("#rev-div4").show();
        
    }
    
    /*$('#employees').click(function(){
        if($('#employees').is(':checked')){        
        $('#employee-option').load('loadEmployeeOption.html');     
    }   
    });
    
    $('#flights').click(function(){    
    if($('#flights').is(':checked')){        
        $('#employee-option').load('loadFlightOption.html');
    }    
    });
    
    $('#customers').click(function(){    
    if($('#customers').is(':checked')){        
        $('#employee-option').load('loadCustomerOption.html');
    }    
    });

$('#reservations').click(function(){    
    if($('#reservations').is(':checked')){        
        $('#employee-option').load('loadReservationOption.html');
    }    
    });
    
$('#revenue').click(function(){    
    if($('#revenue').is(':checked')){        
        $('#employee-option').load('loadRevenueOption.html');
    }    
    });
        
$('#salesreport').click(function(){    
    if($('#salesreport').is(':checked')){        
        $('#employee-option').load('loadSalesReportOption.html');
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
