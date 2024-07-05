
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
First, create a Maven Java project and add the enhanced client dependency in the `pom.xml` file.

```xml
<dependencies>
    <dependency>
        <groupId>software.amazon.awssdk</groupId>
        <artifactId>dynamodb-enhanced</artifactId>
        <version>2.25.60</version>
    </dependency>
</dependencies>
