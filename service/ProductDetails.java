package service;

import java.util.ArrayList;

import dao.ProductDao;
import model.ProductModel;

public class ProductDetails {
	 ProductDao prdDao = new ProductDao();
	 ArrayList<ProductModel> pdList = new ArrayList<ProductModel>();
	 

	public void addProductToList(ProductModel pd) throws ClassNotFoundException {
		pdList.add(pd);
		prdDao.storeEmpRecordInDb(pdList);	
	}	
   
	
   
}


