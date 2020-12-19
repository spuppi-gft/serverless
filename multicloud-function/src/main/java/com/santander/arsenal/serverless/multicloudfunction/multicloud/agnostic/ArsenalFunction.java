package com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArsenalFunction {

	String name(); 
	
}
