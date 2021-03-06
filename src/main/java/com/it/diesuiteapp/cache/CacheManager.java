package com.it.diesuiteapp.cache;

import java.util.ArrayList;
import java.util.List;

import com.it.diesuiteapp.framework.managers.CachePersistenceManager;
import com.it.diesuiteapp.framework.model.ProductCategoryDO;


public class CacheManager {

	public static boolean isCacheLoaded = false;
	
	public static List<ProductCategoryDO> prodCatDatDOList = new ArrayList<>();
//	public static List<StatutoryItemDO> statutoryItemDOList = new ArrayList<>();
	public static List<ProductCategoryDO> cylinderTypesDOList = new ArrayList<>();
	public static List<ProductCategoryDO> arbTypesDOList = new ArrayList<>();
	public static List<ProductCategoryDO> serviceTypesDOList = new ArrayList<>();
//	public static List<CashTransEnumDO> CashTransEnumDOList = new ArrayList<>();

	public static List<ProductCategoryDO> getProductCategoryDataList(){
		if(prodCatDatDOList.size()==0) {
			synchronized (CacheManager.class) {
				if(prodCatDatDOList.size()==0) {
					prodCatDatDOList = getCachePersistenceManager().getProductCatogoryData();
					/*setCylinderTypesDOList();
					setARBTypesDOList();
					setServiceTypesDOList();*/
				}
			}
		}
		return prodCatDatDOList;
	}
	
	/*public static List<StatutoryItemDO> getStatutoryItemDataList(){
		if(statutoryItemDOList.size()==0) {
			synchronized (CacheManager.class) {
				if(statutoryItemDOList.size()==0) {
					statutoryItemDOList = getCachePersistenceManager().getStatutoryItemData();
				}
			}
		}
		return statutoryItemDOList;
	}
	
	public static List<ProductCategoryDO> getCylinderTypesDataList(){
		if(prodCatDatDOList.size()==0)
			getProductCategoryDataList();
		return cylinderTypesDOList;
	}
	
	public static List<ProductCategoryDO> getARBTypesDataList(){
		if(prodCatDatDOList.size()==0)
			getProductCategoryDataList();
		return arbTypesDOList;
	}
	
	public static List<ProductCategoryDO> getServiceTypesDataList(){
		if(prodCatDatDOList.size()==0)
			getProductCategoryDataList();
		return serviceTypesDOList;
	}
	
	private static void setCylinderTypesDOList(){
		for (ProductCategoryDO productCategoryDO : prodCatDatDOList) {
			if(productCategoryDO.getCat_type() == 1 || productCategoryDO.getCat_type() == 2 || productCategoryDO.getCat_type() == 3)
				cylinderTypesDOList.add(productCategoryDO);
		}
	}

	private static void setARBTypesDOList(){
		for (ProductCategoryDO productCategoryDO : prodCatDatDOList) {
			if(productCategoryDO.getCat_type() == 4)
				arbTypesDOList.add(productCategoryDO);
		}
	}

	private static void setServiceTypesDOList(){
		for (ProductCategoryDO productCategoryDO : prodCatDatDOList) {
			if(productCategoryDO.getCat_type() == 5)
				serviceTypesDOList.add(productCategoryDO);
		}
	}
	
	public static List<CashTransEnumDO> getCashTransEnumDataList(){
		if(CashTransEnumDOList.size()==0) {
			synchronized (CacheManager.class) {
				if(CashTransEnumDOList.size()==0) {
					CashTransEnumDOList = getCachePersistenceManager().getCashTransEnumData();
				}
			}
		}
		return CashTransEnumDOList;
	}*/
	
	private static CachePersistenceManager getCachePersistenceManager(){
		return new CachePersistenceManager();
	}
}
