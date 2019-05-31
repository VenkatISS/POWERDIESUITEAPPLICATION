package com.it.diesuiteapp.framework.bos;

import com.it.diesuiteapp.framework.model.ProductPriceDataDO;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;

public class ProductPriceDataBO {
	
	public  ProductPriceDataDO createDO(int pid, String bp, String sgstp, String cgstp, 
			String rsp, int mon, int yr, long agencyId)
			throws BusinessException {
		ProductPriceDataDO doObj = new ProductPriceDataDO();
		try {
			doObj.setProd_code(pid);
			doObj.setBase_price(bp);
			doObj.setSgst_price(sgstp);
			doObj.setCgst_price(cgstp);
			doObj.setRsp(rsp);
		//	doObj.setOffer_price("0.00");
			doObj.setMonth(mon);
			doObj.setYear(yr);
			doObj.setCreated_by(agencyId);
			doObj.setCreated_date(System.currentTimeMillis());
			doObj.setVersion(1);
			doObj.setDeleted(0);
			
		} catch (Exception e){
			throw new BusinessException("UNABLE TO SAVE AGENCY REFILL PRICE DATA");
		}
		return doObj;
	}
}
