package com.jdc.demo;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/", "/counter"})
public class ScopeDemoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Request Scope
		Counter reqCounter = (Counter) req.getAttribute("counter");
		if(null == reqCounter) {
			reqCounter = new Counter();
			req.setAttribute("counter", reqCounter);
		}
		
		reqCounter.countUp();
		
		// Session Scope
		HttpSession session = req.getSession(true);
		Counter sesCounter = (Counter) session.getAttribute("counter");
		
		if(null == sesCounter) {
			sesCounter = new Counter();
			session.setAttribute("counter", sesCounter);
		}
		
		sesCounter.countUp();
		
		// Application Scope
		ServletContext context = getServletContext();
		Counter appCounter = (Counter) context.getAttribute("counter");
		
		if(null == appCounter) {
			appCounter = new Counter();
			context.setAttribute("counter", appCounter);
		}
		
		appCounter.countUp();
		
		var dispatcher = getServletContext().getRequestDispatcher("/counter.jsp");
		dispatcher.forward(req, resp);
	}

}
