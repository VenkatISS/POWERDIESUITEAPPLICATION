package com.it.diesuiteapp.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.it.diesuiteapp.framework.bos.transactions.QuotationsBO;
import com.it.diesuiteapp.framework.managers.TransactionsPersistenceManager;
import com.it.diesuiteapp.framework.model.transactions.sales.QuotationDetailsDO;
import com.it.diesuiteapp.framework.model.transactions.sales.QuotationsDO;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;

public class TransactionsDataFactory {
	
	private TransactionsPersistenceManager getTransactionsPersistenceManager(){
		return new TransactionsPersistenceManager();
	}
	
	
	//Quotations Data
		public List<QuotationsDO> getAgencyQuotations(long agencyId) throws BusinessException{
			return getTransactionsPersistenceManager().getAgencyQuotations(agencyId);
		}

		public QuotationsDO getQuotationsById(long saleInvoiceId) throws BusinessException{
			return getTransactionsPersistenceManager().getAgencyQSInvoiceById(saleInvoiceId);
		}
		
		public List<QuotationsDO> saveAgencyQuotation(Map<String, String[]> requestParams, long agencyId) throws BusinessException{
			QuotationsBO qbo = new QuotationsBO();
			List<QuotationDetailsDO> doList = new ArrayList<>();
			String[] qtnDate = requestParams.get("qtn_date");
			String[] custId = requestParams.get("cust_id");
			String[] staffId = requestParams.get("staff_id");
			String[] qtnAmount = requestParams.get("qtn_c_amt");
			//Items
			String[] epids = requestParams.get("epid");
			String[] vps = requestParams.get("vp");
			String[] qtys = requestParams.get("qty");
			String[] ups = requestParams.get("up");
			String[] upds = requestParams.get("upd");
			String[] bps = requestParams.get("bp");
			String[] igstas = requestParams.get("igsta");
			String[] sgstas = requestParams.get("sgsta");
			String[] cgstas = requestParams.get("sgsta");
			String[] amts = requestParams.get("amt");
			String[] fns = requestParams.get("fn");
			
			for(int i=0; i<epids.length;i++){
				doList.add(qbo.createQuotationDetailsDO(Long.parseLong(epids[i]), vps[i], Integer.parseInt(qtys[i]), ups[i], 
						upds[i], bps[i], igstas[i], sgstas[i], cgstas[i], amts[i], fns[i]));
			}
			
			getTransactionsPersistenceManager().saveAgencyQuotations(qbo.createDO(qtnDate[0], Long.parseLong(custId[0]), 
					Long.parseLong(staffId[0]), qtnAmount[0], fns[0], agencyId),
					doList);
			return getTransactionsPersistenceManager().getAgencyQuotations(agencyId);
		}

		public List<QuotationsDO> deleteAgencyQuotation(long itemId,long agencyId) throws BusinessException{
			getTransactionsPersistenceManager().deleteQuotations(itemId,agencyId);
			return getTransactionsPersistenceManager().getAgencyQuotations(agencyId);
		}


}
