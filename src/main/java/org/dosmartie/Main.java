package org.dosmartie;

import org.dosmartie.configuration.Configuration;
import org.dosmartie.model.Customer;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

public class Main {
    public static final TableSchema<Customer> CUSTOMER_TABLE_SCHEMA = TableSchema
            .fromBean(Customer.class);

    public static void main(String[] args) {
        DynamoDbEnhancedClient dynamoDbEnhancedClient = Configuration.initializeDynamoDbEnhancedClient();
        // 1st param -> table name - Customer
        // 2nd param -> TableSchema of the table
        DynamoDbTable<Customer> dynamoDbTable = dynamoDbEnhancedClient.table("Customer", CUSTOMER_TABLE_SCHEMA);

        // Creating dummy object
        Customer customer = new Customer();
        customer.setCustomerId("12");
        customer.setDob("10.10.2001");
        customer.setEmail("dynamo@gmail.com");
        customer.setMobileNumber("986542120");

        // Inserting data to DynamoDb Customer table.
        dynamoDbTable.putItem(customer);

        // Get data from DynamoDb Customer table.
        Key getKey = Key.builder().partitionValue("12").build();
        dynamoDbTable.getItem(getKey);

        // Delete an item in DynamoDb Customer table.
        Key deleteKey = Key.builder().partitionValue("12").build();
        dynamoDbTable.deleteItem(deleteKey);

        // Update an item in DynamoDb Customer table.
        customer.setMobileNumber("12392834231");
        dynamoDbTable.putItem(customer);
    }
}