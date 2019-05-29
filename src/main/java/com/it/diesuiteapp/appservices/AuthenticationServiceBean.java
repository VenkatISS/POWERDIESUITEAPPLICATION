package com.it.diesuiteapp.appservices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.it.diesuiteapp.commons.ApplicationConstants;
import com.it.diesuiteapp.framework.AgencyFactory;
import com.it.diesuiteapp.framework.model.AdminDO;
import com.it.diesuiteapp.framework.model.UserDetailsDO;
import com.it.diesuiteapp.response.MessageObject;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;

public class AuthenticationServiceBean {
	
	Logger logger=LoggerFactory.getLogger(AuthenticationServiceBean.class);
	
	long adminId;
	String password;
	String emaildForFPWD;
	String selectedDropdown;
	
	public void validateLogin(HttpServletRequest request,HttpServletResponse response){
		MessageObject msgObj = new MessageObject(1001, "AGENCY LOGIN",ApplicationConstants.ERROR_STATUS_STRING);
		try {
			adminId=Long.parseLong(request.getParameter("ai"));
			password=request.getParameter("pwd");
			selectedDropdown=request.getParameter("adminoruser");
			
			logger.info(ApplicationConstants.LogMessageKeys.VALIDATE_DEALER_LOGIN.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ ApplicationConstants.paramKeys.PASSWORD.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					adminId,password);

			//Set Agency Details in Session
			AgencyFactory af = new AgencyFactory();
			HttpSession session = request.getSession(true);
			AdminDO admindo = null;
			UserDetailsDO userdo=null;
			if(Integer.parseInt(selectedDropdown)==1) {
				 userdo = af.validateUserLogin(request,response);
			}
			else{
			 admindo = af.validateAdminLogin(request,response);
					}
			/*if(admindo.getIsFirstTimeLogin()==0){
				msgObj.setMessageStatus("FIRST LOGIN");
				request.setAttribute("showFirstloginDiv", "firstloginDiv");
				
				logger.info(ApplicationConstants.LogMessageKeys.VALIDATE_DEALER_LOGIN.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.adminId.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
						adminId,ApplicationConstants.actionStatusKeys.FIRSTLOGIN.toString());

			}*/
			if(admindo!=null && admindo.getStatus()==1) {
				msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
				
				logger.info(ApplicationConstants.LogMessageKeys.VALIDATE_DEALER_LOGIN.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
						adminId,ApplicationConstants.SUCCESS_STATUS_STRING);
				session.setAttribute("adminDO",admindo);			


			}
			else {
				if(userdo!=null && userdo.getStatus()==1) {
					msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
					
					logger.info(ApplicationConstants.LogMessageKeys.VALIDATE_DEALER_LOGIN.getValue()
							+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
		    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
							+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
							adminId,ApplicationConstants.SUCCESS_STATUS_STRING);
					session.setAttribute("userDO",userdo);			

				}
			}
			
			// call HomePageServiceBean for alerts data
		
						
		}catch (BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.VALIDATE_DEALER_LOGIN.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					adminId,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}
	
	
	// forgot password
	public void sendResetPasswordMail(HttpServletRequest request,HttpServletResponse response){
		MessageObject msgObj = new MessageObject(1005, "AGENCY FORGOT PASSWORD", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			emaildForFPWD=request.getParameter("femailId");
			
			logger.info(ApplicationConstants.LogMessageKeys.SENDRESETPASSWORDMAIL.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.EMAIL.getValue()
					+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),emaildForFPWD);

			//Set Agency Details in Session
			AgencyFactory af = new AgencyFactory();
			af.sendResetPasswordMail(request);
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			request.setAttribute("showDIV1", "successDiv1");
			
				
			logger.info(ApplicationConstants.LogMessageKeys.SENDRESETPASSWORDMAIL.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.EMAIL.getValue()
					+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					emaildForFPWD,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch (BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.SENDRESETPASSWORDMAIL.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.EMAIL.getValue()
					+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
					emaildForFPWD,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}
	
	//update password
	public void updatePassword(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession(true);
		
		MessageObject msgObj = new MessageObject(5011,"ERROR","UNABLE TO PROCESS REQUEST");
		String agencycode=(String) session.getAttribute("adminId");
		String activationCode=(String) session.getAttribute("activationCode");
		String newPassword=request.getParameter("fnpwd");
		long agency_code=Long.parseLong(agencycode);
			
			
		logger.info(ApplicationConstants.LogMessageKeys.UPDATEPASSWORD.getValue()
				+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
				+ ApplicationConstants.paramKeys.ACTIVATIONCODE.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
				+ ApplicationConstants.paramKeys.SEPERATOR.getValue() +ApplicationConstants.paramKeys.NEWPASSWORD.toString()
				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencycode,activationCode,newPassword);
		
		//long adminId = ((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code();
		if (!(StringUtils.isEmpty(agencycode) 
				&& StringUtils.isEmpty(activationCode) )) {
			try {
				AgencyFactory af = new AgencyFactory();
				af.updatePassword(agency_code,activationCode,newPassword);
				msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
				msgObj.setMessageStatus("PASSWORD UPDATED - PLEASE LOGIN");
						
				logger.info(ApplicationConstants.LogMessageKeys.UPDATEPASSWORD.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
						+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ ApplicationConstants.paramKeys.ACTIVATIONCODE.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
						+ ApplicationConstants.paramKeys.SEPERATOR.getValue() +ApplicationConstants.paramKeys.NEWPASSWORD.toString()
						+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
						agencycode,activationCode,newPassword,ApplicationConstants.SUCCESS_STATUS_STRING);

			} catch (Exception e) {
				e.printStackTrace();
				
				logger.info(ApplicationConstants.LogMessageKeys.UPDATEPASSWORD.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
						+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ ApplicationConstants.paramKeys.ACTIVATIONCODE.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
						+ ApplicationConstants.paramKeys.SEPERATOR.getValue() +ApplicationConstants.paramKeys.NEWPASSWORD.toString()
						+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ ApplicationConstants.actionStatusKeys.EXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
						agencycode,activationCode,newPassword,e);

			}
		} else {
			msgObj.setMessageStatus("ERROR");
			msgObj.setMessageStatus("PASSWORD UPDATION FAILED - TRY AGAIN");
				
			logger.info(ApplicationConstants.LogMessageKeys.UPDATEPASSWORD.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
					+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.ACTIVATIONCODE.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue() +ApplicationConstants.paramKeys.NEWPASSWORD.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
					agencycode,activationCode,newPassword,ApplicationConstants.FAILURE_STATUS_STRING);

		}
		request.setAttribute("msg_obj", msgObj);
	}

	//Change Password
	public void changePassword(HttpServletRequest request,HttpServletResponse response){
//  	HttpSession session = request.getSession(true);

		MessageObject msgObj = new MessageObject(1007,"INVALID OLD PASSWORD.PLEASE CHECK IT ONCE AND TRY AGAIN","ERROR");
		String agencycode=request.getParameter("adminId");

//		String agencycode=(String) session.getAttribute("adminId");
		String newPassword=request.getParameter("cnpwd");
		String oldPassword=request.getParameter("copwd");

		long agency_code=Long.parseLong(agencycode);

		logger.info(ApplicationConstants.LogMessageKeys.CHANGEPASSWORD.getValue()
				+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
				+ApplicationConstants.paramKeys.SEPERATOR.getValue() +ApplicationConstants.paramKeys.NEWPASSWORD.toString()
				+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencycode,newPassword);

//		long adminId = ((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code();
		if (!(StringUtils.isEmpty(agencycode))) {
			try {
				AgencyFactory af = new AgencyFactory();
				af.changePassword(agency_code,newPassword,oldPassword);
				msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
				msgObj.setMessageText("PASSWORD CHANGED SUCCESSFULLY");
				request.setAttribute("showDIV", "successDiv");
				
				logger.info(ApplicationConstants.LogMessageKeys.CHANGEPASSWORD.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
						+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ApplicationConstants.paramKeys.SEPERATOR.getValue() +ApplicationConstants.paramKeys.NEWPASSWORD.toString()
						+ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
						agencycode,newPassword,ApplicationConstants.SUCCESS_STATUS_STRING);
				
			} catch (Exception e) {
				e.printStackTrace();
				
				logger.info(ApplicationConstants.LogMessageKeys.CHANGEPASSWORD.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
						+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ApplicationConstants.paramKeys.SEPERATOR.getValue() +ApplicationConstants.paramKeys.NEWPASSWORD.toString()
						+ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
						+ApplicationConstants.actionStatusKeys.EXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
						agencycode,newPassword,e);
				
			}
		} else {
			msgObj.setMessageStatus("ERROR");
			msgObj.setMessageStatus("PASSWORD UPDATION FAILED - TRY AGAIN");

			logger.info(ApplicationConstants.LogMessageKeys.UPDATEPASSWORD.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
					+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue() +ApplicationConstants.paramKeys.NEWPASSWORD.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
					agencycode,newPassword,ApplicationConstants.FAILURE_STATUS_STRING);

		}
		request.setAttribute("msg_obj", msgObj);
    }
		
	public void logoutUser(HttpServletRequest request,HttpServletResponse response){
		
		HttpSession session = request.getSession(true);

		String adminId=(String) session.getAttribute("adminId");
		session.invalidate();
		MessageObject msgObj = new MessageObject(1000, "SUCCESSFULLY LOGGED OUT", 
				ApplicationConstants.SUCCESS_STATUS_STRING);
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
		
		logger.info(ApplicationConstants.LogMessageKeys.LOGOUTUSER.getValue()
				+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
				+ApplicationConstants.paramKeys.ACTIVATIONCODE.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
				adminId,ApplicationConstants.LogMessageKeys.SUCCESSFULLYLOGGEDOUT.getValue());

	}
}