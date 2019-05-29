package com.it.diesuiteapp.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.diesuiteapp.framework.bos.AgencyBO;
import com.it.diesuiteapp.framework.managers.AgencyPersistenceManager;
import com.it.diesuiteapp.framework.model.AccountActivationDO;
import com.it.diesuiteapp.framework.model.AdminDO;
import com.it.diesuiteapp.framework.model.UserDetailsDO;
import com.it.diesuiteapp.framework.model.vos.AgencyVO;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;

public class AgencyFactory {

	public void registerAgency(HttpServletRequest request,HttpServletResponse response) throws BusinessException {
		AgencyBO agencyBO = new AgencyBO();
		getAgencyPersistenceManager().createAgency(
				agencyBO.createDO(request.getParameter("adminid"), request.getParameter("pwd"), request.getParameter("emailId")),
				agencyBO.createAdminDetails(request.getParameter("amobile"), request.getParameter("aname"), 
								Integer.parseInt(request.getParameter("storut")),request.getParameter("gstin")),
				agencyBO.createAccountActvDO(request.getParameter("adminid")));
	}

//Dealer Email Activation
	public AccountActivationDO updateAgency(long agencyId,String activationCode,int requestType){
		return getAgencyPersistenceManager().activateDealerAccount(agencyId,activationCode,requestType);
	}
	
	public UserDetailsDO validateUserLogin(HttpServletRequest request,HttpServletResponse response) throws BusinessException {
		return getAgencyPersistenceManager()
				.validateUserLogin(Long.parseLong(request.getParameter("ai")),request.getParameter("pwd"));
	}
	
	public AdminDO validateAdminLogin(HttpServletRequest request,HttpServletResponse response) throws BusinessException {
		return getAgencyPersistenceManager()
				.validateAdminLogin(Long.parseLong(request.getParameter("ai")),request.getParameter("pwd"));
	}
	
	

	/*---------FORGOT PWD----------*/
	//sendResetPasswordMail
	public void sendResetPasswordMail(HttpServletRequest request) throws BusinessException {
		getAgencyPersistenceManager().sendResetPasswordMail(request.getParameter("femailId"));
	}
	
	//update password	
	public AgencyVO updatePassword(long  agencyId,String activationCode,String newPassword) {
		return  getAgencyPersistenceManager().updatePassword(agencyId,activationCode,newPassword);
	}
	
	//change password
    public AgencyVO changePassword(long  agencyId,String newPassword,String oldPassword) {
            return  getAgencyPersistenceManager().changePassword(agencyId,newPassword,oldPassword);
    }
	
	/*
	 * public AgencyVO fetchAgencyVO(long agencyId){ return
	 * getAgencyPersistenceManager().fetchAgencyDetails(agencyId); }
	 */


	private AgencyPersistenceManager getAgencyPersistenceManager(){
		return new AgencyPersistenceManager();
	}
}