
# Amazon Enhanced Client

## Overview
First of all, we should know why we are using DynamoDb Clients. Prior to that, what is client actually.

### Client
Here the client is referred to a SDK code which connects to the Cloud SaaS product that we use. So it is clear that we are going to connect our code with AWS DynamoDB.

### What is the problem with DynamoDBMapper?
While there are no major issues, it’s important to understand why we should use the DynamoDB Enhanced Client instead of DynamoDB Mapper.

- **Fluent API**: The Enhanced Client uses a more fluent and expressive API, which is easier to use and more intuitive than DynamoDBMapper. This improves readability and reduces the chance of errors.
- **Builder Pattern**: Many operations use the builder pattern, which allows for more flexible and readable code.
- **Consistency**: The Enhanced Client is designed to be consistent with the AWS SDK for Java v2, offering a more seamless integration and uniform experience across the SDK.
- **Asynchronous Programming**: The Enhanced Client supports asynchronous programming natively, which is a significant advantage for applications requiring non-blocking operations.
- **Efficient Serialization**: The Enhanced Client provides more efficient serialization and deserialization mechanisms, reducing overhead and improving performance.
- **Optimized Resource Management**: It is designed to optimize resource management and reduce latency, which is critical for high-throughput applications.
- **Advanced Data Modeling**: Enhanced Client supports more advanced data modeling capabilities, such as more precise control over attribute conversion and type safety.
- **Detailed Exceptions**: The Enhanced Client provides more detailed and informative exceptions, making it easier to diagnose and handle errors.
- **Error Handling Strategies**: It supports more robust error handling strategies, improving the resilience of applications.
- **Complex Query Support**: The Enhanced Client supports more complex query and scan operations, including better support for pagination and filtering.
- **Efficient Query Execution**: Optimized query execution and result handling enhance performance for read-heavy workloads.

## Implementation

### Create a Maven Project

## Prerequisites

1. AWS Account
2. AWS CLI configured with appropriate credentials
3. Java 8 or higher
4. Maven

## AWS DynamoDB Setup

1. **Create a DynamoDB Table:**
   - Go to the [AWS DynamoDB Console](https://console.aws.amazon.com/dynamodb).
   - Create a table named `Customer` with `customer_id` as the partition key.

2. **Create AWS IAM User:**
   - Go to the [AWS IAM Console](https://console.aws.amazon.com/iam).
   - Create a new user with `AmazonDynamoDbFullAccess` policy.
   - Save the `accessKeyId` and `secretKeyId`.

## Project Setup

1. **Add Dependencies:**
   -First, create a Maven Java project and add the enhanced client dependency in the `pom.xml` file.

```xml
<dependencies>
    <dependency>
        <groupId>software.amazon.awssdk</groupId>
        <artifactId>dynamodb-enhanced</artifactId>
        <version>2.25.60</version>
    </dependency>
</dependencies>
```

2. **Configuration:**
   - Create a package `org.example.configuration` and add the following `Configuration.java` class:
     ```java
     package org.example.configuration;

     import software.amazon.awssdk.auth.credentials.AwsCredentials;
     import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
     import software.amazon.awssdk.regions.Region;
     import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
     import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
     import java.util.concurrent.atomic.AtomicReference;

     public class Configuration {
         private static final AtomicReference<DynamoDbClient> dynamoDbClient = new AtomicReference<>();
         private static final AtomicReference<DynamoDbEnhancedClient> dynamoDbEnhancedClient = new AtomicReference<>();

         private Configuration() {
         }

         private static DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
             if (dynamoDbEnhancedClient.get() == null) {
                 dynamoDbEnhancedClient.compareAndSet(null, DynamoDbEnhancedClient.builder().dynamoDbClient(getDynamoDbClient()).build());
             }
             return dynamoDbEnhancedClient.get();
         }

         private static DynamoDbClient getDynamoDbClient() {
             if (dynamoDbClient.get() == null) {
                 dynamoDbClient.compareAndSet(null, DynamoDbClient.builder()
                         .credentialsProvider(new AwsCredentialsProvider() {
                             @Override
                             public AwsCredentials resolveCredentials() {
                                 return new AwsCredentials() {
                                     @Override
                                     public String accessKeyId() {
                                         return "YOUR_ACCESS_KEY_ID";
                                     }

                                     @Override
                                     public String secretAccessKey() {
                                         return "YOUR_SECRET_KEY_ID";
                                     }
                                 };
                             }
                         })
                         .region(Region.of("us-east-1"))
                         .build());
             }
             return dynamoDbClient.get();
         }

         public static DynamoDbEnhancedClient initializeDynamoDbEnhancedClient() {
             return getDynamoDbEnhancedClient();
         }
     }
     ```

3. **Model:**
   - Create a package `org.example.model` and add the following `Customer.java` class:
     ```java
     package org.example.model;

     import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
     import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
     import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;

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
     ```

   - Create a `TableSchema` for the `Customer` bean:
     ```java
     public static final TableSchema<Customer> CUSTOMER_TABLE_SCHEMA = TableSchema.fromBean(Customer.class);
     ```

## Security Note
- Ensure your AWS `accessKeyId` and `secretKeyId` are not exposed publicly. Consider using AWS IAM roles or environment variables for better security.

## Further Reading
- [AWS SDK for Java Developer Guide](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/home.html)

## Conclusion
You are now ready to use DynamoDB in your Spring Boot application. For further customization and usage, refer to the AWS SDK documentation.

