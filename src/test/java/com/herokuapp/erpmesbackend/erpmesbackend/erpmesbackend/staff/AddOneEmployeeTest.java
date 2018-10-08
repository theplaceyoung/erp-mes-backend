package com.herokuapp.erpmesbackend.erpmesbackend.erpmesbackend.staff;

import com.herokuapp.erpmesbackend.erpmesbackend.staff.dto.EmployeeDTO;
import com.herokuapp.erpmesbackend.erpmesbackend.erpmesbackend.FillBaseTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddOneEmployeeTest extends FillBaseTemplate {

    @Before
    public void init() {
        setupToken();
        addOneNonAdminRequest(false);
    }

    @Test
    public void checkIfResponseContainsAddedEmployee() {
        nonAdminRequest.setEmail("jsfkhighnfkv@sd.ds");
        ResponseEntity<EmployeeDTO> employeeResponseEntity = restTemplate
                .postForEntity("/employees", new HttpEntity<>(nonAdminRequest, requestHeaders),
                        EmployeeDTO.class);

        assertThat(employeeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        EmployeeDTO body = employeeResponseEntity.getBody();
        assertTrue(body.checkIfDataEquals(new EmployeeDTO(nonAdminRequest.extractUser())));
    }
}