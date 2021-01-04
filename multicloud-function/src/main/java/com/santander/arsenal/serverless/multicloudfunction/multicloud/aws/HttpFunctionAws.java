package com.santander.arsenal.serverless.multicloudfunction.multicloud.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.Serverless;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.ArsenalHttpMessage;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.HttpMethod;

public class HttpFunctionAws implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{

	@Override
	public APIGatewayProxyResponseEvent handleRequest(
			APIGatewayProxyRequestEvent request, Context context) {

		context.getLogger().log("Java HTTP trigger processed a request.");

		Serverless s = new Serverless();
		Object response = (ArsenalHttpMessage) s.ArsenalFunctionScan(new ArsenalHttpMessage.Builder(HttpMethod.value(request.getHttpMethod()))
				.headers(request.getHeaders())
				.body(request.getBody())
				.build(), context);

		ArsenalHttpMessage responseHttp = (ArsenalHttpMessage) response;

		return new APIGatewayProxyResponseEvent()
				.withStatusCode(responseHttp.getStatus().value())
				.withHeaders(responseHttp.getHeaders())
				.withBody(responseHttp.getBody());
	}
}
