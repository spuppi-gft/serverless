package com.santander.arsenal.serverless.multicloudfunction.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.santander.arsenal.serverless.multicloudfunction.functions.ArsenalFunctionsHttp;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.Serverless;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.ArsenalHttpMessage;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.HttpStatus;

@SpringBootApplication
public class MulticloudFunctionApplication {

	public static void main(String[] args) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		//SpringApplication.run(MulticloudFunctionApplication.class, args);
		
		Serverless s = new Serverless();
		//s.ArsenalFunctionScan(new ArsenalFunctionsHttp(), null, null);
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("key", "value");
		
		ArsenalHttpMessage response = s.ArsenalFunctionScan(new ArsenalFunctionsHttp(), 
				new ArsenalHttpMessage.Builder(HttpStatus.OK)
				.header("key", "value")
				.body("body")
				.build(), null);
		
		
		System.out.println(gson.toJson(gson.toJsonTree(response)));
		
	}

}
