package com.it.diesuiteapp.framework.managers;


import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.it.diesuiteapp.framework.model.AccountActivationDO;
import com.it.diesuiteapp.framework.model.AdminDO;
import com.it.diesuiteapp.framework.model.AdminDetailsDO;
import com.it.diesuiteapp.framework.model.UserDetailsDO;
import com.it.diesuiteapp.framework.model.vos.AgencyVO;
import com.it.diesuiteapp.mailservices.MailUtility;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;
import com.it.diesuiteapp.utils.HibernateUtil;
import com.it.diesuiteapp.utils.PasswordUtil;


public class AgencyPersistenceManager {
	
	Logger logger = Logger.getLogger(AgencyPersistenceManager.class.getName());


	
	public void createAgency(AdminDO adminDO,AdminDetailsDO adminDetailsDO,AccountActivationDO accountActvDO) throws BusinessException {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			session.save(adminDO);
			adminDetailsDO.setCreatedBy(adminDO.getAdminId());
			session.save(adminDetailsDO);
			session.save(accountActvDO);
			tx.commit();
 
			/*long agencyCode=AdminDO.getAgencyCode();
			String emailId=AdminDO.getEmailId();
			String activationCode=accountActvDO.getActivationCode();
			// Send email notification
			MailUtility mailUtility = new MailUtility();
			mailUtility.sendRegistrationActivationMail(agencyCode,emailId,activationCode);
			*/
			//tx.commit();
			
		}catch (Exception e){
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> AgencyPersistenceManager --> createAgency  is not succesful");
			}
			logger.error(e);
			e.printStackTrace();

			String s = null;
			String emsg = (e.getCause()).toString();
			if(emsg.contains("Duplicate entry")){
				if(emsg.contains(Long.toString(adminDO.getAdminId()))){
					s = "UNABLE TO REGISTER AGENCY. THE AGENCY ID ALREADY EXISTS.<br>PLEASE CHECK IT AND REGISTER AGAIN.";
				}else if(emsg.contains(adminDO.getEmailId())){
					s = "UNABLE TO REGISTER AGENCY. THE EMAIL ID ALREADY EXISTS.<br>PLEASE CHECK IT AND REGISTER AGAIN.";
				}
			}
//			String s = "UNABLE TO REGISTER AGENCY. THE AGENCY ID / EMAIL ID ALREADY EXISTS.<br>PLEASE CHECK IT AND REGISTER AGAIN.";
			throw new BusinessException(s);
		}finally {
			session.clear();
			session.close();
		}
	}
	
	
	//emailactivation
	@SuppressWarnings("unchecked")
	public AccountActivationDO activateDealerAccount(long agencyCode ,String activationCode,int requestType) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		AdminDO AdminDO;
		AccountActivationDO avtvDO=new AccountActivationDO();		
		Query<AccountActivationDO> query = session.createQuery("from AccountActivationDO where AGENCY_CODE = ?1 and ACTIVATION_CODE = ?2 and REQUEST_TYPE = ?3 ");
		query.setParameter(1, agencyCode);
		query.setParameter(2, activationCode);
		query.setParameter(3, 1);
		List<AccountActivationDO> result = query.getResultList(); 
		try {
			if(result.size()>0) {
				for (AccountActivationDO acdo : result) {
					tx = session.beginTransaction();
					AdminDO = session.get(AdminDO.class, new Long(agencyCode));
					if((activationCode.equals(acdo.getActivationCode())) && (AdminDO.getStatus() == 0)) {
						AdminDO.setStatus(1);
						session.update(AdminDO);
						tx.commit();
					}else if(AdminDO.getStatus() == 2){
						// when admin de-activates dealer's account...
						System.out.println("Your account has been deactivated. If you have any queries, feel free to contact us through mail: support@ilpg.in or Ph.No : 040-23546767");
						logger.info("Your account has been deactivated. If you have any queries, feel free to contact us through mail: support@ilpg.in or Ph.No : 040-23546767");
					}
				}
			}
		}catch(Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> AgencyPersistenceManager --> activateDealerAccount  is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
		return avtvDO;
	}
	
/*	public void sendResetPasswordMail(String emailId) throws BusinessException {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			//String activationCode = RandomActivationCodeGeneratorUtil.generateActivationCode();
			Date resetPwddate=new Date();
			long ldate=resetPwddate.getTime();
		
			Long agencyCode = null;
		
			@SuppressWarnings("unchecked")
			Query<AdminDO> query = session.createQuery("from AdminDO where emailId = ?1 and status = ?2 ");
			query.setParameter(1, emailId);
			query.setParameter(2, 1);
		
			List<AdminDO> result = query.getResultList(); 

			if(result.size()>0) {
				for (AdminDO ado : result) {
					if(emailId.equals(ado.getEmailId())) {
						agencyCode =  ado.getAgencyCode();
					}
				}
				// Send email notification
				MailUtility mailUtility = new MailUtility();
				mailUtility.sendResetPasswordMail(agencyCode,emailId,ldate);
			}
			tx.commit();			
		}catch (Exception e){
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> AgencyPersistenceManager --> sendResetPasswordMail  is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException("UNABLE TO REGISTER AGENCY");
		}finally {
			session.clear();
			session.close();
		}
	}*/

	public void sendResetPasswordMail(String emailId) throws BusinessException {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			//String activationCode = RandomActivationCodeGeneratorUtil.generateActivationCode();
			Date resetPwddate=new Date();
			long ldate=resetPwddate.getTime();
		
			Long agencyCode = null;
		
			@SuppressWarnings("unchecked")
			Query<AdminDO> query = session.createQuery("from AdminDO where emailId = ?1");
			query.setParameter(1, emailId);

			List<AdminDO> result = query.getResultList(); 

			if(result.size()>0) {
				for (AdminDO ado : result) {
					if(ado.getStatus() == 1) {
						if(emailId.equals(ado.getEmailId())) {
							agencyCode =  ado.getAdminId();
						}
					}else if(ado.getStatus() == 0) {
						throw new BusinessException("YOUR ACCOUNT HAS NOT BEEN ACTIVATED. <br>"
								+ "WE SENT AN EMAIL WITH THE ACTIVATION LINK AT THE TIME OF YOUR REGISTRATION. <br>"
								+ "PLEASE ACTIVATE IT AND TRY AGAIN.");
					}else if(ado.getStatus() == 2) {
						System.out.println("Your account has been deactivated. If you have any queries, feel free to contact us through mail: support@ilpg.in or Ph.No : 040-23546767");
					}
				}
				// Send email notification
				if(agencyCode != null){
					MailUtility mailUtility = new MailUtility();
					mailUtility.sendResetPasswordMail(agencyCode,emailId,ldate);
				}
			}else {
				throw new BusinessException("THE EMAIL ID YOU HAVE PROVIDED IS NOT REGISTERED.");
			}
			tx.commit();
		}catch (BusinessException be){
			logger.error(be.getExceptionMessage());
			logger.error(be);
			be.printStackTrace();
			throw be;
		}catch (Exception e){
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> AgencyPersistenceManager --> sendResetPasswordMail  is not succesful");
			}
			logger.error(e);
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new BusinessException("UNABLE TO COMPLETE FORGET PASSWORD PROCESS");
		}finally {
			session.clear();
			session.close();
		}
	}
	
	//update password
	@SuppressWarnings("unchecked")
	public AgencyVO updatePassword(long agencyCode ,String activationCode,String newPassword) {
		AgencyVO agencyVO = new AgencyVO();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		AdminDO AdminDO;
		Query<AdminDO> query = session.createQuery("from AdminDO where agencyCode = ?1 and status = ?2 ");
		query.setParameter(1, agencyCode);
		query.setParameter(2, 1);
		try {
			//	query.setParameter(2, PasswordUtil.encryptPasscode(pwd));
			List<AdminDO> result = query.getResultList(); 
			if(result.size()==1) {
				
				List<AgencyVO> dresult = session.createQuery("from AgencyVO where agency_code = ?1")
						.setParameter(1, agencyCode)
						.getResultList();
				if(dresult.size()==1) {
					agencyVO = dresult.get(0);
					for (AdminDO acdo : result) {
						tx = session.beginTransaction();
						AdminDO = session.get(AdminDO.class, new Long(agencyCode));
						if(agencyCode==acdo.getAdminId()) {
					         AdminDO.setPassCode(PasswordUtil.encryptPasscode(newPassword));
					         session.update(AdminDO);
					         tx.commit();
						}
					}
				}
			}
		}
		catch(Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> AgencyPersistenceManager --> updatePassword  is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
		return agencyVO;
	}
	
	//change password
    @SuppressWarnings("unchecked")
	public AgencyVO changePassword(long agencyCode ,String newPassword,String oldPassword) {
    	AgencyVO agencyVO = new AgencyVO();
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction tx = null;
    	AdminDO AdminDO;
    	Query<AdminDO> query = session.createQuery("from AdminDO where agencyCode = ?1 and status = ?2 ");
    	query.setParameter(1, agencyCode);
    	query.setParameter(2, 1);
    	try {
    		String dealerOldPwdEncrypt=PasswordUtil.encryptPasscode(oldPassword);
    		System.out.println("DEALER PASSWORD IS:"+dealerOldPwdEncrypt);
            	
    		//        query.setParameter(2, PasswordUtil.encryptPasscode(pwd));
    		List<AdminDO> result = query.getResultList();
    		if(result.size()==1) {
    			List<AgencyVO> dresult = session.createQuery("from AgencyVO where agency_code = ?1")
    					.setParameter(1, agencyCode)
    					.getResultList();
    			if(dresult.size()==1) {
    				for(AdminDO aDO:result) {
    					if(dealerOldPwdEncrypt.equals(aDO.getPassCode())) {
    						agencyVO = dresult.get(0);
    						for (AdminDO acdo : result) {
    							tx = session.beginTransaction();
    							AdminDO = session.get(AdminDO.class, new Long(agencyCode));
    							if(agencyCode==acdo.getAdminId()) {
    								AdminDO.setPassCode(PasswordUtil.encryptPasscode(newPassword));
    								session.update(AdminDO);
    								tx.commit();
    							}
    						}
    					}else
    						throw new BusinessException("INVALID OLD PASSWORD /NEW  PASSWORD /CONFIRM PASSWORD.PLEASE CHECK IT ONCE AND TRY AGAIN");
    				}
    			}
    		}
    	}catch(Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> AgencyPersistenceManager --> changePassword  is not succesful");
			}
			logger.error(e);
    		e.printStackTrace();
    		throw new BusinessException(e.getMessage());
    	}finally {
    		session.clear();
    		session.close();
    	}
    	return agencyVO;
    }
	
	
	
	public UserDetailsDO validateUserLogin(long agencyId, String pwd) throws BusinessException {
		UserDetailsDO userDO = new UserDetailsDO();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<UserDetailsDO> query = session.createQuery("from UserDetailsDO where USER_ID = ?1 and passCode = ?2 and status = ?3 ");
			query.setParameter(1, agencyId);
			query.setParameter(2,pwd);
			query.setParameter(3, 1);
			List<UserDetailsDO> result = query.getResultList(); 
			if(result.size()==1) {
				userDO=result.get(0);
				System.out.println("login success....");
				} else {
					throw new BusinessException("INVALID AGENCY ID / PASSWORD / ACCOUNT NOT ACTIVE");
				}
			
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException("INVALID AGENCY ID / PASSWORD / ACCOUNT NOT ACTIVE");
		}
		return userDO;
	}
//
	public AdminDO validateAdminLogin(long adminId, String pwd) throws BusinessException {
		AdminDO adminDO = new AdminDO();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<AdminDO> query = session.createQuery("from AdminDO where adminId = ?1 and passCode = ?2 and status = ?3 ");
			query.setParameter(1, adminId);
			query.setParameter(2, PasswordUtil.encryptPasscode(pwd));
			query.setParameter(3, 1);
			List<AdminDO> result = query.getResultList(); 
			if(result.size()==1) {
				adminDO=result.get(0);
				System.out.println("login success....");
				} else {
					throw new BusinessException("INVALID AGENCY ID / PASSWORD / ACCOUNT NOT ACTIVE");
				}
			
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException("INVALID AGENCY ID / PASSWORD / ACCOUNT NOT ACTIVE");
		}
		return adminDO;
	}
	/*
	 * public AgencyVO fetchAgencyDetails(long agencyId) { Session session =
	 * HibernateUtil.getSessionFactory().openSession(); AgencyVO agencyVO = new
	 * AgencyVO();
	 * 
	 * @SuppressWarnings("unchecked") List<AgencyVO> dresult =
	 * session.createQuery("from AgencyVO where agency_code = ?1") .setParameter(1,
	 * agencyId) .getResultList(); if(dresult.size()==1) { agencyVO =
	 * dresult.get(0); } return agencyVO; }
	 */
	
	
}