package com.it.diesuiteapp.framework.bos;

import java.text.SimpleDateFormat;

import com.it.diesuiteapp.framework.model.ProductDataDO;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;



public class ProductDataBO {

	/*public ProductDataDO createDO(int pcode,int rawmatcode,int unittype, int units, String gst, 
			int opstock,String landingrate,String sellingrate, String sdeposite, String effectiveDate, long adminId) throws BusinessException {
	*/
	
	public ProductDataDO createDO(int pcode,int rawmatcode,int unittype, int units, String gst, 
			int opstock,String landingrate, String effectiveDate, long adminId) throws BusinessException {
	ProductDataDO doObj = new ProductDataDO();
		try {
			doObj.setProd_code(pcode);
			doObj.setRawmat_code(rawmatcode);
			doObj.setUnit_type(unittype);
			doObj.setUnits(units);
			doObj.setGstp(gst);
			doObj.setOpening_stock(opstock);
			doObj.setCurrent_stock(opstock);
			doObj.setPurchase_price(landingrate);
			/*doObj.setSelling_rate(sellingrate);
			doObj.setSecurity_deposit(sdeposite);*/
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date d = sdf.parse(effectiveDate);
			doObj.setEffective_date(d.getTime());
			doObj.setCreated_by(adminId);
			doObj.setCreated_date(System.currentTimeMillis());
			doObj.setVersion(1);
			doObj.setDeleted(0);
			
		} catch (Exception e){
			throw new BusinessException("UNABLE TO SAVE AGENCY EQUIPMENT DATA");
		}
		return doObj;
	}
}
