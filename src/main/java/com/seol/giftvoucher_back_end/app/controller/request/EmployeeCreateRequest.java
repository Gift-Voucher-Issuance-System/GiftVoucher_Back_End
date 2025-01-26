package com.seol.giftvoucher_back_end.app.controller.request;

public record EmployeeCreateRequest(
        String name, String position, String department
) {
}
