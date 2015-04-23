/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package orbit;

import static Database.Constants.*;
import Database.FlightListing;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author RahulSarna
 */
public class FlightDataServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
         
         if(request.getParameter("optionsRadios").equals("one-way")){
             
             
                RequestDispatcher dispatcher;
                String mysJDBCDriver = "com.mysql.jdbc.Driver";
                String mysURL = "jdbc:mysql://localhost:3306/cse_305_project_transactions?zeroDateTimeBehavior=convertToNull";
                String mysUserID = username;
                String mysPassword = password;
                Connection conn = null;

                try {
                    Class.forName(mysJDBCDriver).newInstance();
                    Properties sysprops = System.getProperties();
                    sysprops.put("user", mysUserID);
                    sysprops.put("password", mysPassword);

                    //connect to the database
                    conn = DriverManager.getConnection(mysURL, sysprops);
                    System.out.println("Connected successfully to database using JConnect");

                    conn.setAutoCommit(false);

                    String query = "select * from stopsat a, stopsat b where a.AirlineID = b.AirlineID and a.StopNo < b.StopNo and a.AirportID= ?";

                    PreparedStatement ps = conn.prepareStatement(query);
                    
                    ps = conn.prepareStatement(query);
                    ps.setString(1, "LGA");
//                    ps.setString(2, "2011-01-05");
//                    ps.executeQuery();

                    ResultSet res = ps.executeQuery();

                    res.next();
                    
                    System.out.println(res.getString("AirlineID"));
                    
                    
//                    System.out.println(res.));

                } catch (Exception e) {
                    e.printStackTrace();
                }

                response.sendRedirect("#flight-data");

            }


            
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
