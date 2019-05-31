package com.it.diesuiteapp.framework.managers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.it.diesuiteapp.framework.model.AgencySerialNosDO;
import com.it.diesuiteapp.framework.model.transactions.sales.QuotationDetailsDO;
import com.it.diesuiteapp.framework.model.transactions.sales.QuotationsDO;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;
import com.it.diesuiteapp.utils.HibernateUtil;




public class TransactionsPersistenceManager {
	
	
Logger logger = Logger.getLogger(TransactionsPersistenceManager.class.getName());
	
	int cyear = Calendar.getInstance().get(Calendar.YEAR);
	String cfy = (Integer.toString(cyear)).substring(2);
	// Quotations
		public List<QuotationsDO> getAgencyQuotations(long agencyId) throws BusinessException {
			List<QuotationsDO> doList = new ArrayList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				@SuppressWarnings("unchecked")
				Query<QuotationsDO> query = session.createQuery("from QuotationsDO where created_by = ?1 and deleted = ?2 ");
				query.setParameter(1, agencyId);
				query.setParameter(2, 0);
				List<QuotationsDO> result = query.getResultList(); 
				if(result.size()>0) {
					for (QuotationsDO dataDO : result) {
						@SuppressWarnings("unchecked")
						Query<QuotationDetailsDO> squery = session.createQuery("from QuotationDetailsDO where qtn_id = ?1");
						squery.setParameter(1, dataDO.getId());
						List<QuotationDetailsDO> itemsResult = squery.getResultList();
						if(itemsResult.size()>0){
							dataDO.getQuotationItemsList().addAll(itemsResult);
						}
						doList.add(dataDO);
					}
				}
			}catch(Exception e) {
				logger.error(e);
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
			return doList;
		}

		public void saveAgencyQuotations(QuotationsDO quotationDO, List<QuotationDetailsDO> doList) throws BusinessException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				AgencySerialNosDO snoDO = session.get(AgencySerialNosDO.class,new Long(quotationDO.getCreated_by()));
				int nno = snoDO.getQtSno();
				if(nno<10){
					quotationDO.setSr_no("QT-"+cfy+"-000"+nno);
				}else if(nno<100){
					quotationDO.setSr_no("QT-"+cfy+"-00"+nno);
				}else if(nno<1000){
					quotationDO.setSr_no("QT-"+cfy+"-0"+nno);
				}else{
					quotationDO.setSr_no("QT-"+cfy+"-"+nno);
				}
				snoDO.setQtSno(nno+1);
				session.update(snoDO);
				session.save(quotationDO);
				for (QuotationDetailsDO dataDO : doList) {
					dataDO.setQtn_id(quotationDO.getId());
					session.save(dataDO);
				}
				
				tx.commit();
			}catch(Exception e) {
				try {
					if (tx != null) tx.rollback();
				}catch (HibernateException e1) {
					logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> TransactionsPersistenceManager --> saveAgencyQuotations  is not succesful");
				}
				logger.error(e);
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
		}

		public void deleteQuotations(long itemId, long agencyId) throws BusinessException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				QuotationsDO quotationDO = session.get(QuotationsDO.class, new Long(itemId));
				quotationDO.setDeleted(1);
				session.update(quotationDO);
				tx.commit();
			}catch(Exception e) {
				try {
					if (tx != null) tx.rollback();
				}catch (HibernateException e1) {
					logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> TransactionsPersistenceManager --> deleteQuotations  is not succesful");
				}
				logger.error(e);
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
		}

		public QuotationsDO getAgencyQSInvoiceById(long invoiceId) throws BusinessException {
			QuotationsDO rdo = new QuotationsDO();
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				@SuppressWarnings("unchecked")
				Query<QuotationsDO> query = session.createQuery("from QuotationsDO where id = ?1 and deleted = ?2 ");
				query.setParameter(1, invoiceId);
				query.setParameter(2, 0);
				List<QuotationsDO> result = query.getResultList(); 
				if(result.size()>0) {
					for (QuotationsDO dataDO : result) {
						@SuppressWarnings("unchecked")
						Query<QuotationDetailsDO> squery = session.createQuery("from QuotationDetailsDO where qtn_id = ?1");
						squery.setParameter(1, dataDO.getId());
						List<QuotationDetailsDO> itemsResult = squery.getResultList();
						if(itemsResult.size()>0){
							dataDO.getQuotationItemsList().addAll(itemsResult);
						}
						rdo = dataDO;
					}
				} 
			}catch(Exception e) {
				logger.error(e);
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
			return rdo;
		}
		

}
