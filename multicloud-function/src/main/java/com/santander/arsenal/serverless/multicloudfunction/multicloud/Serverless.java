package com.santander.arsenal.serverless.multicloudfunction.multicloud;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.ArsenalFunction;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.ArsenalHttpMessage;

public class Serverless {
		
	public ArsenalHttpMessage ArsenalFunctionScan(Object obj, ArsenalHttpMessage request, Object context) {
		
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
		
		return (ArsenalHttpMessage) response;
		
	}

}
