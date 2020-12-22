package com.santander.arsenal.serverless.multicloudfunction.multicloud.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.Serverless;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.ArsenalHttpMessage;

public class HttpResponseMessageAws implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{

	@Override
	public APIGatewayProxyResponseEvent handleRequest(
			APIGatewayProxyRequestEvent request, Context context) {

		context.getLogger().log("Java HTTP trigger processed a request.");

		Serverless s = new Serverless();
		ArsenalHttpMessage response = s.ArsenalFunctionScan(new ArsenalHttpMessage.Builder(request.getHttpMethod())
				.headers(request.getHeaders())
				.body(request.getBody())
				.build(), context);
		
		return new APIGatewayProxyResponseEvent()
				.withStatusCode(response.getStatus().value())
				.withHeaders(response.getHeaders())
				.withBody(response.getBody());
	}
}
