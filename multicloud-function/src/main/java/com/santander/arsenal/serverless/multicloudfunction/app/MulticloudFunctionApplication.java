package com.santander.arsenal.serverless.multicloudfunction.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.santander.arsenal.serverless.multicloudfunction.functions.ArsenalFunctionsHttp;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.Serverless;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.request.ArsenalHttpRequest;

@SpringBootApplication
public class MulticloudFunctionApplication {

	public static void main(String[] args) {
		//SpringApplication.run(MulticloudFunctionApplication.class, args);
		
		Serverless s = new Serverless();
		//s.ArsenalFunctionScan(new ArsenalFunctionsHttp(), null, null);
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("key", "value");		
		
		Object responseEvent = s.ArsenalFunctionScan(new ArsenalFunctionsHttp(), 
				new ArsenalHttpRequest.Builder("POST")
				.headers(headers)
				.body("{\"body\":\"corpo\"}")
				.build(), null);
		
		System.out.println(responseEvent);
	}

}
