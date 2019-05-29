package com.it.diesuiteapp.framework.bos;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import com.it.diesuiteapp.framework.model.AccountActivationDO;
import com.it.diesuiteapp.framework.model.AdminDO;
import com.it.diesuiteapp.framework.model.AdminDetailsDO;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;
import com.it.diesuiteapp.utils.PasswordUtil;
import com.it.diesuiteapp.utils.RandomActivationCodeGeneratorUtil;



public class AgencyBO {

	//ACCOUNT_ACTIVATION

	public AccountActivationDO createAccountActvDO(String agencyCode) throws BusinessException {
		String activationCode = RandomActivationCodeGeneratorUtil.generateActivationCode();

		AccountActivationDO accountActvDO = new AccountActivationDO();
		accountActvDO.setAdminId(Long.parseLong(agencyCode));
		accountActvDO.setRequestType(1);
		accountActvDO.setActivationCode(activationCode);
		accountActvDO.setCreatedDate(System.currentTimeMillis());
		return accountActvDO;
	}
	public AdminDO createDO(String adminId, String passCode, 
			String adminEmail) throws BusinessException {
		AdminDO adminDO = new AdminDO();
		adminDO.setAdminId(Long.parseLong(adminId));
		try {
			adminDO.setPassCode(PasswordUtil.encryptPasscode(passCode));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new BusinessException("UNABLE TO ENCRYPT PASSWORD");
		}
		adminDO.setEmailId(adminEmail);
		adminDO.setStatus(0);
		adminDO.setIsFirstTimeLogin(0);
		adminDO.setCreatedBy(Long.parseLong(adminId));
		adminDO.setCreatedDate(System.currentTimeMillis());
		return adminDO;
	}

	public AdminDetailsDO createAdminDetails(String mobile, String name,int state,String gstin) throws BusinessException {
		AdminDetailsDO adminDetailsDO = new AdminDetailsDO();
		adminDetailsDO.setAdminName(name);
		adminDetailsDO.setGstin(gstin);
		adminDetailsDO.setState(state);
		adminDetailsDO.setAdminMobile(mobile);
		adminDetailsDO.setCreatedDate(System.currentTimeMillis());
		adminDetailsDO.setVersion(1);
		adminDetailsDO.setDeleted(0);
		return adminDetailsDO;
	}
	
	
}