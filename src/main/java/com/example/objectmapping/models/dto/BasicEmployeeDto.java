package com.example.objectmapping.models.dto;

import com.google.gson.annotations.Expose;

public abstract class BasicEmployeeDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
