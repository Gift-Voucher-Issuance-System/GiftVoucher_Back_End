package com.seol.giftvoucher_back_end.app.controller.employee.request;

public record EmployeeCreateRequest(
        String name, String position, String department
) {
}
