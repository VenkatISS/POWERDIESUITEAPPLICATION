package com.it.diesuiteapp.controllers;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.it.diesuiteapp.framework.model.AdminDO;
import com.it.diesuiteapp.framework.model.vos.AgencyVO;
import com.it.diesuiteapp.processor.MasterDataRequestResponseProcessorBean;
import com.it.diesuiteapp.processor.ProcessorBean;


/**
 * Servlet implementation class ControlServlet
 */
@WebServlet("/PopupControlServlet")
public class PopupControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopupControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//RequestDispatcher rd = request.getRequestDispatcher("jsp/pages/app.jsp");
		//rd.forward(request, response);
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String jsp_page = "jsp/pages/app.jsp";
		HttpSession session = request.getSession(true);
		//AgencyVO voObj = (AgencyVO) session.getAttribute("agencyVO");
		AdminDO doObj = (AdminDO) session.getAttribute("adminDO");

		int st = Integer.parseInt(request.getParameter("sitype"));
		if( (doObj != null) && (doObj.getAdminId() > 0 ) ) {
			jsp_page = request.getParameter("page");
			String actionId = request.getParameter("actionId") != null ? request.getParameter("actionId") : "";
			if(actionId.length()>0){
				ProcessorBean processBean = new MasterDataRequestResponseProcessorBean();
				processBean.process(request, response);
				
				switch(st) {
				case 1: jsp_page = "jsp/pages/erp/popups/dom_sale.jsp";
							break;
				case 2: jsp_page = "jsp/pages/erp/popups/com_sale.jsp";
							break;
				case 3: jsp_page = "jsp/pages/erp/popups/arb_sales_popup.jsp";
							break;
				case 4: jsp_page = "jsp/pages/powerdie/popups/quotations_popup.jsp";
							break;
				case 5: jsp_page = "jsp/pages/erp/popups/delivery_challen_popup.jsp";
							break;
				case 6: jsp_page = "jsp/pages/erp/popups/purchases_return_popup.jsp";	
							break;
				case 7: jsp_page = "jsp/pages/erp/popups/ncdbc_popup.jsp";
							break;
				case 8: jsp_page = "jsp/pages/erp/popups/rc_popup.jsp";
							break;
				case 9: jsp_page = "jsp/pages/erp/popups/sales_return_popup.jsp";
							break;
				case 10: jsp_page = "jsp/pages/erp/popups/credit_note_popup.jsp";
							break;
				case 11: jsp_page = "jsp/pages/erp/popups/debit_note_popup.jsp";
							break;
				case 12: jsp_page = "jsp/pages/erp/popups/tv_popup.jsp";
							break;
				default: jsp_page = "jsp/pages/app.jsp";
						 	break;
				}
								
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher(jsp_page);
		rd.forward(request, response);
	}

}
