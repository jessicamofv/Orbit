$(document).ready(function() {
    var user = localStorage.getItem("user");
    
    if (user === "Customer")
    {
        $('#menu').load('customerMenu.html');
    }
    else if (user === "CustomerRep")
    {
        $('#menu').load('customerRepMenu.html');
    }
    else if (user === "Manager")
    {
        $('#menu').load('managerMenu.html');
    }

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

            $("#leaving-from").autocomplete({
                minLength: 0,
                source: function(request, response) {
                    var results = $.ui.autocomplete.filter(data1, request.term);

                    if (!results.length) {
                        results = [NoResultsLabel];
                    }

                    response(results);
                }

            });

            $("#going-to").autocomplete({
                minLength: 0,
                source: function(request, response) {
                    var results = $.ui.autocomplete.filter(data1, request.term);

                    if (!results.length) {
                        results = [NoResultsLabel];
                    }

                    response(results);
                }

            });

        },
        error: function(jsonData) {
            alert('Error displaying flights' + jsonData);
        }
    });

    $(function() {
        $('#flight-home').submit(function() {
            $('#flight-data').html("");
            $.post('flightlist', $(this).serialize(), function(jsonData) {
                $('#flight-data').append("<thead><tr><th>AirlineID</th><th>FlightNo</th>\n\
                                <th>LegNo</th><th>DepTime</th><th>From</th><th>ArrTime</th>\n\
                                <th>To</th><th>Book Flight</th></tr></thead>");

                var parsedJSON = jQuery.parseJSON(jsonData);
                var flightInfo;
                var cellCount = 0;
                
                for (var i = 0; i < parsedJSON.data.length; i++)
                {
                    flightInfo = parsedJSON.data[i];

                    if (flightInfo.toAirport === $('#going-to').val())
                    {
                        $('#flight-data').append('<tr><td id="cell' + cellCount + '">' + flightInfo.airlineID + '</td><td id="cell' + (cellCount + 1) + '">' +
                                flightInfo.flightNo + '</td><td>' + flightInfo.legNo + '</td><td>' +
                                flightInfo.depTime + '</td><td>' + flightInfo.fromAirport + '</td><td>' +
                                flightInfo.arrTime + '</td><td>' + flightInfo.toAirport
                                + '</td><td>' + '<a id="cell' + (cellCount + 2) + '" class="button-secondary pure-button book-flight" href="makeReservation.jsp">Book Flight</a>' + '</td></tr>');

                        cellCount += 3;
                    }
                    else {
                        $('#flight-data').append('<tr class="pure-table-odd"><td>' + flightInfo.airlineID + '</td><td>' +
                                flightInfo.flightNo + '</td><td>' + flightInfo.legNo + '</td><td>' +
                                flightInfo.depTime + '</td><td>' + flightInfo.fromAirport + '</td><td>' +
                                flightInfo.arrTime + '</td><td>' + flightInfo.toAirport + '</td><td>' +
                                "" + '</td></tr>');
                    }
                }
            }/*, 'json'*/);
            return false;
        });
    });
    
    $(function() {
        $('#loginForm').submit(function() {
            $.post('login', $(this).serialize(), function(jsonData) {
                var parsedJSON = jQuery.parseJSON(jsonData);
                var data = parsedJSON.data[0];
                
                localStorage.setItem("user", data.user);
                
                if (data.user === "Customer")
                {
                    localStorage.setItem("accountNo", data.accountNo);
                }
                
                window.location.href = "./home.jsp";
            }/*, 'json'*/);
            return false;
        });
    });
    
    $(function() {
        $('.hFForm').submit(function() {
            var rowNum = (this.id).substring(6);
            $.post('showHiddenFare', $(this).serialize(), function(jsonData) {
                var buttonId = "hFButton" + rowNum;
                var buttonText = $('#' + buttonId).html();
                var hiddenFareId = "hiddenFare" + rowNum;
                
                if (buttonText.substring(0,4) === "Show")
                {
                    var parsedJSON = jQuery.parseJSON(jsonData);
                    var data = parsedJSON.data[0];
            
                    $('#' + hiddenFareId).text("$" + data.hiddenFare);
                    
                    buttonText = buttonText.replace("Show", "Hide");
                }
                else
                {
                    $('#' + hiddenFareId).text("");
                    
                    buttonText = buttonText.replace("Hide", "Show");
                }

                $('#' + buttonId).html(buttonText);
            }/*, 'json'*/);
            return false;
        });
    });
    
    $('.seatClass').each(function(){
        var idNum = (this.id).substring(9);
        var seatClassSelectId = "seatClassSelect" + idNum;
        $(this).val($('#' + seatClassSelectId).val());
    });
    
    $('.seatClassSelect').change(function(){
        var idNum = (this.id).substring(15);
        var seatClassId = "seatClass" + idNum;
        $('#' + seatClassId).val($(this).val());
    });
    
    $('.nYOPInput').change(function(){
        var idNum = (this.id).substring(9);
        var nYOPId = "nYOP" + idNum;
        $('#' + nYOPId).val($(this).val());
    });
});

function makeRequest(request, response) {
    $.ajax({
        url: 'http://query.yahooapis.com/v1/public/yql',
        data: {
            q: buildQuery(request.term),
            format: "json"
        },
        dataType: "jsonp",
        success: function(data) {
            var airports = [];
            if (data && data.query && data.query.results && data.query.results.json && data.query.results.json.json) {
                airports = data.query.results.json.json;
            }

            response($.map(airports, function(item) {
                return {
                    label: item.code + (item.name ? ", " + item.location : "") + ", " + item.location,
                    value: item.code
                };
            }));
        },
        error: function() {
            response([]);
        }
    });
}

function buildQuery(term) {
    return "select * from json where url = 'http://airportcode.riobard.com/search?fmt=JSON&q=" + encodeURI(term) + "'";
}