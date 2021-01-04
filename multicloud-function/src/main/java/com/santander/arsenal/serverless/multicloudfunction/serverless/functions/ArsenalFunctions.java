package com.santander.arsenal.serverless.multicloudfunction.serverless.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.annotation.ArsenalFunction;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.agnostic.http.annotation.ArsenalFunctionController;

@ArsenalFunctionController
public class ArsenalFunctions {

	@ArsenalFunction
	public Object lerConteudo (InputStream content, Object context) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		String contentStr = "";
		
		String line = null;
        try {
			while ((line = reader.readLine()) != null) {
				contentStr += line;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return contentStr;
	}
}
