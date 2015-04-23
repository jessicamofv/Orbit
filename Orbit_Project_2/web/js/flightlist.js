/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    
    
 $('#find-flights').click(function(){
    
    $("#flight-home").submit();

    $.ajax({
	    type: 'post',
	    url: 'flightlist',
	    dataType: 'text',
	    success: function(jsonData) {
	    	
                
	    	var parsedJSON = jQuery.parseJSON(jsonData);
	    	var data = parsedJSON.data[0];	    
                
	    	var data1 = [];
	    	
                              
//	    	for(var i=0;i<parsedJSON.data.length;i++)        			    	
//	    		data1.push(parsedJSON.data[i].airlineID);
    	                   

	    	alert(jsonData);
	    	
                
	    },
            
	    error: function(jsonData) {
	        alert('Error displaying flights' + jsonData);
	    }
  });
     
    
    
    
});   

});