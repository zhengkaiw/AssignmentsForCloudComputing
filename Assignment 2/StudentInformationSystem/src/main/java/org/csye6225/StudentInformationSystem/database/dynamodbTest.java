package org.csye6225.StudentInformationSystem.database;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.util.TableUtils;

public class dynamodbTest {
		static AmazonDynamoDB dynamoDb;
		
		/**
		 * Method creates a DynamoDb 
		 * @exception Exception
 		 * @throws Exception
		 */
		private static void init() throws Exception {
			ProfileCredentialsProvider credentialsProvider = 
					new ProfileCredentialsProvider();
			
			credentialsProvider.getCredentials();
			
			dynamoDb = AmazonDynamoDBClientBuilder
					.standard()
					.withCredentials(credentialsProvider)
					.withRegion("us-west-2")
					.build();
		}
		
		private static void createTableAnnouncement() throws Exception{
			String tableName = "Announcement";
 			
			CreateTableRequest createTableRequest = new CreateTableRequest()
					.withTableName(tableName)
					.withKeySchema(new KeySchemaElement()
							.withAttributeName("Id")
							.withKeyType(KeyType.HASH)
							)
					.withAttributeDefinitions(
							new AttributeDefinition()
							.withAttributeName("Id")
							.withAttributeType(ScalarAttributeType.S)
							)
					.withProvisionedThroughput(
							new ProvisionedThroughput()
							.withReadCapacityUnits(3L)
							.withWriteCapacityUnits(3L)
							);
			TableUtils.createTableIfNotExists(dynamoDb, createTableRequest);
			TableUtils.waitUntilActive(dynamoDb, tableName);
 			
			Map<String, AttributeValue> item1 = 
					new HashMap<>();
			item1.put("Id", new AttributeValue().withS("001"));
			item1.put("Professor", new AttributeValue().withS("Brett"));
			item1.put("Course", new AttributeValue().withS("INFO6250"));
			item1.put("Date", new AttributeValue().withS("Apr 2 2018"));
			item1.put("Content", new AttributeValue().withS("No exam"));
			
			PutItemRequest putItemRequest1 = new PutItemRequest(tableName, item1);
			System.out.println("PutItemRequest : " + putItemRequest1);
			PutItemResult putItemResult1 = dynamoDb.putItem(putItemRequest1);
			System.out.println("PutItemResult: " + putItemResult1);
			
			Map<String, AttributeValue> item2 = 
					new HashMap<>();
			item2.put("Id", new AttributeValue().withS("002"));
			item2.put("Professor", new AttributeValue().withS("Brett"));
			item2.put("Course", new AttributeValue().withS("INFO6250"));
			item2.put("Date", new AttributeValue().withS("Apr 5 2018"));
			item2.put("Content", new AttributeValue().withS("Exam tomorrow"));
			
			PutItemRequest putItemRequest2 = new PutItemRequest(tableName, item2);
			System.out.println("PutItemRequest : " + putItemRequest2);
			PutItemResult putItemResult2 = dynamoDb.putItem(putItemRequest2);
			System.out.println("PutItemResult: " + putItemResult2);
		}
		
		private static void createTableCourse() throws Exception{
			String tableName = "Course";
 			
			CreateTableRequest createTableRequest = new CreateTableRequest()
					.withTableName(tableName)
					.withKeySchema(new KeySchemaElement()
							.withAttributeName("Id")
							.withKeyType(KeyType.HASH)
							)
					.withAttributeDefinitions(
							new AttributeDefinition()
							.withAttributeName("Id")
							.withAttributeType(ScalarAttributeType.S)
							)
					.withProvisionedThroughput(
							new ProvisionedThroughput()
							.withReadCapacityUnits(3L)
							.withWriteCapacityUnits(3L)
							);
			TableUtils.createTableIfNotExists(dynamoDb, createTableRequest);
			TableUtils.waitUntilActive(dynamoDb, tableName);
 			
			Map<String, AttributeValue> item = 
					new HashMap<>();
			item.put("Id", new AttributeValue().withS("INFO6250"));
			item.put("Name", new AttributeValue().withS("Web Tools"));
			item.put("Program", new AttributeValue().withS("INFO6250"));
			item.put("Professor", new AttributeValue().withS("Brett"));
			item.put("Students", new AttributeValue().withSS("Kevin", "Tom"));
			item.put("Announcements", new AttributeValue().withSS("001", "002"));
			
			PutItemRequest putItemRequest = new PutItemRequest(tableName, item);
			System.out.println("PutItemRequest : " + putItemRequest);
			PutItemResult putItemResult = dynamoDb.putItem(putItemRequest);
			System.out.println("PutItemResult: " + putItemResult);
		}
		
		private static void createTableStudent() throws Exception{
			String tableName = "Student";
 			
			CreateTableRequest createTableRequest = new CreateTableRequest()
					.withTableName(tableName)
					.withKeySchema(new KeySchemaElement()
							.withAttributeName("Id")
							.withKeyType(KeyType.HASH)
							)
					.withAttributeDefinitions(
							new AttributeDefinition()
							.withAttributeName("Id")
							.withAttributeType(ScalarAttributeType.S)
							)
					.withProvisionedThroughput(
							new ProvisionedThroughput()
							.withReadCapacityUnits(3L)
							.withWriteCapacityUnits(3L)
							);
			TableUtils.createTableIfNotExists(dynamoDb, createTableRequest);
			TableUtils.waitUntilActive(dynamoDb, tableName);
 			
			Map<String, AttributeValue> item1 = 
					new HashMap<>();
			item1.put("Id", new AttributeValue().withS("001"));
			item1.put("Name", new AttributeValue().withS("Kevin"));
			item1.put("Image", new AttributeValue().withS("img1"));
			item1.put("Email", new AttributeValue().withS("***"));
			item1.put("Courses", new AttributeValue().withSS("INFO6250", "INFO6550"));
			item1.put("Program", new AttributeValue().withS("IS"));
			
			PutItemRequest putItemRequest1 = new PutItemRequest(tableName, item1);
			System.out.println("PutItemRequest : " + putItemRequest1);
			PutItemResult putItemResult1 = dynamoDb.putItem(putItemRequest1);
			System.out.println("PutItemResult: " + putItemResult1);
			
			Map<String, AttributeValue> item2 = 
					new HashMap<>();
			item2.put("Id", new AttributeValue().withS("002"));
			item2.put("Name", new AttributeValue().withS("Tom"));
			item2.put("Image", new AttributeValue().withS("img2"));
			item2.put("Email", new AttributeValue().withS("***"));
			item2.put("Courses", new AttributeValue().withSS("INFO6250", "INFO6500"));
			item2.put("Program", new AttributeValue().withS("IS"));
			
			PutItemRequest putItemRequest2 = new PutItemRequest(tableName, item2);
			System.out.println("PutItemRequest : " + putItemRequest2);
			PutItemResult putItemResult2 = dynamoDb.putItem(putItemRequest2);
			System.out.println("PutItemResult: " + putItemResult2);
		}
		
		private static void createTableProfessor() throws Exception{
			String tableName = "Professor";
 			
			CreateTableRequest createTableRequest = new CreateTableRequest()
					.withTableName(tableName)
					.withKeySchema(new KeySchemaElement()
							.withAttributeName("Id")
							.withKeyType(KeyType.HASH)
							)
					.withAttributeDefinitions(
							new AttributeDefinition()
							.withAttributeName("Id")
							.withAttributeType(ScalarAttributeType.S)
							)
					.withProvisionedThroughput(
							new ProvisionedThroughput()
							.withReadCapacityUnits(3L)
							.withWriteCapacityUnits(3L)
							);
			TableUtils.createTableIfNotExists(dynamoDb, createTableRequest);
			TableUtils.waitUntilActive(dynamoDb, tableName);
 			
			Map<String, AttributeValue> item = 
					new HashMap<>();
			item.put("Id", new AttributeValue().withS("001"));
			item.put("Name", new AttributeValue().withS("Brett"));
			item.put("Course", new AttributeValue().withS("INFO6250"));
			
			PutItemRequest putItemRequest = new PutItemRequest(tableName, item);
			System.out.println("PutItemRequest : " + putItemRequest);
			PutItemResult putItemResult = dynamoDb.putItem(putItemRequest);
			System.out.println("PutItemResult: " + putItemResult);
		}
		
		public static void main(String[] args) throws Exception {
			init();
			createTableAnnouncement();
			createTableCourse();
			createTableProfessor();
			createTableStudent();
		}
}
