package com.wbl.WalmartSearchApi;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

 
public class searchItem {
	
	
    HttpResponse response;
	
	String apiKey = "bgkuxks768g63cgqm2qspmeq";

//	String url = "http://api.walmartlabs.com/v1/search?apiKey="+ apiKey + "&start=22&query=ipod";

 
 	String url = "http://api.walmartlabs.com/v1/search?apiKey="+ apiKey + "&query=ipod";

 	String url1 = "http://api.walmartlabs.com/v1/search?apiKey=" + apiKey + "&query=ipod&categoryId=3944&sort=price&order=desc";
	
	public HttpResponse getIpod() {
		
		
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet get = new HttpGet(url);
		
 		
		try {
			
			response = client.execute(get);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return response;
		
		
	}
	
	
 public HttpResponse getIpodSortbyPrice() {
	 
	 HttpClient client = HttpClientBuilder.create().build();
	 
	 HttpGet get = new HttpGet(url1);
	 
	 try {
			
			response = client.execute(get);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return response;
		
		
	 
	 
	 
 }
}
