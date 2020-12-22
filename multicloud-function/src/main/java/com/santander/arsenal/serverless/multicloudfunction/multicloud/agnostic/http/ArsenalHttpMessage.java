package com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http;

import java.util.HashMap;
import java.util.Map;

public class ArsenalHttpMessage {

	private HttpMethod method;
	private HttpStatus status;
	private Map<String, String> headers;
	private String body;
	
	public ArsenalHttpMessage(Builder builder) {
		this.method = builder.method;
		this.status = builder.status;
		this.headers = builder.headers;
		this.body = builder.body;
	}
			
	public HttpMethod getMethod() {
		return method;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public String getBody() {
		return body;
	}
	
	public static class Builder{
		
		private HttpMethod method;
		private HttpStatus status;
		private Map<String, String> headers = new HashMap<String, String>();
		private String body;
		
		public Builder(HttpMethod httpMethod) {
			this.method = httpMethod;
		}
		
		public Builder(HttpStatus status) {
			this.status = status;
		}
		
		public Builder status(HttpStatus status) {
			this.status = status;
			return this;
		}
		
		public Builder header(String key, String value) {
			this.headers.put(key, value);
			return this;
		}
		
		public Builder headers(Map<String, String> headers) {
			this.headers = headers;
			return this;
		}
		
		public Builder body(String body) {
			this.body = body;
			return this;
		}
		
		public ArsenalHttpMessage build() {
			return new ArsenalHttpMessage(this);
		}
	}
}
