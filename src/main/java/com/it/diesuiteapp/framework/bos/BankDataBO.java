package com.it.diesuiteapp.framework.bos;

import com.it.diesuiteapp.framework.model.BankDataDO;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;

public class BankDataBO {

	public BankDataDO createDO(String code, String name,String accno, String branch, 
			String ifsccode, String balance, String address, String dlBal, long agencyId)
			throws BusinessException {
		BankDataDO bddo = new BankDataDO();
		try {
			bddo.setBank_code(code);
			bddo.setBank_name(name);
			bddo.setBank_acc_no(accno);
			bddo.setBank_branch(branch);
			bddo.setBank_ifsc_code(ifsccode);
			bddo.setAcc_ob(balance);
			bddo.setAcc_cb(balance);
			bddo.setBank_addr(address);
			bddo.setOd_and_loan_acceptable_bal(dlBal);
			bddo.setCreated_by(agencyId);
			bddo.setCreated_date(System.currentTimeMillis());
			bddo.setVersion(1);
			bddo.setDeleted(0);
			
		} catch (Exception e){
			throw new BusinessException("UNABLE TO SAVE AGENCY BANK DATA");
		}
		return bddo;
	}
}
