package com.santander.arsenal.serverless.multicloudfunction.multicloud;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.ArsenalFunction;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.request.ArsenalHttpRequest;

public class Serverless {
		
	public Object ArsenalFunctionScan(Object obj, ArsenalHttpRequest request, Object context) {
		
		Object response = null;
		
		Class<? extends Object> clazz = obj.getClass();
		for(Method m : clazz.getDeclaredMethods()) {
			if(m.isAnnotationPresent((Class<? extends Annotation>) ArsenalFunction.class)){
				try {
					response = m.invoke(obj, request, context);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return response;
		
	}

}
