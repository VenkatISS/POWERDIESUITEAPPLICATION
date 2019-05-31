package com.it.diesuiteapp.appservices;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.it.diesuiteapp.commons.ApplicationConstants;
import com.it.diesuiteapp.framework.AgencyFactory;
import com.it.diesuiteapp.framework.CacheFactory;
import com.it.diesuiteapp.framework.MasterDataFactory;
import com.it.diesuiteapp.framework.TransactionsDataFactory;
import com.it.diesuiteapp.framework.model.AdminDO;
import com.it.diesuiteapp.framework.model.ProductCategoryDO;
import com.it.diesuiteapp.framework.model.vos.AgencyVO;
import com.it.diesuiteapp.response.MessageObject;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;

public class TransactionsDataServiceBean {
	Logger logger=LoggerFactory.getLogger(TransactionsDataServiceBean.class);
	long  adminId;
	long itemId ;
	long bankId ;
	
	// Quotations
		public void fetchQuotations(HttpServletRequest request,
				HttpServletResponse response){
			MessageObject msgObj = new MessageObject(5141, "FETCH QUOTATIONS DATA", ApplicationConstants.ERROR_STATUS_STRING);
			try {
				//adminId = ((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code();
				 adminId = ((AdminDO)request.getSession().getAttribute("adminDO")).getAdminId();

				logger.info(ApplicationConstants.LogMessageKeys.FETCHQUOTATIONS.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),adminId);

				request.setAttribute("product_types_list", (new Gson().toJson(new CacheFactory().getProductTypesData())));
				request.setAttribute("product_prices_data", (new Gson().toJson(new CacheFactory().getProductTypesData())));

				MasterDataFactory mdf = new MasterDataFactory();
				request.setAttribute("cvo_data", (new Gson().toJson(mdf.getAdminAllCVOData(adminId))));
				request.setAttribute("staff_data", (new Gson().toJson(mdf.getAdminUserData(adminId))));
				request.setAttribute("users_data", (new Gson().toJson(mdf.getAdminUserData(adminId))));
				/*request.setAttribute("cl_data",(new Gson().toJson(mdf.getAgencyCreditLimitsData(
						((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code()))));*/
				
				request.setAttribute("dproduct_data", (new Gson().toJson(
						mdf.getAdminDProductData(((AdminDO)request.getSession().getAttribute("adminDO")).getAdminId()))));
				
				
				request.setAttribute("product_data", (new Gson().toJson(
						mdf.getAdminProductData(((AdminDO)request.getSession().getAttribute("adminDO")).getAdminId()))));
				request.setAttribute("product_prices_data", (new Gson().toJson(
						mdf.getAdminProductPriceData(((AdminDO)request.getSession().getAttribute("adminDO")).getAdminId()))));
				TransactionsDataFactory factory = new TransactionsDataFactory();
				request.setAttribute("qtn_data", (new Gson().toJson(
						factory.getAgencyQuotations(adminId))));
				msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
				
				logger.info(ApplicationConstants.LogMessageKeys.FETCHQUOTATIONS.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
						adminId,ApplicationConstants.SUCCESS_STATUS_STRING);

			//	HttpSession session = request.getSession(true);
	          //  AgencyFactory af = new AgencyFactory();
	           // session.setAttribute("agencyVO",(af.fetchAgencyVO(adminId)));
			}catch(BusinessException be) {
				msgObj.setMessageText(be.getExceptionMessage());
				
				logger.info(ApplicationConstants.LogMessageKeys.FETCHQUOTATIONS.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
						adminId,be);


			}
			request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
		}

		public void saveQuotation(HttpServletRequest request, HttpServletResponse response){
			
			MessageObject msgObj = new MessageObject(5142, "SAVE QUOTATIONS DATA", ApplicationConstants.ERROR_STATUS_STRING);
			try {
				adminId = Long.parseLong(request.getParameter("adminId"));
				
				logger.info(ApplicationConstants.LogMessageKeys.SAVEQUOTATION.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),adminId);

				Map<String, String[]> requestParams = request.getParameterMap();
				TransactionsDataFactory factory = new TransactionsDataFactory();
				factory.saveAgencyQuotation(requestParams, adminId);
				fetchQuotations(request, response);
				//request.setAttribute("quo_data", (new Gson().toJson(factory.saveAgencyPurchaseInvoice(requestParams, adminId))));
				msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);

				logger.info(ApplicationConstants.LogMessageKeys.SAVEQUOTATION.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
						adminId,ApplicationConstants.SUCCESS_STATUS_STRING);

			}catch(BusinessException be){
				msgObj.setMessageText(be.getExceptionMessage());
				
				logger.info(ApplicationConstants.LogMessageKeys.SAVEQUOTATION.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
						adminId,be);

			}
			request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
		}

		public void deleteQuotation(HttpServletRequest request, HttpServletResponse response){
			
			MessageObject msgObj = new MessageObject(5143, "DELETE QUOTATIONS DATA", ApplicationConstants.ERROR_STATUS_STRING);
			try {
				adminId = Long.parseLong(request.getParameter("adminId"));
				itemId = Long.parseLong(request.getParameter("dataId"));
				
				logger.info(ApplicationConstants.LogMessageKeys.DELETEQUOTATION.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ApplicationConstants.paramKeys.ITEMID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
						adminId,itemId);

				TransactionsDataFactory factory = new TransactionsDataFactory();
				factory.deleteAgencyQuotation(itemId,adminId);
				fetchQuotations(request, response);
				//request.setAttribute("quo_data", (new Gson().toJson(factory.deleteAgencyPurchaseInvoice(itemId,adminId))));
				msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);

				logger.info(ApplicationConstants.LogMessageKeys.DELETEQUOTATION.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ApplicationConstants.paramKeys.ITEMID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
						+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.STATUS.toString()
						+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),adminId,itemId,
						ApplicationConstants.SUCCESS_STATUS_STRING);

			}catch(BusinessException be){
				msgObj.setMessageText(be.getExceptionMessage());
				
				
				logger.info(ApplicationConstants.LogMessageKeys.DELETEQUOTATION.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ApplicationConstants.paramKeys.ITEMID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
						+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()
						+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),adminId,itemId,be);

			}
			request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
		}

}
