package com.catlogrest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.productrest.dao.ProductDAO;
import com.productrest.vo.ProductVO;

@Path("/productservice")
public class ProductService {
	   public ProductDAO prodDAO = new ProductDAO();
	  
	   
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
	   @Path("/pr1/addproduct")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   public Response addProduct(String productDetails){
		   System.out.println("Service running from productDetails :"+productDetails);
		   Gson gson = new GsonBuilder().create();
		   System.out.println("GSON OBJECT CONVERION TO PRODUCTVO");
		   ProductVO productVO = gson.fromJson(productDetails, ProductVO.class);
		   prodDAO.addOrUpdateProduct(productVO);
		   return Response.ok().entity(new GenericEntity<String>("Product added Successfully") {}).build();
	   }

	   @GET
	   @Path("/pr1/getproducts")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   public Response getProducts(){
		   prodDAO.getProducts();
		   return Response.ok(true).build();
	   }
	   
	   @GET
	   @Path("/pr1/getproduct/{productid}")
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
	   
	   @POST
	   @Path("/pr1/updateproduct/{productid}")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   public Response updateproduct(@PathParam("productid") String productid,String productDetails ){
		   System.out.println("Service running from productDetails :"+productDetails);
		   Gson gson = new GsonBuilder().create();
		   System.out.println("GSON OBJECT CONVERION TO PRODUCTVO");
		   ProductVO productVO = gson.fromJson(productDetails, ProductVO.class);
		   productVO.setProductId(Integer.parseInt(productid));
		   prodDAO.addOrUpdateProduct(productVO);
		   return Response.ok().entity(new GenericEntity<String>("Product update Successfully") {}).build();
	   }
	   
	   @DELETE
	   @Path("/pr1/deleteproduct/{productid}")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   public Response deleteproduct(@PathParam("productid") String productid){
		   System.out.println("Service running from productDetails :"+productid);
		   Gson gson = new GsonBuilder().create();
		   System.out.println("GSON OBJECT CONVERION TO PRODUCTVO");
		   ProductVO productVO = prodDAO.getProduct(productid);//gson.fromJson(productDetails, ProductVO.class);
		   prodDAO.deleteProduct(productVO);
		   return Response.ok().entity(new GenericEntity<String>("Product deleted Successfully") {}).build();
	   }
	  
}