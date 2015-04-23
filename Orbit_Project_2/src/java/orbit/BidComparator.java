/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orbit;

import java.util.Comparator;

public class BidComparator implements Comparator<Bid>
{
    public int compare(Bid bid1, Bid bid2)
    {
        //return (bid1.bidDate).compareTo(bid2.bidDate);
        // want it in descending order
        return (bid2.bidDate).compareTo(bid1.bidDate);
    }
}
