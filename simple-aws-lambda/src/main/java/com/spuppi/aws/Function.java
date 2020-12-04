package com.spuppi.aws;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Function implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event,
			Context context) {

		context.getLogger().log("Java HTTP trigger processed a request.");
		
		Map<String, String> headers = null;
		Map<String, String> map = null;
		
		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();

		try {

			ObjectMapper objectMapper = new ObjectMapper();

			headers = new HashMap<>();
			headers.put("X-Powered-By", "AWS Lambda & Serverless");
			headers.put("Content-Type", "application/json");
			
			if(event == null || event.getBody() == null) {
				responseEvent.setBody("Please pass a name on the query string or in the request body");
				responseEvent.setStatusCode(400);
				responseEvent.setHeaders(headers);
				return responseEvent;
			}
			
			map = objectMapper.readValue(String.valueOf(event.getBody()), new TypeReference<Map<String,String>>(){});

			if (map.get("name") == null) {
				responseEvent.setBody("Please pass a name on the query string or in the request body");
				responseEvent.setStatusCode(400);
				responseEvent.setHeaders(headers);
			} else {
				responseEvent.setBody("Hello, " + map.get("name"));
				responseEvent.setStatusCode(200);
				responseEvent.setHeaders(headers);
			}
			
			return responseEvent;
		} catch (JsonParseException e) {
			responseEvent.setBody(e.getMessage());
			responseEvent.setStatusCode(400);
			responseEvent.setHeaders(headers);
			return responseEvent;
		} catch (JsonMappingException e) {
			responseEvent.setBody("Please pass a name on the query string or in the request body");
			responseEvent.setStatusCode(400);
			responseEvent.setHeaders(headers);
			return responseEvent;
		} catch (IOException e) {
			responseEvent.setBody(e.getMessage());
			responseEvent.setStatusCode(400);
			responseEvent.setHeaders(headers);
			return responseEvent;
		}
	}
}
