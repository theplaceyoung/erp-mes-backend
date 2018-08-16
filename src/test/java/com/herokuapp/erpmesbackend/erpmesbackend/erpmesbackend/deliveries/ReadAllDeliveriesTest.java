package com.herokuapp.erpmesbackend.erpmesbackend.erpmesbackend.deliveries;

import com.herokuapp.erpmesbackend.erpmesbackend.erpmesbackend.FillBaseTemplate;
import com.herokuapp.erpmesbackend.erpmesbackend.shop.deliveries.Delivery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReadAllDeliveriesTest extends FillBaseTemplate {

    @Before
    public void init() {
        addManyItemRequests(true);
        addManyDeliveryRequests(true);
    }

    @Test
    public void checkIfResponseContainsAllDeliveries() {
        ResponseEntity<Delivery[]> forEntity = restTemplate.getForEntity("/deliveries", Delivery[].class);

        assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Delivery> deliveries = Arrays.asList(forEntity.getBody());
        assertThat(deliveries.size()).isEqualTo(3);
    }
}