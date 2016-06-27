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
import javax.xml.ws.RequestWrapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.productrest.dao.OrderDAO;
import com.productrest.vo.OrderVO;

@Path("/orderservice")
public class OrderService {

	 public OrderDAO orderDAO = new OrderDAO();
	
	 @PUT
	   @Path("/or1/addorder")
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
	   @Path("/or1/getorder/{orderid}")
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
	   @Path("/or1/updateorder/{orderid}")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   @RequestWrapper
	   public Response updateOrder(@PathParam("orderid") String orderid,  String orderDetails){
		   System.out.println("Service running from orderdetails :"+orderid);
		   Gson gson = new GsonBuilder().create();
		   System.out.println("GSON OBJECT CONVERION TO ORDERVO");
		   OrderVO orderVO = gson.fromJson(orderDetails, OrderVO.class);
		   orderDAO.updateOrder(orderid,orderVO);
		   return Response.ok().entity(new GenericEntity<String>("Order Updated Successfully") {}).build();
	   }
	   
	   @DELETE
	   @Path("/or1/removeorder/{orderid}")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   public Response removeOrder(@PathParam("orderid") String orderid){
		   System.out.println("Service running from orderdetails :"+orderid);
		   orderDAO.removeOrder(orderid);
		   return Response.ok().entity(new GenericEntity<String>("Order Deleted Successfully") {}).build();
	   }
}
