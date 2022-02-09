package com.example.simplewebapp;

import com.example.simplewebapp.domain.Car;
import com.example.simplewebapp.domain.Health;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimplewebappApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

	@Test
	void contextLoads() {
	}

    @Test
    void healthCheck() {
	    ResponseEntity<Health> response = testRestTemplate.getForEntity("/health", Health.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getStatus(),"OK");
    }

    @Test
    void testSimplePark() {
        ResponseEntity<String> response = testRestTemplate.postForEntity("/lot/cars", new Car("1234","Joe"), String.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(),"Parked!");
    }

    @Test
    void testGettingCarByLicenseNo() {
        testRestTemplate.postForEntity("/lot/cars", new Car("1234","Joe"), String.class);
        ResponseEntity<String> response = testRestTemplate.getForEntity("/lot/car/"+"1234", String.class );

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals("Parked by Joe",response.getBody());
    }
}
