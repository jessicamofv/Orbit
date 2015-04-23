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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jessica
 */
public class CustomerRepApproveOrDenyBidsServlet extends HttpServlet {

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

            String approvedOrDenied = "";
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

                String accountNo = request.getParameter("accountNo");
                String airlineID = request.getParameter("airlineID");
                String flightNo = request.getParameter("flightNo");
                String seatClass = request.getParameter("seatClass");
                String bidDate = request.getParameter("bidDate");
                String nYOP = request.getParameter("nYOP");
                String acceptedString = request.getParameter("accepted");
                boolean accepted;
                if (acceptedString.equals("Yes"))
                    accepted = true;
                else
                    accepted = false;
                Double bookingFee = Double.parseDouble(nYOP) * .1;

                String query = "UPDATE Auctions\n" +
                               "SET Accepted = ?\n" +
                               "WHERE (AccountNo = ? AND AirlineID = ? AND FlightNo = ? "
                               + "AND Class = ? AND BidDate = ? AND NYOP = ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setBoolean(1, accepted);
                ps.setInt(2, Integer.parseInt(accountNo));
                ps.setString(3, airlineID);
                ps.setInt(4, Integer.parseInt(flightNo));
                ps.setString(5, seatClass);
                ps.setString(6, bidDate);
                ps.setDouble(7, Double.parseDouble(nYOP));
                ps.executeUpdate();

                conn.commit();
                
                if (acceptedString.equals("Yes"))
                {
                    approvedOrDenied = "approved and the reservation recorded";
                    
                    query = "SELECT * FROM Reservation";
                    ps = conn.prepareStatement(query);
                    ResultSet res = ps.executeQuery();
                    int resrNo = 1;

                    if (res.next())
                    {
                        res.last();
                        resrNo = res.getInt("ResrNo") + 1;
                    }

                    query = "SELECT * FROM Employee\n"
                            + "WHERE IsManager = false";
                    ps = conn.prepareStatement(query);
                    res = ps.executeQuery();
                    ArrayList<Integer> repSSNs = new ArrayList<>();
                    int repSSN = 0;

                    while (res.next())
                    {
                        repSSNs.add(res.getInt("SSN"));
                    }

                    // randomly assign one of the customer reps
                    repSSN = repSSNs.get((int)(Math.random() * (repSSNs.size() - 1)));
                    
                    query = "INSERT INTO Reservation VALUES (?, NOW(), ?, ?, ?, ?)";
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, resrNo);
                    ps.setDouble(2, bookingFee);
                    ps.setDouble(3, Double.parseDouble(nYOP));
                    ps.setInt(4, repSSN);
                    ps.setInt(5, Integer.parseInt(accountNo));
                    ps.executeUpdate();
                    conn.commit();

                    query = "SELECT DISTINCT LegNo, FromStopNo, DepDate\n" +
                            "FROM Legs\n" +
                            "WHERE AirlineID = ? AND FlightNo = ?";
                    ps = conn.prepareStatement(query);
                    ps.setString(1, airlineID);
                    ps.setInt(2, Integer.parseInt(flightNo));
                    res = ps.executeQuery();
                    int legNo = 0;
                    int fromStopNo = 0;
                    String depDate = null;

                    while (res.next())
                    {
                        legNo = res.getInt("LegNo");
                        fromStopNo = res.getInt("FromStopNo");
                        depDate = res.getString("DepDate");

                        query = "INSERT INTO Legs VALUES (?, ?, ?, ?, ?, ?)";
                        ps = conn.prepareStatement(query);
                        ps.setInt(1, resrNo);
                        ps.setInt(2, legNo);
                        ps.setString(3, airlineID);
                        ps.setInt(4, Integer.parseInt(flightNo));
                        ps.setInt(5, fromStopNo);
                        ps.setString(6, depDate);
                        ps.executeUpdate();
                        conn.commit();
                    }
                    
                    conn.commit();

                    // retrieve the id of the customer under whose account this reservation
                    // is being made
                    query = "SELECT * FROM Customer WHERE AccountNo = ?";
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, Integer.parseInt(accountNo));
                    res = ps.executeQuery();
                    int id = 1;

                    if (res.next())
                    {
                        id = res.getInt("Id");
                    }
                    
                    query = "SELECT NoOfSeats\n"
                            + "FROM Flight\n"
                            + "WHERE (AirlineID = ? AND FlightNo = ?)";
                    ps = conn.prepareStatement(query);
                    ps.setString(1, airlineID);
                    ps.setInt(2, Integer.parseInt(flightNo));
                    res = ps.executeQuery();
                    res.next();
                    int numSeats = res.getInt("NoOfSeats");
                    int numRows = numSeats/10;
                    int numSeatsInLastRow = 0;
                    int seatNum = 1;
                    int seatLetter = 'A';
                    ArrayList<String> seatNos = new ArrayList<>();
                    String seatNoToAdd;
                    
                    // we'll say there are 10 seats per row
                    if (numSeats % 10 != 0)
                    {
                        numRows++;
                        numSeatsInLastRow = numSeats % 10;
                    }
                    
                    // add all possible seat number/letter combinations to
                    // list of seatNos
                    for (int i = 1; i <= numRows; i++)
                    {
                        for (int j = 0; j < 10; j++)
                        {
                            seatNoToAdd = "" + i + (char)(j + 'A');
                            seatNos.add(seatNoToAdd);
                            
                            if (i == numRows && j == numSeatsInLastRow - 1)
                            {
                                break;
                            }
                        }
                    }
                    
                    // pick random seatNo from list of all possible seatNos
                    Random rand = new Random();
                    int randIndex = rand.nextInt(seatNos.size());
                    String seatNo = seatNos.get(randIndex);
                    
                    // set up lists of possible meals for each class
                    ArrayList<String> economyMealOptions = new ArrayList<>();
                    ArrayList<String> firstMealOptions = new ArrayList<>();
                    ArrayList<String> businessMealOptions = new ArrayList<>();
                    ArrayList<String> mealOptions = new ArrayList<>();
                    
                    economyMealOptions.add("Chips");
                    economyMealOptions.add("Chips and Salsa");
                    economyMealOptions.add("Cheese and Crackers");
                    economyMealOptions.add("Pretzels");
                    economyMealOptions.add("Pretzels and Dip");
                    firstMealOptions.add("Fish and Chips");
                    firstMealOptions.add("Sushi");
                    firstMealOptions.add("Steak");
                    firstMealOptions.add("Chicken");
                    firstMealOptions.add("Salad");
                    businessMealOptions.add("Steak");
                    businessMealOptions.add("Chicken");
                    businessMealOptions.add("Salad");

                    // randomly assign a meal from the given class
                    if (seatClass.equals("Economy"))
                    {
                        mealOptions = economyMealOptions;
                    }
                    else if (seatClass.equals("First"))
                    {
                        mealOptions = firstMealOptions;
                    }
                    else
                    {
                        mealOptions = businessMealOptions;
                    }

                    randIndex = rand.nextInt(mealOptions.size());
                    String meal = mealOptions.get(randIndex);

                    query = "INSERT INTO ReservationPassenger VALUES (?, ?, ?, ?, ?, ?)";
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, resrNo);
                    ps.setInt(2, id);
                    ps.setInt(3, Integer.parseInt(accountNo));
                    ps.setString(4, seatNo);
                    ps.setString(5, seatClass);
                    ps.setString(6, meal);
                    ps.executeUpdate();

                    conn.commit();
                }
                else
                {
                    approvedOrDenied = "denied";
                }
                
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
                        + "                    <h2 class=\"content-subhead\" style=\"color:black;\">This bid has successfully been " + approvedOrDenied + "!</h2>\n"
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
