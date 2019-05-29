package com.it.diesuiteapp.framework.bos;

import java.text.SimpleDateFormat;

import com.it.diesuiteapp.framework.model.AdminStockDataDO;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;

public class AdminStockDataBO {
	public AdminStockDataDO createDO(long ref_id, String inv_no,int stock_flag, String tdate,int utype, int ttype, int prod_code,int rawmat_code,
			int stock_units, long cvoid, String discount,long adminId) throws BusinessException {

		AdminStockDataDO asdo = new AdminStockDataDO();
		try {
			// set ref_id , inv_no, fulls, empties, csfulls, csempties in persistence manager
			asdo.setRef_id(ref_id);
			asdo.setInv_no(inv_no);
			asdo.setStock_flag(stock_flag);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			asdo.setTrans_date((sdf.parse(tdate)).getTime());
			
			asdo.setUnit_type(utype);
			asdo.setTrans_type(ttype);
			asdo.setProd_code(prod_code);
			asdo.setRawmat_code(rawmat_code);
			asdo.setStock_units(stock_units);
			asdo.setCvo_id(cvoid);
			asdo.setDiscount(discount);
			asdo.setCreated_by(adminId);
			asdo.setCreated_date(System.currentTimeMillis());
			asdo.setVersion(1);
			asdo.setDeleted(0);
			
		}catch (Exception e){
			throw new BusinessException("UNABLE TO SAVE AGENCY BANK DATA");
		}
		return asdo;
	}
}
