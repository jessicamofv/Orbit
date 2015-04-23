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
public class ManagerAddEmployeeServlet extends HttpServlet {

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
            Connection conn=null;

            boolean error=false;

            try {
                Class.forName(mysJDBCDriver).newInstance();
                Properties sysprops=System.getProperties();
                sysprops.put("user",mysUserID);
                sysprops.put("password",mysPassword);


                //connect to the database
                conn = DriverManager.getConnection(mysURL,sysprops);
                System.out.println("Connected successfully to database using JConnect");

                conn.setAutoCommit(false);

                //Add Employee

                //Parameters
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                String firstName = request.getParameter("first-name");
                String lastName = request.getParameter("last-name");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String zip = request.getParameter("zip");
                String ssn = request.getParameter("socialsecurity");
                String isManagerString = request.getParameter("employee-type-radios");
                System.out.println(isManagerString);
                boolean isManager;
                if (isManagerString.equals("1"))
                    isManager = true;
                else
                    isManager = false;
                String startDate = request.getParameter("start-date");
                String hourlyRate = request.getParameter("hourly-rate");


                String query = "SELECT * FROM Person";
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet res = ps.executeQuery();
                int id = 1;

                if (res.next())
                {
                    res.last();
                    id = res.getInt("Id") + 1;
                }

                query = "INSERT INTO Person VALUES(?, ?, ?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                ps.setString(2, firstName);
                ps.setString(3, lastName);
                ps.setString(4, address);
                ps.setString(5, city);
                ps.setString(6, state);
                ps.setInt(7, Integer.parseInt(zip));
                ps.executeUpdate();

                query = "INSERT INTO Employee VALUES (?, ?, ?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                ps.setInt(2, Integer.parseInt(ssn));
                ps.setBoolean(3, isManager);
                ps.setString(4, startDate);
                ps.setDouble(5, Double.parseDouble(hourlyRate));
                ps.setString(6, email);
                ps.setString(7, password);
                ps.executeUpdate();

                conn.commit();

                error=true;

            } catch(Exception e){
                e.printStackTrace();
                error=false;
            }
            finally{
                try{conn.close();}catch(Exception ee){};
            }

    //        PrintWriter out = response.getWriter();
    //        
    //        out.println("1");
            if(error)
                response.sendRedirect("managerPage.jsp?error=true");  
            else{
                response.sendRedirect("managerPage.jsp?error=false");  
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
                        + "                    <h2 class=\"content-subhead\" style=\"color:black;\">This employee has successfully been added!</h2>\n"
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