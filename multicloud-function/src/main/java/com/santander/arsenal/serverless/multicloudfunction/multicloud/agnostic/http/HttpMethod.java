package com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http;

import java.util.Locale;

public enum HttpMethod {

	GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE;

	public static HttpMethod value(String value) {
		return HttpMethod.valueOf(value.toUpperCase(Locale.ROOT));
	}
	
}
