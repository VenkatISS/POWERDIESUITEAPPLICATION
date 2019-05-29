package com.it.diesuiteapp.framework.managers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.it.diesuiteapp.framework.model.AdminCVOBalanceDataDO;
import com.it.diesuiteapp.framework.model.AdminStockDataDO;
import com.it.diesuiteapp.framework.model.BankDataDO;
import com.it.diesuiteapp.framework.model.CVODataDO;
import com.it.diesuiteapp.framework.model.FleetDataDO;
import com.it.diesuiteapp.framework.model.ProductPriceDataDO;
import com.it.diesuiteapp.framework.model.ProductDataDO;
import com.it.diesuiteapp.framework.model.UserDetailsDO;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;
import com.it.diesuiteapp.utils.HibernateUtil;

public class MasterDataPersistenceManager {

	Logger logger = Logger.getLogger(MasterDataPersistenceManager.class.getName());

	public List<UserDetailsDO> getAdminUserData(long adminId) throws BusinessException {
		List<UserDetailsDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<UserDetailsDO> query = session.createQuery("from UserDetailsDO where createdBy = ?1 and deleted = ?2");
			query.setParameter(1, adminId);
			query.setParameter(2, 0);
			List<UserDetailsDO> result = query.getResultList(); 
			System.out.println("result is:"+result);
			if(result.size()>0) {
				for (UserDetailsDO userDataDO : result) {
					doList.add(userDataDO);
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

	public void saveAdminUserData(List<UserDetailsDO> doList) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (UserDetailsDO userDataDO : doList) {
				session.save(userDataDO);
			}
			tx.commit();
		}catch(Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> saveAgencyStatutoryData  is not succesful");
			}
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}

	public void deleteAdminUserData(long itemId) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			UserDetailsDO sddo = session.get(UserDetailsDO.class, new Long(itemId));
			sddo.setDeleted(1);
			session.update(sddo);
			tx.commit();
		}catch(Exception e) {
			try {
				if (tx != null)
					tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> deleteAgencyStatutoryData  is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}
	
	//CVO Data
		public List<CVODataDO> getAdminCVOData(long adminId) throws BusinessException {
			List<CVODataDO> doList = new ArrayList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				@SuppressWarnings("unchecked")
				Query<CVODataDO> query = session.createQuery("from CVODataDO where created_by = ?1 and deleted = ?2 ");
				query.setParameter(1, adminId);
				query.setParameter(2, 0);
				List<CVODataDO> result = query.getResultList(); 
				if(result.size()>0) {
					for (CVODataDO cvodo : result) {
						doList.add(cvodo);
					}
				} 
			}catch(Exception e) {
				e.printStackTrace();
				logger.error(e);
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
			return doList;
		}
		
		//CVO Data
			public List<CVODataDO> getAdminAllCVOData(long adminId) throws BusinessException {
				List<CVODataDO> doList = new ArrayList<>();
				Session session = HibernateUtil.getSessionFactory().openSession();
				try {
					@SuppressWarnings("unchecked")
					Query<CVODataDO> query = session.createQuery("from CVODataDO where created_by = ?1");
					query.setParameter(1, adminId);
					List<CVODataDO> result = query.getResultList(); 
					if(result.size()>0) {
						for (CVODataDO cvodo : result) {
							doList.add(cvodo);
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

			@SuppressWarnings("unchecked")
			public void saveAdminCVOData(List<CVODataDO> doList,
					List<AdminCVOBalanceDataDO> acvoDOList) throws BusinessException {
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = null;
				try {
					tx = session.beginTransaction();
		            List<Long> list = new ArrayList<>();
		            Map<Long,Integer> map =new HashMap<>();
		            
					for (CVODataDO cvodo : doList) {
						if(cvodo.getIs_gst_reg()==1){
							String gstin = cvodo.getCvo_tin();
							Query<CVODataDO> query = session.createQuery("from CVODataDO where created_by = ?1 and deleted = ?2 and cvo_tin="+"'"+gstin+"'");
							query.setParameter(1, cvodo.getCreated_by());
							query.setParameter(2, 1);
							List<CVODataDO> result = query.getResultList(); 
							if(result.size()>0) {
								for (CVODataDO cvodo2 : result) {
									if((cvodo.getCvo_tin()).equalsIgnoreCase((cvodo2.getCvo_tin()))){
										session.clear();
										cvodo.setId(cvodo2.getId());
										cvodo.setDeleted(0);
										cvodo.setCreated_date(System.currentTimeMillis());
										session.update(cvodo);

										map.put(cvodo.getId(), 1);
			                            list.add(cvodo.getId());
										
									}else{
										list.add((Long) session.save(cvodo));
			                            map.put(cvodo.getId(), 0);
									}
								}
							}else{
								list.add((Long) session.save(cvodo));
			                    map.put(cvodo.getId(), 0);
							}
						}else{
							list.add((Long) session.save(cvodo));
		                    map.put(cvodo.getId(), 0);
						}
					}
					
					int count=0;
					for (AdminCVOBalanceDataDO cvobaldo : acvoDOList) {
						cvobaldo.setCvo_refid(list.get(count));
						cvobaldo.setRef_id(0);
						cvobaldo.setCvoflag(0);
					//	cvobaldo.setInv_date(Long.parseLong(effdate));
						if(map.get(cvobaldo.getCvo_refid())==0){
							session.save(cvobaldo);	
						}
						++count;
			       	}
					
					tx.commit();
									
				}catch(Exception e) {
					try {
						if (tx != null) tx.rollback();
					}catch (HibernateException e1) {
						logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> saveAgencyCVOData is not succesful");
					}
					e.printStackTrace();
					logger.error(e);
					throw new BusinessException(e.getMessage());
				}finally {
					session.clear();
					session.close();
				}
			}

		public void deleteAdminCVOData(long itemId) throws BusinessException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				CVODataDO cvodo = session.get(CVODataDO.class, new Long(itemId));
				cvodo.setDeleted(1);
				session.update(cvodo);
				tx.commit();
			}catch(Exception e) {
				try {
					if (tx != null) tx.rollback();
				}catch (HibernateException e1) {
					logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> deleteAgencyCVOData is not succesful");
				}
				logger.error(e);
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
		}


	/*//Staff Data
	public List<StaffDataDO> getAgencyStaffData(long agencyId) throws BusinessException {
		List<StaffDataDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<StaffDataDO> query = session.createQuery("from StaffDataDO where created_by = ?1 and deleted = ?2 ");
			query.setParameter(1, agencyId);
			query.setParameter(2, 0);
			List<StaffDataDO> result = query.getResultList(); 
			if(result.size()>0) {
				for (StaffDataDO staffDataDO : result) {
					doList.add(staffDataDO);
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
	
	//Staff Data
		public List<StaffDataDO> getAgencyAllStaffData(long agencyId) throws BusinessException {
			List<StaffDataDO> doList = new ArrayList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				@SuppressWarnings("unchecked")
				Query<StaffDataDO> query = session.createQuery("from StaffDataDO where created_by = ?1 and (deleted = ?2 or deleted = ?3) ");
				query.setParameter(1, agencyId);
				query.setParameter(2, 0);
				query.setParameter(3, 1);
				List<StaffDataDO> result = query.getResultList(); 
				if(result.size()>0) {
					for (StaffDataDO staffDataDO : result) {
						doList.add(staffDataDO);
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

		@SuppressWarnings("unchecked")
		public void saveAgencyStaffData(List<StaffDataDO> doList) throws BusinessException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				for (StaffDataDO staffDataDO : doList) {
					String ec = staffDataDO.getEmp_code();
					Query<StaffDataDO> query = session.createQuery("from StaffDataDO where created_by = ?1 and deleted = ?2 and emp_code="+"'"+ec+"'");
					query.setParameter(1, staffDataDO.getCreated_by());
					query.setParameter(2, 1);
					List<StaffDataDO> result = query.getResultList(); 
					if(result.size()>0) {
						for (StaffDataDO staffDataDO2 : result) {
							if((staffDataDO.getEmp_code()).equalsIgnoreCase((staffDataDO2.getEmp_code()))){
								session.clear();
								staffDataDO.setId(staffDataDO2.getId());
								staffDataDO.setDeleted(0);
								staffDataDO.setCreated_date(System.currentTimeMillis());
								session.update(staffDataDO);
							}else{
								session.save(staffDataDO);
							}
						}
					}else{
						session.save(staffDataDO);
					}
				}
				tx.commit();
			}catch(Exception e) {
				try {
					if (tx != null) tx.rollback();
				}catch (HibernateException e1) {
					logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> saveAgencyStaffData  is not succesful");
				}
				e.printStackTrace();
				logger.error(e);
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
		}


	public void deleteAgencyStaffData(long itemId) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			StaffDataDO sddo = session.get(StaffDataDO.class, new Long(itemId));
			sddo.setDeleted(1);
			session.update(sddo);
			tx.commit();
		}catch(Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> deleteAgencyStaffData  is not succesful");
			}
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}
*/
	//Fleet Data
	public List<FleetDataDO> getAdminFleetData(long adminId) throws BusinessException {
		List<FleetDataDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<FleetDataDO> query = session.createQuery("from FleetDataDO where created_by = ?1 and deleted = ?2 ");
			query.setParameter(1, adminId);
			query.setParameter(2, 0);
			List<FleetDataDO> result = query.getResultList(); 
			if(result.size()>0) {
				for (FleetDataDO fleetDataDO : result) {
					doList.add(fleetDataDO);
				}
			} 
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
		return doList;
	}
	//Fleet Data
		public List<FleetDataDO> getAdminAllFleetData(long adminId) throws BusinessException {
			List<FleetDataDO> doList = new ArrayList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				@SuppressWarnings("unchecked")
				Query<FleetDataDO> query = session.createQuery("from FleetDataDO where created_by = ?1 and (deleted = ?2  or deleted = ?3)");
				query.setParameter(1, adminId);
				query.setParameter(2, 0);
				query.setParameter(3, 1);
				List<FleetDataDO> result = query.getResultList(); 
				if(result.size()>0) {
					for (FleetDataDO fleetDataDO : result) {
						doList.add(fleetDataDO);
					}
				} 
			}catch(Exception e) {
				e.printStackTrace();
				logger.error(e);
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
			return doList;
		}

		@SuppressWarnings("unchecked")
		public void saveAdminFleetData(List<FleetDataDO> doList) throws BusinessException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				for (FleetDataDO fleetDataDO : doList) {
					String vn = fleetDataDO.getVehicle_no();
					Query<FleetDataDO> query = session.createQuery("from FleetDataDO where created_by = ?1 and deleted = ?2 and vehicle_no="+"'"+vn+"'");
					query.setParameter(1, fleetDataDO.getCreated_by());
					query.setParameter(2, 1);
					List<FleetDataDO> result = query.getResultList(); 
					if(result.size()>0) {
						for (FleetDataDO fleetDataDO2 : result) {
							if((fleetDataDO.getVehicle_no()).equalsIgnoreCase((fleetDataDO2.getVehicle_no()))){
								session.clear();
								fleetDataDO.setId(fleetDataDO2.getId());
								fleetDataDO.setDeleted(0);
								fleetDataDO.setCreated_date(System.currentTimeMillis());
								session.update(fleetDataDO);
							}else{
								session.save(fleetDataDO);
							}
						}
					}else{
						session.save(fleetDataDO);
					}
				}
				tx.commit();
			}catch(Exception e) {
				try {
					if (tx != null) tx.rollback();
				}catch (HibernateException e1) {
					logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> saveAgencyFleetData  is not succesful");
				}
				e.printStackTrace();
				logger.error(e);
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
		}


	public void deleteAdminFleetData(long itemId) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			FleetDataDO fddo = session.get(FleetDataDO.class, new Long(itemId));
			fddo.setDeleted(1);
			session.update(fddo);
			tx.commit();
		}catch(Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> deleteAgencyFleetData is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}

	//Bank Data
	public List<BankDataDO> getAdminBankData(long adminId) throws BusinessException {
		List<BankDataDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<BankDataDO> query = session.createQuery("from BankDataDO where created_by = ?1 and deleted = ?2 ");
			query.setParameter(1, adminId);
			query.setParameter(2, 0);
			List<BankDataDO> result = query.getResultList(); 
			if(result.size()>0) {
				for (BankDataDO bankDataDO : result) {
					doList.add(bankDataDO);
				}
			} 
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
		return doList;
	}

		
	//Bank Data
		public List<BankDataDO> getAdminAllBankData(long adminId) throws BusinessException {
			List<BankDataDO> doList = new ArrayList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				@SuppressWarnings("unchecked")
				Query<BankDataDO> query = session.createQuery("from BankDataDO where created_by = ?1 and (deleted = ?2 or deleted = ?3) ");
				query.setParameter(1, adminId);
				query.setParameter(2, 0);
				query.setParameter(3, 1);
				List<BankDataDO> result = query.getResultList(); 
				if(result.size()>0) {
					for (BankDataDO bankDataDO : result) {
						doList.add(bankDataDO);
					}
				} 
			}catch(Exception e) {
				e.printStackTrace();
				logger.error(e);
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
			return doList;
		}

		@SuppressWarnings("unchecked")
		public void saveAdminBankData(List<BankDataDO> doList) throws BusinessException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				for (BankDataDO bankDataDO : doList) {
					String acn = bankDataDO.getBank_acc_no();
					Query<BankDataDO> query = session.createQuery("from BankDataDO where created_by = ?1 and deleted = ?2 and bank_acc_no="+"'"+acn+"'");
					query.setParameter(1, bankDataDO.getCreated_by());
					query.setParameter(2, 1);
					List<BankDataDO> result = query.getResultList(); 
					if(result.size()>0) {
						for (BankDataDO bankDataDO2 : result) {
							if((bankDataDO.getBank_acc_no()).equalsIgnoreCase((bankDataDO2.getBank_acc_no()))){
								session.clear();
								bankDataDO.setId(bankDataDO2.getId());
								bankDataDO.setDeleted(0);
								bankDataDO.setCreated_date(System.currentTimeMillis());
								session.update(bankDataDO);
							}else{
								session.save(bankDataDO);
							}
						}
					}else{
						session.save(bankDataDO);
					}
				}
				tx.commit();
			}catch(Exception e) {
				try {
					if (tx != null) tx.rollback();
				}catch (HibernateException e1) {
					logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> saveAgencyBankData is not succesful");
				}
				logger.error(e);
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
		}

	public void deleteAdminBankData(long itemId) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			BankDataDO bddo = session.get(BankDataDO.class, new Long(itemId));
			bddo.setDeleted(1);
			session.update(bddo);
			tx.commit();
		}catch(Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> deleteAgencyBankData is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}
	
	
	
	// Equipment Data
	public List<ProductDataDO> getAdminProductData(long adminId) throws BusinessException {
		List<ProductDataDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<ProductDataDO> query = session
					.createQuery("from ProductDataDO where created_by = ?1 and deleted = ?2 ");
			query.setParameter(1, adminId);
			query.setParameter(2, 0);
			List<ProductDataDO> result = query.getResultList();
			if (result.size() > 0) {
				for (ProductDataDO doObj : result) {
					doList.add(doObj);
				}
			}
		}catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
		return doList;
	}

	// Equipment Data
	public List<ProductDataDO> getAdminAllProductData(long adminId) throws BusinessException {
		List<ProductDataDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<ProductDataDO> query = session
				.createQuery("from ProductDataDO where created_by = ?1 and (deleted = ?2 or deleted = ?3) ");
			query.setParameter(1, adminId);
			query.setParameter(2, 0);
			query.setParameter(3, 1);
			List<ProductDataDO> result = query.getResultList();
			if (result.size() > 0) {
				for (ProductDataDO doObj : result) {
					doList.add(doObj);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		} finally {
			session.clear();
			session.close();
		}
		return doList;
	}

	// Deleted Equipment Data
	public List<ProductDataDO> getAdminDProductData(long adminId) throws BusinessException {
		List<ProductDataDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<ProductDataDO> query = session
					.createQuery("from ProductDataDO where created_by = ?1 and deleted = ?2");
			query.setParameter(1, adminId);
			query.setParameter(2, 1);
			List<ProductDataDO> result = query.getResultList();
			if (result.size() > 0) {
				for (ProductDataDO doObj : result) {
					doList.add(doObj);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} finally {
			session.clear();
			session.close();
		}
		return doList;
	}



	@SuppressWarnings("unchecked")
	public void saveAdminProductData(List<ProductDataDO> doList, List<AdminStockDataDO> asdoList) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (ProductDataDO doObj : doList) {
				int pc = doObj.getProd_code();

				Query<ProductDataDO> query = session.createQuery(
						"from ProductDataDO where created_by = ?1 and deleted = ?2 and prod_code =?3");
				query.setParameter(1, doObj.getCreated_by());
				query.setParameter(2, 1);
				query.setParameter(3, pc);

				List<ProductDataDO> result = query.getResultList();
				if (result.size() > 0) {
					for (ProductDataDO doObj2 : result) {
//						session.clear();
						long effdate = doObj2.getEffective_date();
//						doObj2.setSecurity_deposit(doObj.getSecurity_deposit());
						doObj2.setEffective_date(doObj.getEffective_date());
						doObj2.setDeleted(0);
						doObj2.setModified_date(System.currentTimeMillis());
						doObj2.setModified_by(doObj.getCreated_by());
						session.update(doObj2);

						for (AdminStockDataDO asdoObj : asdoList) {
							if(doObj.getProd_code() == asdoObj.getProd_code()){
								
								Query<AdminStockDataDO> asdDOq = session.createQuery(
										"from AdminStockDataDO where created_by = ?1 and deleted != ?2 and prod_code =" + pc + " and trans_date <= ?3 order by trans_date, created_date");
								asdDOq.setParameter(1, doObj.getCreated_by());
								asdDOq.setParameter(2, 3);
//								asdDOq.setParameter(3, effdate);
								asdDOq.setParameter(3, doObj.getEffective_date());
								List<AdminStockDataDO> asresult  = asdDOq.getResultList();

								String invNo = "";
								long oldEffDate = 0;
								long stockCount = 0;
								if(asresult.size() == 1){
									for(AdminStockDataDO asdo : asresult){
										invNo = asdo.getInv_no();
									}
								}else if(asresult.isEmpty()) {
									invNo = "EMPTY";
								}else if(asresult.size() > 1){
									for(AdminStockDataDO asdDo : asresult){
										if(asdDo.getInv_no().equalsIgnoreCase("NA")) {
											oldEffDate = asdDo.getTrans_date();
											break;
										}
									}
									
									Query<Long> asdDOCountq = session.createQuery(
											"select count(*) from AgencyStockDataDO where created_by = ?1 and deleted != ?2 and prod_code =" + pc + " and trans_date between ?3 and ?4");
									asdDOCountq.setParameter(1, doObj.getCreated_by());
									asdDOCountq.setParameter(2, 3);
									asdDOCountq.setParameter(3, oldEffDate);
									asdDOCountq.setParameter(4, doObj.getEffective_date());
									stockCount  = asdDOCountq.getSingleResult();
									if(oldEffDate == doObj.getEffective_date())
										stockCount = 1;
								}
								
								if((asresult.size() <= 1) && ((invNo.equals("NA")) || (invNo.equals("EMPTY"))) && (doObj.getEffective_date() <= effdate) && (stockCount <= 1)) {
//								if(doObj.getEffective_date() <= effdate) {
									
									// SELECT * FROM AGENCY_STOCK_DATA WHERE CREATED_BY=12345678 AND DELETED=1 AND PROD_CODE=4 AND INV_NO="NA"; 
									Query<AdminStockDataDO> asdDOquery = session.createQuery(
											"from AdminStockDataDO where created_by = ?1 and deleted = ?2 and prod_code =" + pc + " and inv_no = ?3 order by trans_date,created_date").setMaxResults(1);
									asdDOquery.setParameter(1, doObj.getCreated_by());
									asdDOquery.setParameter(2, 1);
									asdDOquery.setParameter(3, "NA");
									List<AdminStockDataDO> asdDOres = asdDOquery.getResultList();
									
									if(!(asdDOres.isEmpty())){
										for (AdminStockDataDO asdDO : asdDOres) {
//											asdoObj.setId(asdDO.getId());
											asdDO.setTrans_date(doObj.getEffective_date());
											asdDO.setDeleted(0);
											asdDO.setModified_date(System.currentTimeMillis());
											asdDO.setModified_by(doObj.getCreated_by());
											session.update(asdDO);
										}
									}								
								}else {
									// SELECT * FROM AGENCY_STOCK_DATA WHERE CREATED_BY=12345678 AND DELETED!=3 AND PROD_CODE=4 AND TRANS_DATE <= 3 ORDER BY TRANS_DATE DESC, CREATED_DATE DESC LIMIT 1;
									Query<AdminStockDataDO> asdDOqry = session.createQuery("from AdminStockDataDO where created_by = ?1 and deleted != ?2 "
											+ "and prod_code =" + pc + " and trans_date <= ?3 order by trans_date desc,created_date desc").setMaxResults(1);
									asdDOqry.setParameter(1, doObj.getCreated_by());
									asdDOqry.setParameter(2, 3);
									asdDOqry.setParameter(3, doObj.getEffective_date());
									List<AdminStockDataDO> asdDOr = asdDOqry.getResultList();
									
									if(!(asdDOr.isEmpty())){
										for (AdminStockDataDO asdDOobj : asdDOr) {
									
											asdoObj.setTrans_date(doObj.getEffective_date());
											asdoObj.setRef_id(0);
											asdoObj.setInv_no("NA");
											asdoObj.setStock_units(asdDOobj.getStock_units());
											
											session.save(asdoObj);
										}
									}
								}

								break;
							}
						}
							
					}
				}else {
					session.save(doObj);					
					for (AdminStockDataDO asdoObj : asdoList) {
						if(doObj.getProd_code() == asdoObj.getProd_code()){
							asdoObj.setRef_id(0);
							asdoObj.setInv_no("NA");
							session.save(asdoObj);
							break;
						}
					}
				}
			}
			tx.commit();
		} catch (Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> PPMasterDataPersistenceManager --> saveAgencyEquipmentData  is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} finally {
			session.clear();
			session.close();
		}
	}
	
	

	
	@SuppressWarnings("unchecked")
	public void deleteAdminProductData(long id) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ProductDataDO doObj = session.get(ProductDataDO.class, new Long(id));
			int pc = doObj.getProd_code();
			
			Query<ProductPriceDataDO> query = session.createQuery("from ProductPriceDataDO where created_by = ?1 and deleted = ?2 and prod_code =" + pc);
			query.setParameter(1, doObj.getCreated_by());
			query.setParameter(2, 0);
			List<ProductPriceDataDO> result = query.getResultList();
			if (result.size() > 0) {
				for (ProductPriceDataDO doObj2 : result) {
					doObj2.setDeleted(1);
					session.update(doObj2);
				}
			}
			doObj.setDeleted(1);
			session.update(doObj);
			
			Query<AdminStockDataDO> asdDOqry = session.createQuery("from AdminStockDataDO where created_by = ?1 and deleted = ?2 and inv_no = ?3 and prod_code =" + pc);
			asdDOqry.setParameter(1, doObj.getCreated_by());
			asdDOqry.setParameter(2, 0);
			asdDOqry.setParameter(3, "NA");
			List<AdminStockDataDO> asdDOres = asdDOqry.getResultList();
			if (asdDOres.size() > 0) {
				for (AdminStockDataDO asDdo : asdDOres) {
					asDdo.setDeleted(1);
					asDdo.setModified_by(doObj.getCreated_by());
					asDdo.setModified_date(System.currentTimeMillis());
					session.update(asDdo);
				}
			}
			
			tx.commit();
		} catch (Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> PPMasterDataPersistenceManager --> deleteAgencyEquipmentData  is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} finally {
			session.clear();
			session.close();
		}
	}

	
	
	// Product Price Data
		public List<ProductPriceDataDO> getAdminProductPriceData(long adminId) throws BusinessException {
			List<ProductPriceDataDO> doList = new ArrayList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				@SuppressWarnings("unchecked")
				Query<ProductPriceDataDO> query = session
						.createQuery("from ProductPriceDataDO where created_by = ?1 and deleted = ?2 ");
				query.setParameter(1, adminId);
				query.setParameter(2, 0);
				List<ProductPriceDataDO> result = query.getResultList();
				if (result.size() > 0) {
					for (ProductPriceDataDO doObj : result) {
						doList.add(doObj);
					}
				}
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			} finally {
				session.clear();
				session.close();
			}
			return doList;
		}

		public void saveAdminProductPriceData(List<ProductPriceDataDO> doList) throws BusinessException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				for (ProductPriceDataDO doObj : doList) {
					session.save(doObj);
				}
				tx.commit();
			} catch (Exception e) {
				try {
					if (tx != null) tx.rollback();
				}catch (HibernateException e1) {
					logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> PPMasterDataPersistenceManager --> saveAgencyRefillPriceData  is not succesful");
				}
				e.printStackTrace();
				logger.error(e);
				throw new BusinessException(e.getMessage());
			} finally {
				session.clear();
				session.close();
			}
		}

		public void deleteAdminProductPriceData(long id) throws BusinessException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				ProductPriceDataDO doObj = session.get(ProductPriceDataDO.class, new Long(id));
				doObj.setDeleted(1);
				session.update(doObj);
				tx.commit();
			} catch (Exception e) {
				try {
					if (tx != null) tx.rollback();
				}catch (HibernateException e1) {
					logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> PPMasterDataPersistenceManager --> deleteAgencyRefillPriceData  is not succesful");
				}
				logger.error(e);
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			} finally {
				session.clear();
				session.close();
			}
		}

	/*
	//Expenditure Data
	public List<ExpenditureDataDO> getAgencyExpenditureData(long agencyId) throws BusinessException {
		List<ExpenditureDataDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<ExpenditureDataDO> query = session.createQuery("from ExpenditureDataDO where created_by = ?1 and deleted = ?2 ");
			query.setParameter(1, agencyId);
			query.setParameter(2, 0);
			List<ExpenditureDataDO> result = query.getResultList(); 
			if(result.size()>0) {
				for (ExpenditureDataDO edo : result) {
					doList.add(edo);
				}
			} 
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
		return doList;
	}

	public void saveAgencyExpenditureData(List<ExpenditureDataDO> doList) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (ExpenditureDataDO edo : doList) {
				session.save(edo);
			}
			tx.commit();
		}catch(Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> saveAgencyExpenditureData is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}

	public void deleteAgencyExpenditureData(long itemId) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ExpenditureDataDO dataDO = session.get(ExpenditureDataDO.class, new Long(itemId));
			dataDO.setDeleted(1);
			session.update(dataDO);
			tx.commit();
		}catch(Exception e) {
			try {
				if(tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> deleteAgencyExpenditureData is not succesful");
			}
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}

	//Credit Limits Data
	public List<CreditLimitsDataDO> getAgencyCreditLimitsData(long agencyId) throws BusinessException {
		List<CreditLimitsDataDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<CreditLimitsDataDO> query = session.createQuery("from CreditLimitsDataDO where created_by = ?1 and deleted = ?2 ");
			query.setParameter(1, agencyId);
			query.setParameter(2, 0);
			List<CreditLimitsDataDO> result = query.getResultList(); 
			if(result.size()>0) {
				for (CreditLimitsDataDO dataDO : result) {
					doList.add(dataDO);
				}
			} 
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
		return doList;
	}

	public void saveAgencyCreditLimitsData(List<CreditLimitsDataDO> doList) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (CreditLimitsDataDO edo : doList) {
				session.save(edo);
			}
			tx.commit();
		}catch(Exception e) {
			try {
				if(tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> saveAgencyCreditLimitsData is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}

	public void deleteAgencyCreditLimitsData(long itemId) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			CreditLimitsDataDO dataDO = session.get(CreditLimitsDataDO.class, new Long(itemId));
			dataDO.setDeleted(1);
			session.update(dataDO);
			tx.commit();
		}catch(Exception e) {
			try {
				if(tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> deleteAgencyCreditLimitsData is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void updateCreditlimitsData(Map<String, String> requestparams,long agencyId) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		 String cid=requestparams.get("cid");
		 String dis19kgndne=requestparams.get("dis19kgndne");
		 String dis19kgcgas=requestparams.get("dis19kgcgas");
		 String dis35kgndne=requestparams.get("dis35kgndne");
		 String dis47_5kgndne=requestparams.get("dis47_5kgndne");
		 String dis450kgsumo=requestparams.get("dis450kgsumo");

		try {
			tx = session.beginTransaction();
			Query query1 = session.createQuery(
					"UPDATE CreditLimitsDataDO SET disc_19kg_ndne='"+dis19kgndne+"', disc_19kg_cutting_gas='"+dis19kgcgas+"',disc_35kg_ndne='"+dis35kgndne+"',disc_47_5kg_ndne='"+dis47_5kgndne+"', disc_450kg_sumo='"+dis450kgsumo+"' WHERE created_by='"+agencyId+"' AND cust_id='"+Long.parseLong(cid)+"'");
			query1.executeUpdate();
			tx.commit();
		}catch(Exception e) {
			try {
				if(tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> updateCreditlimitsData is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}*/
}
