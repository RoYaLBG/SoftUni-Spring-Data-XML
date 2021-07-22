package com.example.objectmapping.models.dto;

import java.util.List;

public class UserCollectionDto {

    private int userCount;

    private List<UserWithSoldProductDto> users;

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public List<UserWithSoldProductDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithSoldProductDto> users) {
        this.users = users;
    }
}
