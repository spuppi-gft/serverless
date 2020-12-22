package com.santander.arsenal.serverless.multicloudfunction.multicloud;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;

import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.ArsenalFunction;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.ArsenalFunctionController;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.ArsenalHttpMessage;

public class Serverless {

	public ArsenalHttpMessage ArsenalFunctionScan(ArsenalHttpMessage request, Object context) {

		Object response = null;

		Reflections ref = new Reflections("com.santander.arsenal.serverless");
		for (Class<?> cl : ref.getTypesAnnotatedWith(ArsenalFunctionController.class)) {
			for(Method m : cl.getDeclaredMethods()) {
				if(m.isAnnotationPresent((Class<? extends Annotation>) ArsenalFunction.class)){
					try {
						//Se nao tiver especificado a @ArsenalFunction executar a function apontado como default
						if(!request.getHeaders().containsKey("Arsenal-Function") && 
								m.getAnnotation(ArsenalFunction.class).functionDefault()){
							response = m.invoke(cl.newInstance(), request, context);
							return (ArsenalHttpMessage) response;
						}
						//Executar a function apontada no header
						if(StringUtils.equalsIgnoreCase(m.getAnnotation(ArsenalFunction.class).name(),request.getHeaders().get("Arsenal-Function"))){
							response = m.invoke(cl.newInstance(), request, context);
							return (ArsenalHttpMessage) response;
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
				}
			}
		}

		return (ArsenalHttpMessage) response;	
	}

}
