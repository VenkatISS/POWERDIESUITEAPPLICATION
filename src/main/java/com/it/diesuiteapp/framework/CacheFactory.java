package com.it.diesuiteapp.framework;

import java.util.List;

import com.it.diesuiteapp.cache.CacheManager;
import com.it.diesuiteapp.framework.model.ProductCategoryDO;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;



public class CacheFactory {

	
	public List<ProductCategoryDO> getProductTypesData() throws BusinessException {
		return CacheManager.getProductCategoryDataList();
	}
	/*public List<ProductCategoryDO> getProductCatogoryData() throws BusinessException{
		return CacheManager.getProductCategoryDataList();
	}

	public List<StatutoryItemDO> getStatutoryItemsData() throws BusinessException{
		return CacheManager.getStatutoryItemDataList();
	}
	


	public List<ProductCategoryDO> getARBTypesData() throws BusinessException {
		return CacheManager.getARBTypesDataList();
	}

	public List<ProductCategoryDO> getServiceTypesData() throws BusinessException {
		return CacheManager.getServiceTypesDataList();
	}
	public List<CashTransEnumDO> getCashTransEnumData() throws BusinessException{
		return CacheManager.getCashTransEnumDataList();
	}
*/
}
