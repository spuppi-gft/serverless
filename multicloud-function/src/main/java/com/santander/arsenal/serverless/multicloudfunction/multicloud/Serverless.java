package com.santander.arsenal.serverless.multicloudfunction.multicloud;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;

import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.ArsenalHttpMessage;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.HttpStatus;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.annotation.ArsenalFunction;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.annotation.ArsenalFunctionController;

public class Serverless {

	//	private static final String KEY_FUNCTION = "arsenal-function";
	//	private static final String PACKAGE = "com.santander.arsenal.serverless";

	public ArsenalHttpMessage ArsenalFunctionScan(ArsenalHttpMessage request, Object context) {

		Object response = null;

		Reflections ref = new Reflections("com.santander.arsenal.serverless");

		try {

			outerloop:
			for (Class<?> cl : ref.getTypesAnnotatedWith(ArsenalFunctionController.class)) {
				for(Method m : cl.getDeclaredMethods()) {
					if(m.isAnnotationPresent((Class<? extends Annotation>) ArsenalFunction.class)){
						//Se nao tiver especificado a @ArsenalFunction executar a function apontado como default
						if(!request.getHeaders().containsKey("arsenal-function") && 
								m.getAnnotation(ArsenalFunction.class).functionDefault()){
							response = m.invoke(cl.newInstance(), request, context);
							break outerloop;
						}else {
							//Executar a function apontada no header
							if(StringUtils.equalsIgnoreCase(m.getAnnotation(ArsenalFunction.class).name().toUpperCase(),request.getHeaders().get("arsenal-function")) && 
									Arrays.asList(m.getAnnotation(ArsenalFunction.class).method()).contains(request.getMethod())){
								response = m.invoke(cl.newInstance(), request, context);
								break outerloop;
							}
						}			

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
		
		if(response == null) {
			response = new ArsenalHttpMessage.Builder(HttpStatus.NOT_FOUND)
					.header("ArsenalFunction","Not Found")
					.header("Version","1.0.0")
					.body(request.getBody().concat("Nao encontrado"))
					.build();
		}

		return (ArsenalHttpMessage) response;

	}

}
