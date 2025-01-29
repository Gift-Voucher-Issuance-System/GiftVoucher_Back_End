package com.seol.giftvoucher_back_end.domain.employee;

import com.seol.giftvoucher_back_end.app.controller.employee.response.EmployeeResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @DisplayName("회원 생성 후 조회 가능")
    @Test
    public void test(){
        //Given
        String name = "테스트네임1";
        String position = "사원";
        String department = "개발팀";

        //When
        Long no = employeeService.create(name, position, department);
        EmployeeResponse response = employeeService.get(no);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.no()).isEqualTo(no);
        assertThat(response.name()).isEqualTo(name);
        assertThat(response.position()).isEqualTo(position);
        assertThat(response.department()).isEqualTo(department);

        //System.out.println(response);
    }

}