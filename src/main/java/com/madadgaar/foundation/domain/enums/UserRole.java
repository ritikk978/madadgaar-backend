package com.madadgaar.foundation.domain.enums;

import com.madadgaar.foundation.exceptions.UnivestException;

import java.util.EnumSet;

public enum UserRole {

    USER("USER"),
    ADVISOR("ADVISOR"),
    GROUP_ADMIN("GROUP_ADMIN"),
    GROUP_MEMBER("GROUP_MEMBER");
    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public static UserRole getIfPresent(String role) {
        return EnumSet.allOf(UserRole.class)
                      .stream()
                      .filter(userRole -> userRole.getRole().equals(role))
                      .findFirst()
                      .orElseThrow(() -> new UnivestException(
                              String.format("The user role %s is not known to the application.", role)));
    }

    public String getRole() {
        return role;
    }
}
