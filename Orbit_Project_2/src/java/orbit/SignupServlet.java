package orbit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static Database.Constants.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
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
 * @author Jessica
 */
public class SignupServlet extends HttpServlet {

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
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signup.jsp");
        
        String username = request.getParameter("username");
        
        if (username.trim().equals(""))
        {
           request.setAttribute("errorMessage", "Please enter a username.");
           dispatcher.forward(request, response);
        }
        
        String password = request.getParameter("password");
        
        if (password.trim().equals(""))
        {
           request.setAttribute("errorMessage", "Please enter a password.");
           dispatcher.forward(request, response);
        }
        
        String confirmPassword = request.getParameter("confirm-password");
        
        if (confirmPassword.trim().equals(""))
        {
           request.setAttribute("errorMessage", "Please re-enter your password.");
           dispatcher.forward(request, response);
        }
        
        if (!password.equals(confirmPassword))
        {
           request.setAttribute("errorMessage", "Passwords must match.");
           dispatcher.forward(request, response);
        }
                
        String firstname = request.getParameter("first-name");
        
        if (firstname.trim().equals(""))
        {
           request.setAttribute("errorMessage", "Please enter your first name.");
           dispatcher.forward(request, response);
        }
        
        String lastname = request.getParameter("last-name");
        
        if (lastname.trim().equals(""))
        {
           request.setAttribute("errorMessage", "Please enter your last name.");
           dispatcher.forward(request, response);
        }
        
        String address = request.getParameter("address");
        
        if (address.trim().equals(""))
        {
           request.setAttribute("errorMessage", "Please enter your address.");
           dispatcher.forward(request, response);
        }
        
        String city = request.getParameter("city");
        
        if (city.trim().equals(""))
        {
           request.setAttribute("errorMessage", "Please enter your city.");
           dispatcher.forward(request, response);
        }
        
        String state = request.getParameter("state");
        
        if (state.trim().equals(""))
        {
           request.setAttribute("errorMessage", "Please enter your state.");
           dispatcher.forward(request, response);
        }
        
        String zip = request.getParameter("zip");
        
        if (zip.trim().equals(""))
        {
           request.setAttribute("errorMessage", "Please enter your zipcode.");
           dispatcher.forward(request, response);
        }
        
        String telephone = request.getParameter("telephone");
        
        String creditCard = request.getParameter("credit-card");
        
        if (creditCard.trim().equals(""))
        {
           request.setAttribute("errorMessage", "Please enter your credit card number.");
           dispatcher.forward(request, response);
        }
        
        String pereferences = request.getParameter("preferences");
        
        System.out.println(username+" "+password+" "+firstname);
        
        String mysJDBCDriver = "com.mysql.jdbc.Driver";
        String mysURL = "jdbc:mysql://localhost:3306/cse_305_project_transactions?zeroDateTimeBehavior=convertToNull";
        String mysUserID = username;
        String mysPassword = password;

        Connection conn=null;
        try {
                Class.forName(mysJDBCDriver).newInstance();
                Properties sysprops=System.getProperties();
                sysprops.put("user",mysUserID);
                sysprops.put("password",mysPassword);

                //connect to the database
                conn = DriverManager.getConnection(mysURL,sysprops);
                System.out.println("Connected successfully to database using JConnect");

                conn.setAutoCommit(false);
                
                String query = "SELECT * FROM Person";
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet res = ps.executeQuery();
                int personId = 1;
                
                if (res.next())
                {
                    res.last();
                    personId = res.getInt("Id") + 1;
                }
                
                query = "INSERT INTO Person VALUES(?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(query);
                ps.setInt(1, personId);
                ps.setString(2, firstname);
                ps.setString(3, lastname);
                ps.setString(4, address);
                ps.setString(5, city);
                ps.setString(6, state);
                ps.setInt(7, Integer.parseInt(zip));
                ps.executeUpdate();
                
                conn.commit();
                
                query = "SELECT * FROM Customer";
                ps = conn.prepareStatement(query);
                res = ps.executeQuery();
                int customerAccountNo = 1;
                
                if (res.next())
                {
                    res.last();
                    customerAccountNo = res.getInt("AccountNo") + 1;
                }
                
                query = "INSERT INTO Customer VALUES(?,?,?,?,NOW(),NULL,?)";
                ps = conn.prepareStatement(query);
                ps.setInt(1, personId);
                ps.setInt(2, customerAccountNo);
                ps.setString(3, creditCard);
                ps.setString(4, username);
                ps.setString(5, password);
                ps.executeUpdate();
                
                conn.commit();
        } catch(Exception e){
                e.printStackTrace();
        }
        finally{
                try{conn.close();}catch(Exception ee){};
        }
        
        response.setContentType("text/html;charset=UTF-8");
        
        response.sendRedirect("/WEB-INF/jsp/index.jsp");
        
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet SignupServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet SignupServlet at " + request.getContextPath() +" "+ username +"</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        
        
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
