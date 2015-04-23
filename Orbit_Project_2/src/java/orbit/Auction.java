/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orbit;

/**
 *
 * @author Jessica
 */
public class Auction {
    public String airlineID;
    public int flightNo;
    public String seatClass;
    
    public Auction(String initAirlineID, int initFlightNo, String initSeatClass)
    {
        airlineID = initAirlineID;
        flightNo = initFlightNo;
        seatClass = initSeatClass;
    }
}
