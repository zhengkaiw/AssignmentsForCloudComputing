package com.amazonaws.lambda.demo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;

public class LambdaFunctionHandler implements RequestHandler<DynamodbEvent, Integer> {

	private AmazonSNS SNS_CLIENT = AmazonSNSClientBuilder.standard()
			.withRegion(Regions.US_WEST_2).build();
	private static String RESTURANTS_SNS_TOPIC = "***:test";
    
	@Override
	public Integer handleRequest(DynamodbEvent input, Context context) {
	  
		// Read DDB Records
		for (DynamodbStreamRecord record : input.getRecords()) {

            if (record == null) {
                continue;
            }
            String professor = record.getDynamodb().getNewImage().get("Professor").getS();	
            String content = record.getDynamodb().getNewImage().get("Content").getS();
            String time = record.getDynamodb().getNewImage().get("Date").getS();
            if (record.getEventName().equals("INSERT")) {
            	
                String output = "You have a new announcement from Professor " + professor;
                String outputBody = output + ":\n" + content + "\n\nTime:" + time;
                sendEmailNotification(output, outputBody);
            } else if (record.getEventName().equals("REMOVE")) {
                String output = "Message removed";
                String outputBody = output;
                sendEmailNotification(output, outputBody);
            } else {
                String output = "Professor " + professor + "has changed an announcement";
                String outputBody = output + ":\n" + content + "\n\nTime:" + time;
                sendEmailNotification(output, outputBody);
            }            
        }
		return null;
  }

  	private void sendEmailNotification(final String subject, final String message) {  		
  		// Message object 
  		PublishRequest publishRequest 
  				= new PublishRequest(RESTURANTS_SNS_TOPIC, message);
  		// Call Client.publishMessage
  		SNS_CLIENT.publish(publishRequest);
  	}

}