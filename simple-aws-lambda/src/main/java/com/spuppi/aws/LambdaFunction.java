package com.spuppi.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.HashMap;
import java.util.Map;

//public class LambdaFunction implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

public class LambdaFunction implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	//	@Override
	//	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
	//
	//		context.getLogger().log("Input: " + input);
	//
	//		String name = input.get("name") != null ? String.valueOf(input.get("name")) : null;
	//		
	//		Map<String, String> headers = new HashMap<>();
	//		headers.put("X-Powered-By", "AWS Lambda & Serverless");
	//		headers.put("Content-Type", "application/json");
	//		
	//		if (name == null) {
	//			return ApiGatewayResponse.builder()
	//					.setStatusCode(400)
	//					.setObjectBody("Please pass a name on the query string or in the request body")
	//					.setHeaders(headers)
	//					.build();
	//		} else {
	//			return ApiGatewayResponse.builder()
	//					.setStatusCode(200)
	//					.setObjectBody("Hello, " + name)
	//					.setHeaders(headers)
	//					.build();
	//		}
	//	}

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

		input.getPathParameters();
		String requestString = input.getBody();

		System.out.println(requestString);

		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
		responseEvent.setBody("{\"name\": \"duke\"}");
		responseEvent.setStatusCode(200);

		Map<String, String> headers = new HashMap();
		headers.put("X-Custom-Header", "Olá Mundo Serverless!");
		responseEvent.setHeaders(headers);

		return responseEvent;
	}
}
