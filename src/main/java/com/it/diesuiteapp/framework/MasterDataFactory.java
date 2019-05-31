package com.it.diesuiteapp.framework;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.it.diesuiteapp.framework.bos.AdminCVOBalanceDataBO;
import com.it.diesuiteapp.framework.bos.AdminStockDataBO;
import com.it.diesuiteapp.framework.bos.BankDataBO;
import com.it.diesuiteapp.framework.bos.CVODataBO;
import com.it.diesuiteapp.framework.bos.FleetDataBO;
import com.it.diesuiteapp.framework.bos.ProductDataBO;
import com.it.diesuiteapp.framework.bos.ProductPriceDataBO;
import com.it.diesuiteapp.framework.bos.UserDataBO;
import com.it.diesuiteapp.framework.managers.MasterDataPersistenceManager;
import com.it.diesuiteapp.framework.model.AdminCVOBalanceDataDO;
import com.it.diesuiteapp.framework.model.AdminStockDataDO;
import com.it.diesuiteapp.framework.model.BankDataDO;
import com.it.diesuiteapp.framework.model.CVODataDO;
import com.it.diesuiteapp.framework.model.FleetDataDO;
import com.it.diesuiteapp.framework.model.ProductDataDO;
import com.it.diesuiteapp.framework.model.ProductPriceDataDO;
import com.it.diesuiteapp.framework.model.UserDetailsDO;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;



public class MasterDataFactory {

	private MasterDataPersistenceManager getMasterDataPersistenceManager(){
		return new MasterDataPersistenceManager();
	}
	public List<UserDetailsDO> getAdminUserData(long adminId) throws BusinessException{
		return getMasterDataPersistenceManager().getAdminUserData(adminId);
	}

	public List<UserDetailsDO> saveAdminUserData(Map<String, String[]> requestParams, long adminId) throws BusinessException{
		String[] userid = requestParams.get("u_code");
		String[] username = requestParams.get("u_name");
		String[] usermobile = requestParams.get("u_mobile");
		String[] useremail = requestParams.get("u_email");
		String[] address = requestParams.get("u_address");
		String[] passcode = requestParams.get("u_pwd");
		List<UserDetailsDO> doList = new ArrayList<>();
		UserDataBO udbo = new UserDataBO();
		for(int i=0; i<userid.length;i++){
			doList.add(udbo.createDO(Long.parseLong(userid[i]), username[i], 
					passcode[i],usermobile[i], useremail[i], address[i],adminId));
		}
		getMasterDataPersistenceManager().saveAdminUserData(doList);
		return getMasterDataPersistenceManager().getAdminUserData(adminId);
	}

	public List<UserDetailsDO> deleteAdminStatutoryData(long itemId,long adminId) throws BusinessException{
		getMasterDataPersistenceManager().deleteAdminUserData(itemId);
		return getMasterDataPersistenceManager().getAdminUserData(adminId);
	}

	
	//CVO Data
		public List<CVODataDO> getAdminCVOData(long adminId) throws BusinessException {
			return getMasterDataPersistenceManager().getAdminCVOData(adminId);
		}
		//CVO Data
		public List<CVODataDO> getAdminAllCVOData(long adminId) throws BusinessException {
			return getMasterDataPersistenceManager().getAdminAllCVOData(adminId);
		}

		public List<CVODataDO> saveAdminCVOData(Map<String, String[]> requestParams, 
				long adminId) throws BusinessException{
			
			String[] names = requestParams.get("cvo_name");
			String[] caddrs = requestParams.get("cvo_addr");
			String[] categorys = requestParams.get("cvo_cat");
			String[] contacts = requestParams.get("cvo_contact");
			String[] gstyns = requestParams.get("gstyn");
			String[] tins = requestParams.get("cvo_tin");
			String[] emails = requestParams.get("cvo_email");
			String[] pans = requestParams.get("cvo_pan");
			String[] balances = requestParams.get("cvo_ob");

			List<CVODataDO> doList = new ArrayList<>();
			CVODataBO cvobo = new CVODataBO();
			
			List<AdminCVOBalanceDataDO> acvoDOList = new ArrayList<>();
			AdminCVOBalanceDataBO acvoBalBO = new AdminCVOBalanceDataBO();
			
			for(int i=0; i<names.length; i++) {
				doList.add(cvobo.createDO(names[i], contacts[i], caddrs[i], Integer.parseInt(gstyns[i]), tins[i], 
						emails[i], pans[i],balances[i], Integer.parseInt(categorys[i]), adminId));
				
				acvoDOList.add(acvoBalBO.createDO(balances[i], Integer.parseInt(categorys[i]), adminId,0,"NA",0,0,"0","NA"));
			}
			getMasterDataPersistenceManager().saveAdminCVOData(doList, acvoDOList); 
			return getMasterDataPersistenceManager().getAdminCVOData(adminId);
		}
		
		public List<CVODataDO> deleteAgencyCVOData(long cvoDataId,long agencyId) throws BusinessException{
			getMasterDataPersistenceManager().deleteAdminCVOData(cvoDataId);
			return getMasterDataPersistenceManager().getAdminCVOData(agencyId);
		}
	
	//Fleet Data
	public List<FleetDataDO> getAdminFleetData(long agencyId) throws BusinessException{
		return getMasterDataPersistenceManager().getAdminFleetData(agencyId);
	}
	//Fleet Data
	public List<FleetDataDO> getAdminAllFleetData(long agencyId) throws BusinessException{
		return getMasterDataPersistenceManager().getAdminAllFleetData(agencyId);
	}

	public List<FleetDataDO> saveAdminFleetData(Map<String, String[]> requestParams, long adminId) throws BusinessException{
		String[] vnos = requestParams.get("vh_no");
		String[] vmakes = requestParams.get("vh_make");
		String[] vtypes = requestParams.get("vh_type");
		String[] vusages = requestParams.get("vh_usage");
		List<FleetDataDO> doList = new ArrayList<>();
		FleetDataBO fdbo = new FleetDataBO();
		for(int i=0; i<vnos.length;i++){
			doList.add(fdbo.createDO(vnos[i], vmakes[i], Integer.parseInt(vtypes[i]), Integer.parseInt(vusages[i]), adminId));
		}
		getMasterDataPersistenceManager().saveAdminFleetData(doList); 
		return getMasterDataPersistenceManager().getAdminFleetData(adminId);
	}

	public List<FleetDataDO> deleteAdminFleetData(long fleetId,long adminId) throws BusinessException{
		getMasterDataPersistenceManager().deleteAdminFleetData(fleetId);
		return getMasterDataPersistenceManager().getAdminFleetData(adminId);
	}

	//Bank Data
	public List<BankDataDO> getAdminBankData(long adminId) throws BusinessException{
		return getMasterDataPersistenceManager().getAdminBankData(adminId);
	}
	

		//Bank Data
	public List<BankDataDO> getAdminAllBankData(long adminId) throws BusinessException{
		return getMasterDataPersistenceManager().getAdminAllBankData(adminId);
	}

	public List<BankDataDO> saveAdminBankData(Map<String, String[]> requestParams, long adminId) throws BusinessException{
		String[] codes = requestParams.get("bank_code");
		String[] names = requestParams.get("bank_name");
		String[] accnos = requestParams.get("bank_accno");
		String[] branches = requestParams.get("bank_branch");
		String[] ifsccodes = requestParams.get("bank_ifsc");
		String[] balances = requestParams.get("bank_ob");
		String[] addresses = requestParams.get("bank_addr");
		String[] dlBal = requestParams.get("DLbal");
		
		List<BankDataDO> doList = new ArrayList<>();
		BankDataBO bdbo = new BankDataBO();
		for(int i=0; i<codes.length; i++) {
			doList.add(bdbo.createDO(codes[i], names[i], accnos[i], branches[i], ifsccodes[i], balances[i], addresses[i], dlBal[i], adminId));
		}
		getMasterDataPersistenceManager().saveAdminBankData(doList);
		return getMasterDataPersistenceManager().getAdminAllBankData(adminId);
	}

	public List<BankDataDO> deleteAdminBankData(long bankDataId,long agencyId) throws BusinessException{
		getMasterDataPersistenceManager().deleteAdminBankData(bankDataId);
		return getMasterDataPersistenceManager().getAdminAllBankData(agencyId);
	}
	
	
	
	//Equipment Data
		public List<ProductDataDO> getAdminProductData(long adminId) throws BusinessException{
			return getMasterDataPersistenceManager().getAdminProductData(adminId);
		}
		//Equipment Data
			public List<ProductDataDO> getAdminAllProductData(long adminId) throws BusinessException{
				return getMasterDataPersistenceManager().getAdminAllProductData(adminId);
			}
		//Equipment Data
			public List<ProductDataDO> getAdminDProductData(long adminId) throws BusinessException{
				return getMasterDataPersistenceManager().getAdminDProductData(adminId);
			}

		public List<ProductDataDO> saveAdminProductData(Map<String, String[]> requestParams, long adminId) throws BusinessException{
			List<ProductDataDO> doList = new ArrayList<>();
			List<AdminStockDataDO> asdoList = new ArrayList<>();
			
			String[] pcode = requestParams.get("pro_id");
			String[] prawcode = requestParams.get("rawmid");
			String[] putype = requestParams.get("unitstype");
			String[] punits = requestParams.get("units");
			String[] pgstp = requestParams.get("rgst");
		//	String[] psdeposite = requestParams.get("rsd");
			String[] opstock = requestParams.get("ops");
			String[] lrate = requestParams.get("lrate");
		//	String[] srate = requestParams.get("srate");
			String[] pedate = requestParams.get("rd");

			
			ProductDataBO boObj = new ProductDataBO();
			AdminStockDataBO asbo = new AdminStockDataBO();
			for(int i=0; i<pcode.length;i++){
				/*doList.add(boObj.createDO(Integer.parseInt(pcode[i]), Integer.parseInt(prawcode[i]),Integer.parseInt(putype[i]),Integer.parseInt(punits[i]), pgstp[i], 
						Integer.parseInt(opstock[i]),lrate[i],srate[i],psdeposite[i], 
						pedate[i], adminId));*/

				doList.add(boObj.createDO(Integer.parseInt(pcode[i]), Integer.parseInt(prawcode[i]),Integer.parseInt(putype[i]),Integer.parseInt(punits[i]), pgstp[i], 
						Integer.parseInt(opstock[i]),lrate[i],pedate[i], adminId));
				asdoList.add(asbo.createDO(0,"NA",0, pedate[i],Integer.parseInt(putype[i]), 0, Integer.parseInt(pcode[i]),Integer.parseInt(prawcode[i]), Integer.parseInt(punits[i]), 0, "0.00", adminId));
			}
			getMasterDataPersistenceManager().saveAdminProductData(doList, asdoList); 
			return getMasterDataPersistenceManager().getAdminProductData(adminId);
		}

	
		public List<ProductDataDO> deleteAdminProductData(long itemId,long adminId) throws BusinessException{
			getMasterDataPersistenceManager().deleteAdminProductData(itemId);
			return getMasterDataPersistenceManager().getAdminProductData(adminId);
		}

		//Product Price Data
		public List<ProductPriceDataDO> getAdminProductPriceData(long adminId) throws BusinessException{
			return getMasterDataPersistenceManager().getAdminProductPriceData(adminId);
		}

		public List<ProductPriceDataDO> saveAdminProductPriceData(Map<String, String[]> requestParams, long adminId) throws BusinessException{
			List<ProductPriceDataDO> doList = new ArrayList<>();
			String[] pids = requestParams.get("pid");
			String[] bps = requestParams.get("bp");
			String[] sgsts = requestParams.get("sgst");
			String[] cgsts = requestParams.get("cgst");
			String[] rsps = requestParams.get("rsp");
			//String[] ops = requestParams.get("op");
			String[] mons = requestParams.get("mon");
			String[] yrs = requestParams.get("yr");
			ProductPriceDataBO boObj = new ProductPriceDataBO();
			for(int i=0; i<pids.length;i++){
				doList.add(boObj.createDO(Integer.parseInt(pids[i]), bps[i], sgsts[i], cgsts[i], rsps[i], 
						Integer.parseInt(mons[i]), Integer.parseInt(yrs[i]), adminId));
			}
			getMasterDataPersistenceManager().saveAdminProductPriceData(doList); 
			return getMasterDataPersistenceManager().getAdminProductPriceData(adminId);
		}

		public List<ProductPriceDataDO> deleteAdminProductPriceData(long itemId,long adminId) throws BusinessException{
			getMasterDataPersistenceManager().deleteAdminProductPriceData(itemId);
			return getMasterDataPersistenceManager().getAdminProductPriceData(adminId);
		}
		
		
/*	//Expenditure Data
	public List<ExpenditureDataDO> getAgencyExpenditureData(long agencyId) throws BusinessException{
		return getMasterDataPersistenceManager().getAgencyExpenditureData(agencyId);
	}

	public List<ExpenditureDataDO> saveAgencyExpenditureData(Map<String, String[]> requestParams, long agencyId) throws BusinessException{
		List<ExpenditureDataDO> doList = new ArrayList<>();
		ExpenditureDataBO ebo = new ExpenditureDataBO();
		String[] item_names = requestParams.get("item_name");
		String[] shs = requestParams.get("esh");
		String[] mhs = requestParams.get("emh");
		for(int i=0; i<item_names.length; i++) {
			doList.add(ebo.createDO(item_names[i], Integer.parseInt(shs[i]), Integer.parseInt(mhs[i]), agencyId));
		}
		getMasterDataPersistenceManager().saveAgencyExpenditureData(doList); 
		return getMasterDataPersistenceManager().getAgencyExpenditureData(agencyId);
	}

	public List<ExpenditureDataDO> deleteAgencyExpenditureData(long expDataId,long agencyId) throws BusinessException{
		getMasterDataPersistenceManager().deleteAgencyExpenditureData(expDataId);
		return getMasterDataPersistenceManager().getAgencyExpenditureData(agencyId);
	}

	
	private MasterDataPersistenceManager getMasterDataPersistenceManager(){
		return new MasterDataPersistenceManager();
	}

	//Credit Limits Data
	public List<CreditLimitsDataDO> getAgencyCreditLimitsData(long agencyId) throws BusinessException{
		return getMasterDataPersistenceManager().getAgencyCreditLimitsData(agencyId);
	}

	public void saveAgencyCreditLimitsData(Map<String, String[]> requestParams, long agencyId) throws BusinessException{
		List<CreditLimitsDataDO> doList = new ArrayList<>();
		CreditLimitsDataBO dataBO = new CreditLimitsDataBO();
		String[] cust_ids = requestParams.get("cid");
		String[] cls = requestParams.get("cl");
		String[] cds = requestParams.get("cd");
		
		String[] discountCcyl1 = requestParams.get("ccyl1");
		String[] discountCcyl2 = requestParams.get("ccyl2");
		String[] discountCcyl3 = requestParams.get("ccyl3");
		String[] discountCcyl4 = requestParams.get("ccyl4");
		String[] discountCcyl5 = requestParams.get("ccyl5");

		String[] ccs = requestParams.get("cc");
		for(int i=0; i<cust_ids.length; i++) {
			doList.add(dataBO.createDO(Long.parseLong(cust_ids[i]), cls[i],Integer.parseInt(cds[i]),discountCcyl1[i],
					discountCcyl2[i], discountCcyl3[i],discountCcyl4[i],discountCcyl5[i],	 Integer.parseInt(ccs[i]), agencyId));
			}
		getMasterDataPersistenceManager().saveAgencyCreditLimitsData(doList); 
		
	}

	public void deleteAgencyCreditLimitsData(long clDataId,long agencyId) throws BusinessException{
		getMasterDataPersistenceManager().deleteAgencyCreditLimitsData(clDataId);
	}
	
	public void updateCreditlimitsData(Map<String, String> requestParams, long agencyId) throws BusinessException{

		getMasterDataPersistenceManager().updateCreditlimitsData(requestParams,agencyId);

	}
*/

}
