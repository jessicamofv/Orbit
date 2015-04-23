/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.servlet.RequestDispatcher;

/**
 *
 * 
 * jsonData.add("{\"airlineID\": \"" + res.getString("AirlineID") + "\", "
                            + "\"flightNo\": \"" + res.getInt("FlightNo") + "\", "
                            + "\"legNo\": \"" + res.getInt("LegNo") + "\", "
                            + "\"depTime\": \"" + res.getString("DepTime") + "\", "
                            + "\"fromAirport\": \"" + res.getString("FromAirport") + "\", "
                            + "\"arrTime\": \"" + res.getString("ArrTime") + "\", "
                            + "\"toAirport\": \"" + res.getString("ToAirport") + "\"}");
 * 
 * 
 * 
 */
public class FlightListing {
    
    String AirlineID;
    String FlightNo;
    
    
    
}
