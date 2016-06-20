package com.productrest.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.mapping.List;

import com.productrest.util.SessionFactoryUtil;
import com.productrest.vo.OrderVO;
import com.productrest.vo.ProductVO;

public class OrderDAO {
		
	public void addOrder(OrderVO orderVO){
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(orderVO);
        //insert into ORDER (PRODUCTID, ORDERQUANTITY, ORDERTOTAL, CUSTOMERID, ORDERID) values (?, ?, ?, ?, ?)

        /*SQLQuery insertQuery = session.createSQLQuery("" +
	        "INSERT INTO ORDERNEW (PRODUCTID, ORDERQUANTITY, ORDERTOTAL, CUSTOMERID, ORDERID) values (?, ?, ?, ?, ?)");
	        insertQuery.setParameter(0, orderVO.getProductId());
	        insertQuery.setParameter(1, orderVO.getOrderQuantity());
	        insertQuery.setParameter(2, orderVO.getOrderTotal());
	        insertQuery.setParameter(3, orderVO.getCustomerId());
	        insertQuery.setParameter(4, orderVO.getOrderId());
	        insertQuery.executeUpdate();*/
	        session.getTransaction().commit();
	}
	
	public OrderVO getOrder(String orderId){
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from OrderVO where orderid = :orderid");
		query.setInteger("orderid", Integer.parseInt(orderId));
		OrderVO ord = (OrderVO) query.uniqueResult();
        System.out.println("Get Unique Order  ::"+ord.getProductId()+","+ord.getOrderId());
        return ord;
	}
	

	public void updateOrder(String orderId, OrderVO orderVO){
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
		session.saveOrUpdate(orderVO);
		session.getTransaction().commit();
	}

	public void removeOrder(String orderId){
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
		session.delete(getOrder(orderId));
		session.getTransaction().commit();
	}
}
