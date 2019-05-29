package com.it.diesuiteapp.framework.bos;

import com.it.diesuiteapp.framework.model.CVODataDO;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;

public class CVODataBO {

	public CVODataDO createDO(String name, String contact, String caddr, int gstyn,
			String tin, String email, String pan,String balances, int category, long agencyId)
			throws BusinessException {
		CVODataDO cvodo = new CVODataDO();
		try {
			cvodo.setCvo_name(name);
			cvodo.setCvo_address(caddr);
			cvodo.setCvo_contact(contact);
			cvodo.setIs_gst_reg(gstyn);
			cvodo.setCvo_tin(tin);
			cvodo.setCvo_email(email);
			cvodo.setCvo_pan(pan);
			cvodo.setObal(balances);
			cvodo.setCbal(balances);
			cvodo.setEbal("0.00");
			cvodo.setCvo_cat(category);
			cvodo.setCreated_by(agencyId);
			cvodo.setCreated_date(System.currentTimeMillis());
			cvodo.setVersion(1);
			cvodo.setDeleted(0);
			
		} catch (Exception e) {
			throw new BusinessException("UNABLE TO SAVE ADMIN CUSTOMER / VENDOR DATA");
		}
		return cvodo;
	}
}
