package com.it.diesuiteapp.controllers;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.diesuiteapp.processor.ProcessorBean;
import com.it.diesuiteapp.processor.RequestResponseProcessorBean;
import com.it.diesuiteapp.response.MessageObject;

/**
 * Servlet implementation class ControlServlet
 */
@WebServlet("/RegistrationControlServlet")
public class RegistrationControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionId = (String)request.getParameter("actionId");

		if(null != actionId && actionId.equals("1004")) {
			doPost(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("jsp/pages/app.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp_page = "jsp/pages/"+request.getParameter("page");
		String actionId = request.getParameter("actionId") != null ? request.getParameter("actionId") : "";
		
		if(actionId.length()>0){
			
			ProcessorBean processorBean = new RequestResponseProcessorBean();
			processorBean.process(request, response);

			if ((actionId.equals("1002"))
					&& (((MessageObject) request
							.getAttribute("msg_obj")).getMessageStatus()
							.equals("ERROR"))) {
				//jsp_page = "jsp/pages/app.jsp";
				jsp_page = "jsp/pages/app";
			}

			if(actionId.equals("1004")) { 
					request.setAttribute("NEXTJSP","/jsp/pages/app.jsp");
			}
			else
				request.setAttribute("NEXTJSP", "/" + jsp_page + ".jsp");
		} else {
			jsp_page = "jsp/pages/app";
			request.setAttribute("NEXTJSP", "/" + jsp_page + ".jsp");
		}

		RequestDispatcher rd = request.getRequestDispatcher((String)request.getAttribute("NEXTJSP"));
		rd.forward(request, response);
			}
	}


