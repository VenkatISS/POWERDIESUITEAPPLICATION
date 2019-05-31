package com.it.diesuiteapp.appservices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.it.diesuiteapp.commons.ApplicationConstants;
import com.it.diesuiteapp.framework.AgencyFactory;
import com.it.diesuiteapp.response.MessageObject;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;



public class RegistrationServiceBean {
	
	Logger logger=LoggerFactory.getLogger(RegistrationServiceBean.class);
	long adminId;
	
	public void registerAgency(HttpServletRequest request,HttpServletResponse response){

		MessageObject msgObj = new MessageObject(1002, "ADMIN REGISTRATION", 
				ApplicationConstants.ERROR_STATUS_STRING);
		try {
			
			adminId=Long.parseLong(request.getParameter("adminid"));
			String dName=request.getParameter("aname");
			String dMobile=request.getParameter("amobile");
			String emailId=request.getParameter("emailId");
			String gstin=request.getParameter("gstin");
			String stOrrut=request.getParameter("storut");
			String pwd=request.getParameter("pwd");
			String cPwd=request.getParameter("cpwd");
			
			logger.info(ApplicationConstants.LogMessageKeys.REGISTERAGENCY.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
					+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.DNAME.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue() +ApplicationConstants.paramKeys.DMOBILE.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.EMAILID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.paramKeys.GSTIN.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.STATEORUT.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.paramKeys.PASSWORD.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.CPASSWORD.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.paramKeys.FIRMTYPE.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),adminId,dName,dMobile,emailId,gstin,stOrrut,pwd,
					cPwd);
			
			AgencyFactory af = new AgencyFactory();
			af.registerAgency(request,response);
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			request.setAttribute("showDIV", "successDiv");
			logger.info(
					ApplicationConstants.LogMessageKeys.REGISTERAGENCY.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
					+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					adminId,ApplicationConstants.SUCCESS_STATUS_STRING);
		
		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			logger.info(
					ApplicationConstants.LogMessageKeys.REGISTERAGENCY.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
					+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					adminId,be);
		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}
	
	// Dealer email activation
	public void activateDealerAccount(HttpServletRequest request, HttpServletResponse response) {
			
		MessageObject msgObj = new MessageObject(5011,"ERROR","UNABLE TO PROCESS REQUEST");
		String agencycode=request.getParameter("ai");
		String activationCode=request.getParameter("ac");
		System.out.println("agencycode is:"+agencycode);
		adminId=Long.parseLong(agencycode);
			
		logger.info(
				ApplicationConstants.LogMessageKeys.ACTIVATEDEALERACCOUNT.getValue()
				+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
				+ApplicationConstants.paramKeys.ACTIVATIONCODE.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
				agencycode,activationCode);
	
		//long ADMINID = ((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code();
		if (!(StringUtils.isEmpty(agencycode) && StringUtils.isEmpty(activationCode) )) {
			try {
				AgencyFactory af = new AgencyFactory();
				af.updateAgency(adminId,activationCode,1);
				msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
				msgObj.setMessageStatus("ACCOUNT ACTIVATED - PLEASE LOGIN");
				logger.info(ApplicationConstants.LogMessageKeys.ACTIVATEDEALERACCOUNT.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
						+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
						adminId,ApplicationConstants.SUCCESS_STATUS_STRING);
			} catch (Exception e) {
				e.printStackTrace();
			
				logger.info(
						ApplicationConstants.LogMessageKeys.ACTIVATEDEALERACCOUNT.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
						+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ApplicationConstants.actionStatusKeys.EXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
						adminId,e);
			}
		}else {
			msgObj.setMessageStatus("ERROR");
			msgObj.setMessageStatus("ACCOUNT ACTIVATION FAILED - TRY AGAIN");
			
			logger.info(ApplicationConstants.LogMessageKeys.ACTIVATEDEALERACCOUNT.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
					+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					adminId,ApplicationConstants.FAILURE_STATUS_STRING);
		}
		request.setAttribute("msg_obj", msgObj);
	}
}