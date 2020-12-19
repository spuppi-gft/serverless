package com.santander.arsenal.serverless.multicloudfunction.multicloud.azure;

import java.util.Optional;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.santander.arsenal.serverless.multicloudfunction.functions.ArsenalFunctionsHttp;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.Serverless;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.request.ArsenalHttpRequest;

public class HttpResponseMessageAzure extends ArsenalFunctionsHttp{

	@FunctionName(FUNCTION_NAME)
	public HttpResponseMessage httpResponseMessage(@HttpTrigger(name = FUNCTION_NAME) 
			HttpRequestMessage<Optional<String>> request, final ExecutionContext context) {

		context.getLogger().info("Java HTTP trigger processed a request.");

		Serverless s = new Serverless();
		Object responseEvent = s.ArsenalFunctionScan(new ArsenalFunctionsHttp(), 
				new ArsenalHttpRequest.Builder(request.getHttpMethod().name())
				.headers(request.getHeaders())
				.body(request.getBody().get())
				.build(), null);
		
		return request.createResponseBuilder(HttpStatus.OK).
				body(responseEvent).
				header("X-Powered-By", "Azure Functions").
				header("Content-Type", "application/json").
				build();
	}	
}
