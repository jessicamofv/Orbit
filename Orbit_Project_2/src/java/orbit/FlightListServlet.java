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
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jessica
 */
public class FlightListServlet extends HttpServlet {

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
        
        String leaving_from = request.getParameter("leaving-from");
        String going_to = request.getParameter("going-to");
        String departing_on = request.getParameter("departing-on");
        
        //List<String> jsonData = new ArrayList();
        String json1 = "";
        String json2 = "";
        String jsonFinal = "";
        String mysJDBCDriver = "com.mysql.jdbc.Driver";
        String mysURL = "jdbc:mysql://localhost:3306/cse_305_project_transactions?zeroDateTimeBehavior=convertToNull";
        String mysUserID = username;
        String mysPassword = password;
        Connection conn=null;
        
        try (PrintWriter out = response.getWriter()) {
        try {
                Class.forName(mysJDBCDriver).newInstance();
                Properties sysprops=System.getProperties();
                sysprops.put("user",mysUserID);
                sysprops.put("password",mysPassword);

                //connect to the database
                conn = DriverManager.getConnection(mysURL,sysprops);
                System.out.println("Connected successfully to database using JConnect");

                conn.setAutoCommit(false);
                
                String query = "DROP VIEW FlightSchedule";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.executeUpdate();
                
                query = "CREATE VIEW FlightSchedule(AirlineID, FlightNo, LegNo, DepTime, FromAirport, "
                        + "ArrTime, ToAirport) AS\n" +
                        "SELECT SD.AirlineID, SD.FlightNo, SD.StopNo, SD.DepTime, SD.AirportID, "
                        + "SA.ArrTime, SA.AirportID\n" +
                        "FROM StopsAt SD, StopsAt SA\n" +
                        "WHERE (SD.AirlineID = SA.AirlineID AND SD.FlightNo = SA.FlightNo "
                        + "AND SD.StopNo = SA.StopNo - 1)";
                ps = conn.prepareStatement(query);
                ps.executeUpdate();
                
                conn.commit();
                
                query = "SELECT DISTINCT F1.AirlineID, F1.FlightNo\n" +
                        "FROM FlightSchedule F1, FlightSchedule F2\n" +
                        "WHERE (F1.AirlineID = F2.AirlineID AND F1.FlightNo = F2.FlightNo "
                        + "AND F1.FromAirport = ? AND F2.ToAirport = ? AND F1.DepTime LIKE ?)";
                ps = conn.prepareStatement(query);
                ps.setString(1, leaving_from);
                ps.setString(2, going_to);
                ps.setString(3, departing_on + "%");
                ResultSet res = ps.executeQuery();
                String airlineID = "";
                int flightNo = 0;
                String json = "";
                ResultSet res2;
                
                json1 += "{\"data\":[";
                
                while (res.next())
                {
                    airlineID = res.getString("AirlineID");
                    flightNo = res.getInt("FlightNo");
                    
                    query = "SELECT * FROM FlightSchedule\n" +
                        "WHERE (AirlineID = ? AND FlightNo = ?)";
                    ps = conn.prepareStatement(query);
                    ps.setString(1, airlineID);
                    ps.setInt(2, flightNo);
                    res2 = ps.executeQuery();

                    while (res2.next())
                    {
                        if (res2.getString("fromAirport").equals(leaving_from) 
                                && res2.getString("toAirport").equals(going_to))
                            json = "";
                        
                        json += "{\"airlineID\": \"" + res2.getString("AirlineID") + "\","
                                + "\"flightNo\": \"" + res2.getInt("FlightNo") + "\","
                                + "\"legNo\": \"" + res2.getInt("LegNo") + "\","
                                + "\"depTime\": \"" + res2.getString("DepTime") + "\","
                                + "\"fromAirport\": \"" + res2.getString("FromAirport") + "\","
                                + "\"arrTime\": \"" + res2.getString("ArrTime") + "\","
                                + "\"toAirport\": \"" + res2.getString("ToAirport") + "\"},";
                        
                        if (res2.getString("fromAirport").equals(leaving_from) 
                                && res2.getString("toAirport").equals(going_to))
                            res2.last();
                    }
                    
                    json1 += json;
                    json = "";
                }
                    
                    /*if (request.getParameter("optionsRadios").equals("round-trip"))
                    {
                        String returning_on = request.getParameter("returning-on");
                        
                        query = "SELECT DISTINCT F1.AirlineID, F1.FlightNo\n" +
                                "FROM FlightSchedule F1, FlightSchedule F2\n" +
                                "WHERE (F1.AirlineID = F2.AirlineID AND F1.FlightNo = F2.FlightNo\n" +
                                "AND F1.FromAirport = ? AND F2.ToAirport = ? AND F2.ArrTime LIKE ?)";
                        ps = conn.prepareStatement(query);
                        ps.setString(1, going_to);
                        ps.setString(2, leaving_from);
                        ps.setString(3, returning_on + "%");
                        res = ps.executeQuery();

                        while (res.next())
                        {
                            airlineID = res.getString("AirlineID");
                            flightNo = res.getInt("FlightNo");
                            
                            query = "SELECT * FROM FlightSchedule\n" +
                                "WHERE (AirlineID = ? AND FlightNo = ?)";
                            ps = conn.prepareStatement(query);
                            ps.setString(1, airlineID);
                            ps.setInt(2, flightNo);
                            res2 = ps.executeQuery();

                            while (res2.next())
                            {
                                if (res2.getString("fromAirport").equals(going_to) 
                                        && res2.getString("toAirport").equals(leaving_from))
                                    json = "";

                                json += "{\"airlineID\": \"" + res2.getString("AirlineID") + "\","
                                        + "\"flightNo\": \"" + res2.getInt("FlightNo") + "\","
                                        + "\"legNo\": \"" + res2.getInt("LegNo") + "\","
                                        + "\"depTime\": \"" + res2.getString("DepTime") + "\","
                                        + "\"fromAirport\": \"" + res2.getString("FromAirport") + "\","
                                        + "\"arrTime\": \"" + res2.getString("ArrTime") + "\","
                                        + "\"toAirport\": \"" + res2.getString("ToAirport") + "\"},";

                                if (res2.getString("fromAirport").equals(going_to) 
                                        && res2.getString("toAirport").equals(leaving_from))
                                    res2.last();
                            }
                            
                            json2 += json;
                            json = "";
                        }
                        
                        if (json2.equals(""))
                            json1 = "{\"data\":[";
                    }*/

                    jsonFinal = json1 /*+ json2*/;
                    jsonFinal = jsonFinal.substring(0, jsonFinal.length()-1);
                    jsonFinal += "]}";

                    System.out.println(jsonFinal);

                    out.print(jsonFinal);
                
        } catch(Exception e){
                e.printStackTrace();
        }
        finally{
                try{conn.close();}catch(Exception ee){};
        }
        
        /*response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            for (int i = 0; i < jsonData.size(); i++)
            {
                out.print(jsonData.get(i));
            }
            out.flush();
        }*/
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
