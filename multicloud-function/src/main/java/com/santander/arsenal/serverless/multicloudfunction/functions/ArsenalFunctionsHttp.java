package com.santander.arsenal.serverless.multicloudfunction.functions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.ArsenalFunction;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.request.ArsenalHttpRequest;

public class ArsenalFunctionsHttp {
	
	protected static final String FUNCTION_NAME = "ArsenalMulticloudFunction";
	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@ArsenalFunction(name = FUNCTION_NAME)
	public String HttpResponse (ArsenalHttpRequest request, Object context) {
		
		System.out.println("Esta e a funcao multicloud");
		
		return gson.toJson(gson.toJsonTree(request));
	}
}
