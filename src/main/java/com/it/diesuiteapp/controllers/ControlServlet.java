package com.it.diesuiteapp.controllers;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.it.diesuiteapp.processor.ProcessorBean;
import com.it.diesuiteapp.processor.RequestResponseProcessorBean;
import com.it.diesuiteapp.response.MessageObject;



/**
 * Servlet implementation class ControlServlet
 */
@WebServlet("/login")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControlServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String jsp_page = "/jsp/pages/app.jsp";
		String dateFromMail = request.getParameter("fdate");
		String actionId = request.getParameter("actionId");
		String agencyId = request.getParameter("ai");

		if (actionId != null) {
			if (dateFromMail != null && actionId.equals("1005")) {
				long longdateFromMail = Long.parseLong(dateFromMail);

				Date maildate = new Date(longdateFromMail);
				String hoursFromMail = new SimpleDateFormat("HH").format(maildate);

				HttpSession session = request.getSession();
				session.setAttribute("agencyId", agencyId);

				int hours = Integer.parseInt(hoursFromMail);
				Date d = new Date();
				long systemLongDate = d.getTime();
				String systemDate = new SimpleDateFormat("HH").format(d);
				int systemHours = Integer.parseInt(systemDate);
				int totalHours = systemHours - hours;
				long totalsec = systemLongDate - longdateFromMail;

				if(totalHours!=0 && totalsec < 86400000) {
					jsp_page = "/jsp/pages/mailSession.jsp";
				}else {
					jsp_page = "jsp/pages/pwdUpdate.jsp";
				}
			}else {
				jsp_page = "jsp/pages/app.jsp";
			}
		}else {
			jsp_page = "/jsp/pages/app.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(jsp_page);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String jsp_page = "jsp/pages/" + request.getParameter("page");
		String actionId = request.getParameter("actionId") != null ? request.getParameter("actionId") : "";

		if(actionId.length() > 0) {
			
			ProcessorBean processorBean = new RequestResponseProcessorBean();
			processorBean.process(request, response);

			if ((("1001").equals(actionId)) && (("ERROR").equals(((MessageObject) request.getAttribute("msg_obj")).getMessageStatus()))) {
				jsp_page = "jsp/pages/app";
			}else if ((("1005").equals(actionId)) && (("ERROR").equals(((MessageObject) request.getAttribute("msg_obj")).getMessageStatus()))) {
				jsp_page = "jsp/pages/forgotPwd";
			}
			request.setAttribute("NEXTJSP", "/" + jsp_page + ".jsp");
		}else {
			jsp_page = "jsp/pages/app";
			request.setAttribute("NEXTJSP", "/" + jsp_page + ".jsp");
		}

		RequestDispatcher rd = request.getRequestDispatcher((String) request.getAttribute("NEXTJSP"));
		rd.forward(request, response);
	}

}