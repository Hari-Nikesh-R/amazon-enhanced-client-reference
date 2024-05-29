package org.dosmartie.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Customer {
    private String customerId;
    private String name;
    private String dob;
    private String email;
    private String mobileNumber;

    public Customer() {
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute(value = "customer_id")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Customer(String customerId, String name, String dob, String email, String mobileNumber) {
        this.customerId = customerId;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }
}
