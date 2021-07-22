package com.example.objectmapping.models.dto;

public class UserWithSoldProductDto {

    private String firstName;

    private String lastName;

    private int age;

    private SoldProductCollectionDto soldProducts;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SoldProductCollectionDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldProductCollectionDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
