package com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.HttpMethod;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArsenalFunction {

	HttpMethod[] method() default {};
}
