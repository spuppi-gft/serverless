package com.santander.arsenal.serverless.multicloudfunction.multicloud.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.santander.arsenal.serverless.multicloudfunction.functions.ArsenalFunctionsHttp;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.Serverless;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.request.ArsenalHttpRequest;

public class HttpResponseMessageAws implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{

	@Override
	public APIGatewayProxyResponseEvent handleRequest(
			APIGatewayProxyRequestEvent request, Context context) {

		context.getLogger().log("Java HTTP trigger processed a request.");

		Serverless s = new Serverless();
		Object responseEvent = s.ArsenalFunctionScan(new ArsenalFunctionsHttp(), 
				new ArsenalHttpRequest.Builder(request.getHttpMethod())
				.headers(request.getHeaders())
				.body(request.getBody())
				.build(), null);

		return new APIGatewayProxyResponseEvent()
				.withStatusCode(200)
				.withHeaders(request.getHeaders())
				.withBody(String.valueOf(responseEvent));
	}
}
