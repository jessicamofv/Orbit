/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package orbit;

import static Database.Constants.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jessica
 */
public class ManagerOnTimeFlightsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
        String mysJDBCDriver = "com.mysql.jdbc.Driver";
            String mysURL = "jdbc:mysql://localhost:3306/cse_305_project_transactions?zeroDateTimeBehavior=convertToNull";
            String mysUserID = username;
            String mysPassword = password;
            Connection conn = null;
            String json = "";
            String printHtml = "";

            boolean error = false;
            
            try {
                Class.forName(mysJDBCDriver).newInstance();
                Properties sysprops = System.getProperties();
                sysprops.put("user", mysUserID);
                sysprops.put("password", mysPassword);

                //connect to the database
                conn = DriverManager.getConnection(mysURL, sysprops);
                System.out.println("Connected successfully to database using JConnect");

                conn.setAutoCommit(false);
                
                
               String query = "SELECT * FROM Flight F\n" +
                                    "WHERE NOT EXISTS (\n" +
                                    "	SELECT * FROM StopsAt S\n" +
                                    "	WHERE F.AirlineID = S.AirlineID AND F.FlightNo = S.FlightNo\n" +
                                    "	AND (S.ActualArrTime > S.ArrTime OR S.ActualDepTime > S.DepTime))";
                            PreparedStatement ps = conn.prepareStatement(query);
                            ResultSet res = ps.executeQuery();

                            json += "{\"data\":[";

                            while (res.next())
                            {
                                json += "{\"airlineID\": \"" + res.getString("AirlineID") + "\","
                                        + "\"flightNo\": \"" + res.getInt("FlightNo") + "\","
                                        + "\"noOfSeats\": \"" + res.getInt("NoOfSeats") + "\","
                                        + "\"daysOperating\": \"" + res.getString("DaysOperating") + "\","
                                        + "\"minLengthOfStay\": \"" + res.getInt("MinLengthOfStay") + "\","
                                        + "\"maxLengthOfStay\": \"" + res.getInt("MaxLengthOfStay") + "\"},";
                                
                                String days = res.getString("DaysOperating");
                            
                            String constructDay="";
                            for(int i=0;i<days.length();i++){
                                
                                if(days.charAt(i)=='1' && i==0){
                                    constructDay+="Mon ";                                    
                                }                                
                                if(days.charAt(i)=='1' && i==1){
                                    constructDay+="Tue ";                                    
                                }
                                if(days.charAt(i)=='1' && i==2){
                                    constructDay+="Wed ";                                    
                                }
                                if(days.charAt(i)=='1' && i==3){
                                    constructDay+="Thur ";                                    
                                }
                                if(days.charAt(i)=='1' && i==4){
                                    constructDay+="Fri ";                                    
                                }
                                if(days.charAt(i)=='1' && i==5){
                                    constructDay+="Sat ";                                    
                                }
                                if(days.charAt(i)=='1' && i==6){
                                    constructDay+="Sun ";                                    
                                }
                                                                
                                
                            }
                            
                            
                            printHtml += "<tr><td>"                                         
                                    + res.getString("AirlineID") + "</td><td>"
                                    + res.getString("FlightNo") + "</td><td>"
                                    + res.getString("NoOfSeats") + "</td><td>"
                                    + constructDay + "</td><td>"
                                    + res.getString("MinLengthOfStay") + "</td><td>"
                                    + res.getString("MaxLengthOfStay")
                                    + "</td></tr>";
                            
                            constructDay="";
                            }

                            json = json.substring(0, json.length()-1);
                            json += "]}";

                            System.out.println(json);


                error = true;

            } catch (Exception e) {
                e.printStackTrace();
                error = false;
            } finally {
                try {
                    conn.close();
                } catch (Exception ee) {
                };
            }
            
            
            
            
        out.println("<!doctype html>\n"
                    + "<html lang=\"en\">\n"
                    + "    <head>\n"
                    + "        <meta charset=\"utf-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "        <meta name=\"description\" content=\"Orbit Travel Reservation System Website for CSE 305\">\n"
                    + "\n"
                    + "        <title>Orbit Travel Reservation &ndash; CSE 305 Project</title>\n"
                    + "\n"
                    + "        <link rel=\"stylesheet\" href=\"http://yui.yahooapis.com/pure/0.4.2/pure.css\">\n"
                    + "        <link rel=\"stylesheet\" href=\"css/layouts/side-menu.css\">  \n"
                    + "\n"
                    + "        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js\"></script>      \n"
                    + "        <script src=\"https://code.jquery.com/jquery-1.10.2.js\"></script>\n"
                    + "        <script src=\"https://code.jquery.com/ui/1.10.4/jquery-ui.js\"></script>\n"
                    + "\n"
                    + "        <link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css\">\n"
                    + "\n"
                    + "\n"
                    + "        <script src=\"js/ui.js\"></script>\n"
                    + "        <script src=\"js/orbitfunction.js\"></script>\n"
                    + "\n"
                    + "\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "\n"
                    + "        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->\n"
                    + "\n"
                    + "        <div id=\"layout\">\n"
                    + "            <!-- Menu toggle -->\n"
                    + "            <a href=\"#menu\" id=\"menuLink\" class=\"menu-link\">\n"
                    + "                <!-- Hamburger icon -->\n"
                    + "                <span></span>\n"
                    + "            </a>\n"
                    + "\n"
                    + "            <div id=\"menu\">\n"
                    + "\n"
                    + "            </div>\n"
                    + "\n"
                    + "            <div id=\"main\">\n"
                    + "                <div class=\"header\">\n"
                    + "                    <h1>Book Your Travel</h1>\n"
                    + "                    <h2>Quick and Cheap Flights</h2>\n"
                    + "                </div>\n"
                    + "\n"
                    + "                <div class=\"content\">\n"
                    + "\n"
                    + "                    <h2 class=\"content-subhead\">On-Time Flight Listing</h2>\n"
                    + "\n"
                    + "                    <table id='salesData' class=\"pure-table\">\n"
                    + "\n"
                    + "                        <thead>\n"
                    + "                            <tr>\n"
                    + "                                <th>Airline ID</th>\n"
                    + "                                <th>Flight No</th>\n"
                    + "                                <th>Number of Seats</th>\n"
                    + "                                <th>Days Operating</th>\n"
                    + "                                <th>Min Length of Stay</th>\n"
                    + "                                <th>Max Length of Stay</th>                            \n"
                    + "                            </tr>\n"
                    + "                        </thead>\n"
                    + "\n"
                    + "\n"
                    + "\n"+printHtml
                    + "                    </table>\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "                    <h2 class=\"content-subhead\">Project Specification</h2>\n"
                    + "                    <p>\n"
                    + "                        The basic idea behind your on-line travel reservation system is that it will allow customers to use the web to browse/search the contents of your database (at least that part you want the customer to see) and to make flight reservations over the web. Your web site should allow users to make both domestic and international reservations. It should also allow users to query the database for available flights (direct or indirect) between a pair of cities for a given date and \"approximate\" time.\n"
                    + "\n"
                    + "                        Your system should also support reverse auction, in which individuals specify the price they are willing to pay for a seat and the airlines either agree to sell it at that price or not. Reverse auction sites include priceline.com and expedia.com, a Microsoft-owned travel site that has a feature enabling customers to name their price.\n"
                    + "\n"
                    + "                        Actual travel sites allow you to do a lot more than simply make flight reservations. For example, you can book a rental car or a hotel room. Due to time limitations, we will stick to flight reservations only this semester.\n"
                    + "\n"
                    + "                        Your database system must be based on the specifications and requirements that follow.     \n"
                    + "                    </p>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "    </body>\n"
                    + "</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
