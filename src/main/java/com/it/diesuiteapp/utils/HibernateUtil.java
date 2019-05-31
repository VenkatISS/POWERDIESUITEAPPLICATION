package com.it.diesuiteapp.utils;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.it.diesuiteapp.framework.model.AccountActivationDO;
import com.it.diesuiteapp.framework.model.AdminCVOBalanceDataDO;
import com.it.diesuiteapp.framework.model.AdminDO;
import com.it.diesuiteapp.framework.model.AdminDetailsDO;
import com.it.diesuiteapp.framework.model.AdminStockDataDO;
import com.it.diesuiteapp.framework.model.BankDataDO;
import com.it.diesuiteapp.framework.model.CVODataDO;
import com.it.diesuiteapp.framework.model.FleetDataDO;
import com.it.diesuiteapp.framework.model.ProductCategoryDO;
import com.it.diesuiteapp.framework.model.ProductDataDO;
import com.it.diesuiteapp.framework.model.ProductPriceDataDO;
import com.it.diesuiteapp.framework.model.UserDetailsDO;
import com.it.diesuiteapp.framework.model.transactions.sales.QuotationsDO;
import com.it.diesuiteapp.framework.model.vos.AgencyVO;





public class HibernateUtil {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			synchronized (HibernateUtil.class) {
				if(sessionFactory == null) {
					try {
						// Create registry builder
						StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

						// Hibernate settings equivalent to hibernate.cfg.xml's properties
						Map<String, String> settings = new HashMap<>();
						settings.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
						settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/diesuitedb");
						settings.put("hibernate.connection.username", "root");
						settings.put("hibernate.connection.password", "root");
//						settings.put("hibernate.connection.password", "Jagan@321");
						settings.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
						settings.put("hibernate.show_sql", "true");
						settings.put("hibernate.id.new_generator_mappings", "false");
						settings.put("hibernate.connection.autocommit", "false");
						settings.put("hibernate.connection.autoReconnect", "true");
//						'autoReconnect=true'
						// Apply settings
						registryBuilder.applySettings(settings);

						// Create registry
						registry = registryBuilder.build();
						
						// Create MetadataSources
						MetadataSources sources = new MetadataSources(registry)
								.addAnnotatedClass(AdminDO.class)
								.addAnnotatedClass(AdminDetailsDO.class)
								.addAnnotatedClass(AccountActivationDO.class)
								.addAnnotatedClass(UserDetailsDO.class)
								.addAnnotatedClass(CVODataDO.class)
								.addAnnotatedClass(AdminCVOBalanceDataDO.class)
								.addAnnotatedClass(BankDataDO.class)
								.addAnnotatedClass(FleetDataDO.class)

								.addAnnotatedClass(AdminStockDataDO.class)
								.addAnnotatedClass(ProductCategoryDO.class)
								.addAnnotatedClass(ProductDataDO.class)
								.addAnnotatedClass(ProductPriceDataDO.class)
								.addAnnotatedClass(QuotationsDO.class)
								.addAnnotatedClass(AgencyVO.class)

								
								;

						// Create Metadata
						Metadata metadata = sources.getMetadataBuilder().build();

						// Create SessionFactory
						sessionFactory = metadata.getSessionFactoryBuilder().build();

					} catch (Exception e) {
						e.printStackTrace();
						if (registry != null) {
							StandardServiceRegistryBuilder.destroy(registry);
						}
					}
				}
			}
		}
		return sessionFactory;
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}
// 04046474747