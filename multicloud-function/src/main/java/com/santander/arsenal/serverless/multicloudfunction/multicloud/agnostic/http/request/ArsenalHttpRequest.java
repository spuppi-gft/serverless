package com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.request;

import java.util.Map;

public class ArsenalHttpRequest {

	private String method;
	private Map<String, String> headers;
	private String body;
	
	public ArsenalHttpRequest(Builder builder) {
		this.method = builder.method;
		this.headers = builder.headers;
		this.body = builder.body;
	}
	
	public String getMethod() {
		return method;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public String getBody() {
		return body;
	}
	
	public static class Builder{
		
		private String method;
		private Map<String, String> headers;
		private String body;
		
		public Builder(String method) {
			this.method = method;
		}
		
		public Builder headers(Map<String, String> headers) {
			this.headers = headers;
			return this;
		}
		
		public Builder body(String body) {
			this.body = body;
			return this;
		}
		
		public ArsenalHttpRequest build() {
			return new ArsenalHttpRequest(this);
		}
	}
}
