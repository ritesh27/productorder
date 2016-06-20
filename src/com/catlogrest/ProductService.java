package com.catlogrest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.productrest.dao.OrderDAO;
import com.productrest.dao.ProductDAO;
import com.productrest.vo.OrderVO;
import com.productrest.vo.ProductVO;

@Path("/ProductService")
public class ProductService {
	   public ProductDAO prodDAO = new ProductDAO();
	   public OrderDAO orderDAO = new OrderDAO();
	   
	   /**
	    *  {
        	'productId': '123',
        	'productName': 'Shirts',
        	'productDesc': 'Shirts for Boys',
        	'productPrice':10.99,
        	'productQuantity':10
}
	    * */
	   
	   @PUT
	   @Path("/product/addProduct")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   public Response addProduct(String productDetails){
		   System.out.println("Service running from productDetails :"+productDetails);
		   Gson gson = new GsonBuilder().create();
		   System.out.println("GSON OBJECT CONVERION TO PRODUCTVO");
		   ProductVO productVO = gson.fromJson(productDetails, ProductVO.class);
		   prodDAO.addProduct(productVO);
		   return Response.ok().entity(new GenericEntity<String>("Product added Successfully") {}).build();
	   }

	   @GET
	   @Path("/product/getProducts")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   public Response getProducts(){
		   prodDAO.getProducts();
		   return Response.ok(true).build();
	   }
	   
	   @GET
	   @Path("/product/getProduct/{productid}")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   public String getProduct(@PathParam("productid") String productid){
		   System.out.println("Service running for get ProductId :"+productid);
		   Gson gson = new GsonBuilder().create();
		   System.out.println("GSON OBJECT CONVERION TO PRODUCTVO");
		   //ProductVO productVO = gson.fromJson(productId, ProductVO.class);
		   ProductVO prodVO = prodDAO.getProduct(productid);
		   String productDetails;
		   productDetails = "{\"productdetails\" :" + gson.toJson(prodVO) + "}";
		   //System.out.println(gson.toJson(productVO));
		   return productDetails;
		   //return Response.ok().entity(new GenericEntity<String>("Success") {}).build();
	   }
	   
	   /**
	    *{
        	'productId': '1',
        	'orderId': '1',
            'customerId' : '1',
        	'orderQuantity': '1',
			'orderTotal': '1'
		}
	    * */
	   @PUT
	   @Path("/order/addOrder")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   public Response addOrder(String orderDetails){
		   System.out.println("Service running from orderdetails :"+orderDetails);
		   Gson gson = new GsonBuilder().create();
		   System.out.println("GSON OBJECT CONVERION TO ORDERVO");
		   OrderVO orderVO = gson.fromJson(orderDetails, OrderVO.class);
		   orderDAO.addOrder(orderVO);
		   return Response.ok().entity(new GenericEntity<String>("Order Created Successfully") {}).build();
	   }

	   @GET
	   @Path("/order/getOrder/{orderid}")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   public String getOrder(@PathParam("orderid") String orderId){
		   System.out.println("Service running for get orderid :"+orderId);
		   Gson gson = new GsonBuilder().create();
		   System.out.println("GSON OBJECT CONVERION TO ORDERVO");
		   OrderVO prodVO = orderDAO.getOrder(orderId);
		   String orderdetails;
		   orderdetails = "{\"orderdetails\" :" + gson.toJson(prodVO) + "}";
		   //System.out.println(gson.toJson(productVO));
		   return orderdetails;
		   //return Response.ok().entity(new GenericEntity<String>("Success") {}).build();
	   }
	   

	   @POST
	   @Path("/order/updateOrder/{orderid}")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   public Response updateOrder(@PathParam("orderid") String orderid,String orderDetails){
		   System.out.println("Service running from orderdetails :"+orderid);
		   Gson gson = new GsonBuilder().create();
		   System.out.println("GSON OBJECT CONVERION TO ORDERVO");
		   OrderVO orderVO = gson.fromJson(orderDetails, OrderVO.class);
		   orderDAO.updateOrder(orderid,orderVO);
		   return Response.ok().entity(new GenericEntity<String>("Order Updated Successfully") {}).build();
	   }
	   
	   @DELETE
	   @Path("/order/removeOrder/{orderid}")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   public Response removeOrder(@PathParam("orderid") String orderid){
		   System.out.println("Service running from orderdetails :"+orderid);
		   orderDAO.removeOrder(orderid);
		   return Response.ok().entity(new GenericEntity<String>("Order Deleted Successfully") {}).build();
	   }
}