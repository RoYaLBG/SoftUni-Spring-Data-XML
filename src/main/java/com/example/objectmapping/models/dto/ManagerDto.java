package com.example.objectmapping.models.dto;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ManagerDto extends BasicEmployeeDto {

    @Expose
    private List<EmployeeDto> subordinates;

    public List<EmployeeDto> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<EmployeeDto> subordinates) {
        this.subordinates = subordinates;
    }
}
