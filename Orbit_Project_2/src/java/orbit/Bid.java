/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orbit;

import java.sql.Date;

public class Bid {
    public int accountNo;
    public String airlineID;
    public int flightNo;
    public String seatClass;
    public Date bidDate;
    public String bidDateString;
    public double nYOP;
    public String accepted;
    
    public Bid(int initAccountNo, String initAirlineID, int initFlightNo, String initSeatClass, Date initBidDate, String initBidDateString, double initNYOP, String initAccepted)
    {
        accountNo = initAccountNo;
        airlineID = initAirlineID;
        flightNo = initFlightNo;
        seatClass = initSeatClass;
        bidDate = initBidDate;
        bidDateString = initBidDateString;
        nYOP = initNYOP;
        accepted = initAccepted;
    }
}
