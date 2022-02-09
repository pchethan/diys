package com.example.simplewebapp;

import com.example.simplewebapp.domain.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParkingLotController.class)
@ExtendWith(SpringExtension.class)
public class ParkingLotControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingLotService parkingLotService;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset

            .forName("utf8"));


    @Test
	void contextLoads() {
	}

    @Test
    void testGettingCarByLicenseNo() throws Exception {
	    given(parkingLotService.parkCar(any(Car.class))).willReturn("Parked!");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(new Car("a","b"));

        mockMvc.perform(MockMvcRequestBuilders.post("/lot/cars")
                                              .contentType(APPLICATION_JSON_UTF8)
                                              .content(requestJson))
               .andExpect(status().isOk())
               .andExpect(content().string("Parked!"));
    }

}
