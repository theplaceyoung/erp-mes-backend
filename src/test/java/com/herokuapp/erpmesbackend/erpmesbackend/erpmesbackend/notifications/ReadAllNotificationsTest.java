package com.herokuapp.erpmesbackend.erpmesbackend.erpmesbackend.notifications;

import com.herokuapp.erpmesbackend.erpmesbackend.erpmesbackend.FillBaseTemplate;
import com.herokuapp.erpmesbackend.erpmesbackend.notifications.Notification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReadAllNotificationsTest extends FillBaseTemplate {

    private List<Notification> notifications;

    @Before
    public void init() {
        setupToken();
        addEmployeeRequests(true);
        addOneOrderRequest(true);
        notifications = addNotificationRequests(true);
    }

    @Test
    public void checkIfResponseContainsAllNotifications() {
        ResponseEntity<Notification[]> forEntity = restTemplate.exchange("/notifications", HttpMethod.GET,
                new HttpEntity<>(null, requestHeaders), Notification[].class);
        assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Notification> fetchedNotifications = Arrays.asList(forEntity.getBody());
        for (Notification notification : fetchedNotifications) {
            assertTrue(notifications.stream().anyMatch(n -> n.checkIfDataEquals(notification)));
        }
    }
}