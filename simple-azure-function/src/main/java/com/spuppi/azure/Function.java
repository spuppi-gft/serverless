package com.spuppi.azure;

import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class Function {

	@FunctionName("simpleHttp")
	public HttpResponseMessage run(
			@HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) 
			HttpRequestMessage<Optional<String>> request, final ExecutionContext context) {

		context.getLogger().info("Java HTTP trigger processed a request.");

		Map<String, String> map = null;

		try {

			ObjectMapper objectMapper = new ObjectMapper();

			if(!request.getBody().isPresent()) {
				return request.createResponseBuilder(HttpStatus.BAD_REQUEST).
						body("Please pass a name on the query string or in the request body").
						header("X-Powered-By", "Azure Functions").
						header("Content-Type", "application/json").
						build();
			}

			map = objectMapper.readValue(String.valueOf(request.getBody().get()), new TypeReference<Map<String,String>>(){});

			if (map.get("name") == null) {
				return request.createResponseBuilder(HttpStatus.BAD_REQUEST).
						body("Please pass a name on the query string or in the request body").
						header("X-Powered-By", "Azure Functions").
						header("Content-Type", "application/json").
						build();
			} else {
				return request.createResponseBuilder(HttpStatus.OK).
						body("Hello, " + map.get("name")).
						header("X-Powered-By", "Azure Functions").
						header("Content-Type", "application/json").
						build();
			}

		} catch (JsonMappingException e) {
			return request.createResponseBuilder(HttpStatus.BAD_REQUEST).
					body(e.getMessage()).
					header("X-Powered-By", "Azure Functions").
					header("Content-Type", "application/json").
					build();
		} catch (JsonProcessingException e) {
			return request.createResponseBuilder(HttpStatus.BAD_REQUEST).
					body(e.getMessage()).
					header("X-Powered-By", "Azure Functions").
					header("Content-Type", "application/json").
					build();
		}
	}
}
