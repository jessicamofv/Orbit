package orbit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static Database.Constants.*;
import beans.CustomerBean;
import beans.SessionBean;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author RahulSarna
 */
public class LoginServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            RequestDispatcher dispatcher;

            String user = request.getParameter("username");
            String pass = request.getParameter("password");

            String mysJDBCDriver = "com.mysql.jdbc.Driver";
            String mysURL = "jdbc:mysql://localhost:3306/cse_305_project_transactions?zeroDateTimeBehavior=convertToNull";
            String mysUserID = username;
            String mysPassword = password;
            Connection conn=null;
            String json = "";
            
            try {
                    Class.forName(mysJDBCDriver).newInstance();
                    Properties sysprops=System.getProperties();
                    sysprops.put("user", mysUserID);
                    sysprops.put("password", mysPassword);

                    //connect to the database
                    conn = DriverManager.getConnection(mysURL, sysprops);
                    System.out.println("Connected successfully to database using JConnect");

                    conn.setAutoCommit(false);

                    String query = "SELECT * FROM Customer WHERE Email = ?";
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1, user);
                    ResultSet res = ps.executeQuery();

                    if (res.next())
                    {
                        if (res.getString("Password").equals(pass))
                        {
                            json += "{\"data\":[{\"user\": \"Customer\","
                                    + "\"accountNo\": \"" + res.getInt("AccountNo")
                                    + "\"}]}";
                            
                            System.out.println(json);
                            
                            out.print(json);
                            //session.setAttribute("user", "Customer");

                            /*CustomerBean cBean = new CustomerBean();
                            SessionBean sBean = new SessionBean();

                            cBean.setEmail(user);
                            sBean.setProfBean(cBean);

                            session.setAttribute("sessionBean", sBean);*/

                            //dispatcher = getServletContext().getRequestDispatcher("/home.jsp"/*"/restricted/customerProfile.jsp"*/);
                            //dispatcher.forward(request, response);
                        }
                        else
                        {
                            request.setAttribute("errorMessage", "Incorrect password.");
                            dispatcher = getServletContext().getRequestDispatcher("/redirect.jsp");
                            dispatcher.forward(request, response);
                        }
                    }
                    else
                    {
                        query = "SELECT * FROM Employee WHERE Email = ?";
                        ps = conn.prepareStatement(query);
                        ps.setString(1, user);
                        ResultSet res2 = ps.executeQuery();

                        if (res2.next())
                        {
                            if (res2.getString("Password").equals(pass))
                            {
                                if (!res2.getBoolean("IsManager"))
                                {
                                    json += "{\"data\":[{\"user\": \"CustomerRep\"}]}";
                                    //session.setAttribute("user", "CustomerRep");
                                }
                                else
                                {
                                    json += "{\"data\":[{\"user\": \"Manager\"}]}";
                                    //session.setAttribute("user", "Manager");
                                }
                            
                                System.out.println(json);
                            
                                out.print(json);
                                
                                //dispatcher = getServletContext().getRequestDispatcher("/home.jsp"/*"/restricted/customerProfile.jsp"*/);
                                //dispatcher.forward(request, response);
                            }
                            else
                            {
                                request.setAttribute("errorMessage", "Incorrect password.");
                                dispatcher = getServletContext().getRequestDispatcher("/redirect.jsp");
                                dispatcher.forward(request, response);
                            }
                        }
                        else
                        {
                            request.setAttribute("errorMessage", "User does not exist.");
                            dispatcher = getServletContext().getRequestDispatcher("/redirect.jsp");
                            dispatcher.forward(request, response);
                        }
                    }
            } catch(Exception e){
                    e.printStackTrace();
            }
            finally{
                    try{conn.close();}catch(Exception ee){};
            }

            /*response.setContentType("text/html;charset=UTF-8");

            response.sendRedirect("index.jsp");*/

            //        try (PrintWriter out = response.getWriter()) {
            //            /* TODO output your page here. You may use following sample code. */
            //            out.println("<!DOCTYPE html>");
            //            out.println("<html>");
            //            out.println("<head>");
            //            out.println("<title>Servlet LoginServlet</title>");            
            //            out.println("</head>");
            //            out.println("<body>");
            //            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            //            out.println("</body>");
            //            out.println("</html>");
            //        }
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
