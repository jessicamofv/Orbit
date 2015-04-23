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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jessica
 */
public class MakeReservationServlet extends HttpServlet {

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

                String passCount = request.getParameter("passCount");
                String airlineID = request.getParameter("airlineID");
                String flightNo = request.getParameter("flightNo");
                String accountNo = request.getParameter("accountNo");
                String bookingFee = request.getParameter("bookingFee");
                String totalFare = request.getParameter("totalFare");
                String cSeatNo = request.getParameter("cSeatNo");
                String cSeatClass = request.getParameter("cSeatClass");
                String cMeal = request.getParameter("cMeal");

                String query = "SELECT * FROM Reservation";
                PreparedStatement ps = conn.prepareStatement(query);
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
                ps.setDouble(2, Double.parseDouble(bookingFee));
                ps.setDouble(3, Double.parseDouble(totalFare));
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
                /*for (int i = 1; i <= Integer.parseInt(legCount); i++)
                {
                    String legNo = request.getParameter("legNo" + i);
                    String fromStopNo = request.getParameter("fromStopNo" + i);
                    String depDate = request.getParameter("depDate" + i);

                    query = "INSERT INTO Legs VALUES (?, ?, ?, ?, ?, ?)";
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, resrNo);
                    ps.setInt(2, Integer.parseInt(legNo));
                    ps.setString(3, airlineID);
                    ps.setInt(4, Integer.parseInt(flightNo));
                    ps.setInt(5, Integer.parseInt(fromStopNo));
                    ps.setString(6, depDate);
                    ps.executeUpdate();
                }*/

                conn.commit();

                // retrieve the id of the customer under whose account this reservation
                // is being made
                query = "SELECT * FROM Customer where AccountNo = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(accountNo));
                res = ps.executeQuery();
                int id = 1;

                if (res.next())
                {
                    id = res.getInt("Id");
                }

                query = "INSERT INTO ReservationPassenger VALUES (?, ?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(query);
                ps.setInt(1, resrNo);
                ps.setInt(2, id);
                ps.setInt(3, Integer.parseInt(accountNo));
                ps.setString(4, cSeatNo);
                ps.setString(5, cSeatClass);
                ps.setString(6, cMeal);
                ps.executeUpdate();

                conn.commit();

                // be careful - starts at index 1 and goes up to and including passCount!
                for (int i = 1; i <= Integer.parseInt(passCount); i++)
                {
                    String firstName = request.getParameter("firstName" + i);
                    String lastName = request.getParameter("lastName" + i);
                    String address = request.getParameter("address" + i);
                    String city = request.getParameter("city" + i);
                    String state = request.getParameter("state" + i);
                    String zip = request.getParameter("zip" + i);
                    String seatNo = request.getParameter("seatNo" + i);
                    String seatClass = request.getParameter("seatClass" + i);
                    String meal = request.getParameter("meal" + i);

                    query = "SELECT * FROM Person";
                    ps = conn.prepareStatement(query);
                    res = ps.executeQuery();
                    id = 1;

                    if (res.next())
                    {
                        res.last();
                        id = res.getInt("Id") + 1;
                    }

                    query = "INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?, ?)";
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, id);
                    ps.setString(2, firstName);
                    ps.setString(3, lastName);
                    ps.setString(4, address);
                    ps.setString(5, city);
                    ps.setString(6, state);
                    ps.setInt(7, Integer.parseInt(zip));
                    ps.executeUpdate();

                    query = "INSERT INTO Passenger VALUES (?, ?)";
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, id);
                    ps.setInt(2, Integer.parseInt(accountNo));
                    ps.executeUpdate();

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
                        + "                    <h2 class=\"content-subhead\" style=\"color:black;\">Your flight has successfully been booked!</h2>\n"
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
