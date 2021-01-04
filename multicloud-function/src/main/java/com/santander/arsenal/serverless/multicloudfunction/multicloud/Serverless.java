package com.santander.arsenal.serverless.multicloudfunction.multicloud;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.reflections.Reflections;

import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.ArsenalHttpMessage;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.HttpStatus;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.annotation.ArsenalFunction;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.annotation.ArsenalFunctionController;

public class Serverless {

	public Object ArsenalFunctionScan(Object request, Object context) {

		Object response = new ArsenalHttpMessage.Builder(HttpStatus.NOT_FOUND)
				.header("ArsenalFunction","Not Found")
				.header("Version","1.0.0")
				.body("Nao encontrado")
				.build();

		Reflections ref = new Reflections("com.santander.arsenal.serverless");

		try {

			outerloop:
			for (Class<?> cl : ref.getTypesAnnotatedWith(ArsenalFunctionController.class)) {
				for(Method m : cl.getDeclaredMethods()) {
					if(m.isAnnotationPresent((Class<? extends Annotation>) ArsenalFunction.class)){
						response = m.invoke(cl.newInstance(), request, context);
						break outerloop;
					}
				}
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;

	}

}
