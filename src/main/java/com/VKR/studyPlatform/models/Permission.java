package com.VKR.studyPlatform.models;

public enum Permission {
    USERS_READ("users:read"),
    USERS_WRITE("users:write"),
    ADMIN_READ("admin:read");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
