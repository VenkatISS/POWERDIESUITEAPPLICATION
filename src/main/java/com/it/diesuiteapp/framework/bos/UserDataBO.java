package com.it.diesuiteapp.framework.bos;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.it.diesuiteapp.framework.model.UserDetailsDO;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;

public class UserDataBO {
	
	public UserDetailsDO createDO(long userid, String name,
			String pwd, String mobile, String email,String address, long adminId)
			throws BusinessException {
		UserDetailsDO uData = new UserDetailsDO();
		try {
			uData.setUserId(userid);
			uData.setUserName(name);
			uData.setCreatedBy(adminId);
			uData.setUsetrMobile(mobile);
			uData.setPassCode(pwd);
			uData.setUserEmail(email);
			uData.setUserAddress(address);
			uData.setCreatedDate(System.currentTimeMillis());
			uData.setVersion(1);
			uData.setDeleted(0);
		} catch (Exception e){
			throw new BusinessException("UNABLE TO SAVE AGENCY STATUTORY DATA");
		}
		return uData;
	}

}
