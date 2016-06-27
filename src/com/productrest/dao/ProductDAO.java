package com.productrest.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.productrest.util.SessionFactoryUtil;
import com.productrest.vo.ProductVO;

public class ProductDAO {
	
	public void addProduct(){
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ProductVO product = new ProductVO();
        product.setProductId(3);
        product.setProductName("PROD3");
        product.setProductDesc("ProdDesc");
        product.setProductPrice(1.45);
        product.setProductQuantity(10);
        session.save(product);
        session.getTransaction().commit();    
	}
	
	public void addOrUpdateProduct(ProductVO productVO){
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(productVO);
        session.getTransaction().commit();
	}
	
	public void deleteProduct(ProductVO productVO){
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(productVO);
        session.getTransaction().commit();
	}
	
	public void getProducts(){
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Query query = session.createQuery("from product");
		List<ProductVO> prodList = query.list();
		for(ProductVO prod : prodList){
			System.out.println("List of products ::"+prod.getProductId()+","+prod.getProductName());
		}
	}
	
	public ProductVO getProduct(String productId){
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from ProductVO where productid= :productid");
		query.setInteger("productid", Integer.parseInt(productId));
		ProductVO prod = (ProductVO) query.uniqueResult();
        System.out.println("Get Unique Product  ::"+prod.getProductId()+","+prod.getProductName());
        return prod;
	}
}
