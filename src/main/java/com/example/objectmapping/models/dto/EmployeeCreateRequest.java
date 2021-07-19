package com.example.objectmapping.models.dto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class EmployeeCreateRequest extends BasicEmployeeDto {

    @Expose
    private BigDecimal salary;

    @Expose
    private String address;

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
