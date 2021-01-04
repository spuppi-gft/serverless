package com.santander.arsenal.serverless.multicloudfunction.multicloud.aws;

import java.io.InputStream;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.santander.arsenal.serverless.multicloudfunction.multicloud.Serverless;

public class StorageFunctionAws implements RequestHandler<S3Event, String> {
		
	@Override
	public String handleRequest(S3Event s3Event, Context context) {
		
		S3EventNotificationRecord record = s3Event.getRecords().get(0);
		
		String srcBucket = record.getS3().getBucket().getName();
		String srcKey = record.getS3().getObject().getUrlDecodedKey();
		
        context.getLogger().log("ARQUIVO DETECTADO: \n" + "Bucket: " + srcBucket +
				"\n Arquivo: " + srcKey + " \n " + System.currentTimeMillis());
		
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();
        S3Object s3Object = s3Client.getObject(new GetObjectRequest(srcBucket, srcKey));
        InputStream objectData = s3Object.getObjectContent();
                
        Serverless s = new Serverless();
		Object response = s.ArsenalFunctionScan(objectData, context);
        		
		return (String) response;
	}
}
