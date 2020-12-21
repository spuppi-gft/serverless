package com.santander.arsenal.serverless.multicloudfunction.functions;

import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.ArsenalFunction;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.ArsenalHttpMessage;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.HttpStatus;

public class ArsenalFunctionsHttp {
	
	protected static final String FUNCTION_NAME = "ArsenalMulticloudFunction";
	
	@ArsenalFunction(name = FUNCTION_NAME)
	public ArsenalHttpMessage HttpResponse (ArsenalHttpMessage request, Object context) {
		
		System.out.println("Esta e a funcao multicloud");
		
		System.out.println(request.getMethod());
		
		System.out.println(request.getHeaders());
		
		System.out.println(request.getBody());
		
		return new ArsenalHttpMessage.Builder(request.getMethod())
				.status(HttpStatus.OK)
				.header("ArsenalFunction","Serverless")
				.header("Version","1.0.0")
				.body(request.getBody())
				.build();
	}
}
