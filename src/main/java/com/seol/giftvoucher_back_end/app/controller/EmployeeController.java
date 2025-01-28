package com.seol.giftvoucher_back_end.app.controller;

import com.seol.giftvoucher_back_end.app.controller.request.EmployeeCreateRequest;
import com.seol.giftvoucher_back_end.app.controller.response.EmployeeResponse;
import com.seol.giftvoucher_back_end.domain.employee.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

   private final EmployeeService employeeService;

   public EmployeeController(EmployeeService employeeService){
       this.employeeService = employeeService;
   }

    //회원 생성
    @PostMapping("/api/v1/employee")
    public Long create(@RequestBody final EmployeeCreateRequest request){
        return employeeService.create(request.name(), request.position(), request.department());
    }

    //회원 조회
    @GetMapping("/api/v1/employee/{no}")
    public EmployeeResponse get(@PathVariable(value="no") final Long no){
        return employeeService.get(no);
    }
}
