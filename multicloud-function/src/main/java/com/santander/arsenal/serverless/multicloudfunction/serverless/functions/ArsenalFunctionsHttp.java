package com.santander.arsenal.serverless.multicloudfunction.serverless.functions;

import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.ArsenalHttpMessage;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.HttpMethod;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.HttpStatus;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.annotation.ArsenalFunction;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.annotation.ArsenalFunctionController;

@ArsenalFunctionController
public class ArsenalFunctionsHttp {

	@ArsenalFunction(name = "CadastrarUsuario", defaultFunction = true, method = {HttpMethod.GET, HttpMethod.POST})
	public ArsenalHttpMessage cadastrarUsuario (ArsenalHttpMessage request, Object context) {

		System.out.println("Esta e a funcao multicloud - CadastrarUsuario");

		System.out.println(request.getMethod());

		System.out.println(request.getHeaders());

		System.out.println(request.getBody());

		return new ArsenalHttpMessage.Builder(request.getMethod())
				.status(HttpStatus.OK)
				.header("ArsenalFunction","CadastrarUsuario")
				.header("Version","1.0.0")
				.body(request.getBody().concat("CadastrarUsuario"))
				.build();
	}

	@ArsenalFunction(name = "AtualizarUsuario", defaultFunction = false, method = {HttpMethod.PUT})
	public ArsenalHttpMessage atualizarUsuario (ArsenalHttpMessage request, Object context) {
		
		System.out.println("Esta e a funcao multicloud - AtualizarUsuario");

		System.out.println(request.getMethod());

		System.out.println(request.getHeaders());

		System.out.println(request.getBody());

		return new ArsenalHttpMessage.Builder(request.getMethod())
				.status(HttpStatus.OK)
				.header("ArsenalFunction","AtualizarUsuario")
				.header("Version","1.0.0")
				.body(request.getBody().concat("AtualizarUsuario"))
				.build();
	}
	
	@ArsenalFunction(name = "CadastrarUsuario", defaultFunction = true, method = {HttpMethod.DELETE})
	public ArsenalHttpMessage deletarUsuario (ArsenalHttpMessage request, Object context) {

		System.out.println("Esta e a funcao multicloud - DeletarUsuario");

		System.out.println(request.getMethod());

		System.out.println(request.getHeaders());

		System.out.println(request.getBody());

		return new ArsenalHttpMessage.Builder(request.getMethod())
				.status(HttpStatus.OK)
				.header("ArsenalFunction","DeletarUsuario")
				.header("Version","1.0.0")
				.body(request.getBody().concat("DeletarUsuario"))
				.build();
	}
}
