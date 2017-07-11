package com.wbl.WalmartSearchApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class searchItemTest {
	
	
	HttpResponse response;
	searchItem wsi; 
	
	
	@BeforeClass
	public void before() {
		
		wsi =  new searchItem();
	 	
	}
	
	
	@Test
	public void itemTest() throws JSONException, IllegalStateException, IOException {
		
		response = wsi.getIpod();
		
		 
			
			JSONObject obj = new JSONObject(IOUtils.toString(response.getEntity().getContent()));
			
		 
		
		System.out.println(response.getStatusLine().getStatusCode());

		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		
		
	/*
 		
		query": "ipod",
	    "sort": "relevance",
	    "responseGroup": "base",
	    "totalResults": 21298,
	    "start": 1,
	    "numItems": 10,
		*/
		
	   System.out.println( obj.get("totalResults"));
	   System.out.println( obj.get("sort"));
	   System.out.println( obj.get("responseGroup"));
	   System.out.println( obj.get("start"));
	   System.out.println( obj.get("numItems"));
	    
	   
	   Assert.assertEquals( obj.get("totalResults"),21297);
	   Assert.assertEquals( obj.get("sort"),"relevance");
	   Assert.assertEquals( obj.get("responseGroup"),"base");
	   Assert.assertEquals( obj.get("start"),1);
	   Assert.assertEquals( obj.get("numItems"),10);
	    
		 
	  
	   JSONArray arr =  obj.getJSONArray("items");
	   
	   // assert the number of items is 10
	   
	   Assert.assertEquals(arr.length(), 10);
	   
	    obj = arr.getJSONObject(0);
	   
	   System.out.println(obj.get("itemId"));
	   System.out.println( obj.get("name"));
	   System.out.println(obj.get("salePrice"));
	   
	   Assert.assertEquals(obj.get("itemId"),42608121);
	   Assert.assertEquals( obj.get("name"),"Apple iPod touch 16GB, Assorted Colors");
	   Assert.assertEquals(obj.get("salePrice"),189.99);

	   
// reading the 10th item details 
	   
      	 obj = arr.getJSONObject(9); 
	   
	   System.out.println(obj.get("itemId"));
	   System.out.println( obj.get("name") );
	   System.out.println(obj.get("salePrice") );

	   Assert.assertEquals(obj.get("itemId"), 34008412);
	   Assert.assertEquals( obj.get("name"),"Apple iPod nano 16GB Refurbished, Space Gray");
	   Assert.assertEquals(obj.get("salePrice"),123.95);

		
	}
	
	@Test
	public void getIpodSortByPriceTest() throws JSONException, IllegalStateException, IOException {
		
		response = wsi.getIpodSortbyPrice();
		
		JSONObject obj = new JSONObject(IOUtils.toString(response.getEntity().getContent()));
		
	/*	
		"query": "ipod",
	    "categoryId": "3944",
	    "sort": "price",
	    "responseGroup": "base",
	    "totalResults": 21030,
		
		*/
		
		  System.out.println( obj.get("query"));
		   System.out.println( obj.get("categoryId"));
		   System.out.println( obj.get("sort"));
		   System.out.println( obj.get("start"));
		   System.out.println( obj.get("totalResults"));
		    
		   
		   Assert.assertEquals( obj.get("query"),"ipod");
		   Assert.assertEquals( obj.get("categoryId"),"3944");
		   Assert.assertEquals( obj.get("sort"),"price");
 		   Assert.assertEquals( obj.get("totalResults"),21030);

		
 // to assert the sorted price check the first 
 		   
 		   JSONArray arr = obj.getJSONArray("items");
 		   
 		   List<Double> price = new ArrayList<Double>();
 		   
 		   int i = 0;
 		   
  		  while(arr.get(i) != null) {
 			  
 			  obj = arr.getJSONObject(i);
 			   
  			  price.add( (Double) obj.get("salePrice"));
  			  
  			  System.out.println(price.get(i));
  			   
  			  
  			  if((i+1) == 10) {
  				  
  				  break;
  			  }
  			  
  			  else {
  				  
 // 		         Assert.assertTrue(price.get(i) <= price.get(i+1));

  				  i++;
  			  }
  			  
  			  			  
 		  }
 		  
 		 List<Double> expectedPrice = Arrays.asList(289.0d, 269.88, 254.95, 249.99, 239.99, 222.95,  199.99, 199.99, 199.99, 199.99);

  		 
 		 Assert.assertEquals(price, expectedPrice);
   
 		 
 		 
	}
	 

}
