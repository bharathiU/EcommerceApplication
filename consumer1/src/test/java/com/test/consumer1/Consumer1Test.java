package com.test.consumer1;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.consumer1.services.connector.Provider1Connector;
import com.test.consumer1.services1.ProductConsumerService;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyTestConfig.class)
public class Consumer1Test {
     @Rule
        public PactProviderRuleMk2 provider = new PactProviderRuleMk2("EcommereceProviderPact","localhost", 8066, this);
    @Autowired
    private ProductConsumerService consumerService;
    @Pact(consumer = "myconsumerpact") // will default to the provider name from
                                    // mockProvider in Rule
    public RequestResponsePact defineExpectation(PactDslWithProvider builder) {
        return builder.uponReceiving("get Product list by Apple")
                .path("/app/manufacturers/name/Apple").method("GET").willRespondWith().status(200)
                .body("{\n" +
                        "    \"manufacturerVOs\": [\n" +
                        "        {\n" +
                        "            \"manufacturerId\": \"5678\",\n" +
                        "            \"manufacturerName\": \"Apple\",\n" +
                        "            \"manufacturerAddress\": \"Bangalore\",\n" +
                        "            \"productsList\": [\n" +
                        "                {\n" +
                        "                    \"productId\": \"1007\",\n" +
                        "                    \"productName\": \"Apple Mack book Pro\",\n" +
                        "                    \"productType\": \"Laptop\",\n" +
                        "                    \"price\": 90000.0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"productId\": \"1008\",\n" +
                        "                    \"productName\": \"mack book Air\",\n" +
                        "                    \"productType\": \"Laptop\",\n" +
                        "                    \"price\": 75000.0\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}").toPact();
    }
    
    @Pact(consumer = "myconsumerpact") // will default to the provider name from mockProvider in Rule
    public RequestResponsePact defineExpectation1(PactDslWithProvider builder) {
        return builder
                .uponReceiving("get product list by HP")
                .path("/app/manufacturers/name/HP")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("{\n" + 
                        "    \"manufacturerVOs\": [\n" + 
                        "        {\n" + 
                        "            \"manufacturerId\": \"5679\",\n" + 
                        "            \"manufacturerName\": \"Hp\",\n" + 
                        "            \"manufacturerAddress\": \"Mumbai\",\n" + 
                        "            \"productsList\": [\n" + 
                        "                {\n" + 
                        "                    \"productId\": \"1009\",\n" + 
                        "                    \"productName\": \"pro book\",\n" + 
                        "                    \"productType\": \"Laptop\",\n" + 
                        "                    \"price\": 83000.0\n" + 
                        "                }\n" + 
                        "            ]\n" + 
                        "        }\n" + 
                        "    ]\n" + 
                        "}")
                .toPact();
    }
    @Pact(consumer = "myconsumerpact") // will default to the provider name from mockProvider in Rule
    public RequestResponsePact defineExpectationWithState(PactDslWithProvider builder) {
        return builder
                .given("DELL")
                .uponReceiving("get product list by DELL")
                .path("/app/manufacturers/name/DELL")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("{\n" + 
                        "    \"manufacturerVOs\": [\n" + 
                        "        {\n" + 
                        "            \"manufacturerId\": \"5680\",\n" + 
                        "            \"manufacturerName\": \"DELL\",\n" + 
                        "            \"manufacturerAddress\": \"Chennai\",\n" + 
                        "            \"productsList\": [\n" + 
                        "                {\n" + 
                        "                    \"productId\": \"1010\",\n" + 
                        "                    \"productName\": \"DELL Inspiron\",\n" + 
                        "                    \"productType\": \"Laptop\",\n" + 
                        "                    \"price\": 82000.0\n" + 
                        "                }\n" + 
                        "            ]\n" + 
                        "        }\n" + 
                        "    ]\n" + 
                        "}")
                .toPact();
    }
    
    @Pact(consumer = "myconsumerpact") // will default to the provider name from mockProvider in Rule
    public RequestResponsePact defineExpectation2(PactDslWithProvider builder) {
        return builder.uponReceiving("get product list by productid 1008").path("/app/products/1008").method("GET")
                .willRespondWith().status(200)
                .body("{\n" + " \"productsVOs\": [\n" + " {\n" + " \"productId\": \"1008\",\n"
                        + "   \"productName\": \"mack book Air\",\n" + "  \"productType\": \"Laptop\",\n"
                        + "    \"price\":  75000.0 ,\n" + "     \"manufacturerId\": \"5678\",\n"
                        + "   \"manufacturerName\": \"Apple\",\n" + "  \"manufacturerAddress\": \"Bangalore\"\n"
                        + "  }\n" + "  ]\n" + "}")
                .toPact();

 

    }
    @Test 
    @PactVerification(fragment = "defineExpectation")
    public void test() throws IOException {
         Assert.assertTrue(consumerService.getProductList("Apple").isPresent());
    }
     @Test
        @PactVerification(fragment = "defineExpectation1")
        public void runTest1() throws IOException {
            Assert.assertTrue(consumerService.getProductList("HP").isPresent());
        }
     @Test
        @PactVerification(fragment = "defineExpectationWithState")
        public void runTestWithState() throws IOException {
            Assert.assertTrue(consumerService.getProductList("DELL").isPresent());
        }
     @Test
     @PactVerification(fragment = "defineExpectation2")
     public void runTest2() throws IOException {
         Assert.assertTrue(consumerService.getProductListById("1008").isPresent());
     }
}

@TestConfiguration
class MyTestConfig {
    @Bean
    public ProductConsumerService getStudentConsumerService() {
        return new ProductConsumerService();
    }
    @Bean
    public Provider1Connector getProviderConnector(ObjectMapper objectMapper, RestTemplateBuilder restTemplateBuilder) {
        return new Provider1Connector("http://localhost:8066/app", restTemplateBuilder, objectMapper);
    }
    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
    @Bean
    public RestTemplateBuilder getRestTemplateBuilder() {
        return new RestTemplateBuilder();
    }

}