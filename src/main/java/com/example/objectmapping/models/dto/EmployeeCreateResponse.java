package com.example.objectmapping.models.dto;

import com.google.gson.annotations.Expose;

public class EmployeeCreateResponse extends EmployeeCreateRequest {

    @Expose
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
