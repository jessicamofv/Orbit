/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.SessionBean;
import javax.servlet.RequestDispatcher;

public class LoggedInFilter implements Filter {

        RequestDispatcher dispatcher;
    
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest sreq, ServletResponse sresp,
			FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sreq;        
        
        SessionBean sBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        if(sBean == null) {
        	HttpServletResponse response = (HttpServletResponse) sresp;
        	response.sendRedirect("/Orbit_Project/loginPage.jsp?nouser=true&error=true");
                
//                dispatcher = getServletContext().getRequestDispatcher("/restricted/customerProfile.jsp");
//                dispatcher.forward(request, response);
                
        	return;
        }
        
        arg2.doFilter(sreq, sresp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
