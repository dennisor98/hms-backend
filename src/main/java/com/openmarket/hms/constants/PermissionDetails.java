package com.openmarket.hms.constants;

public class PermissionDetails {
	private String permission;
    private String description;
    private String category;

    public PermissionDetails(String permission, String description, String category) {
        this.permission = permission;
        this.description = description;
        this.category = category;
    }

    public String getPermission() {
        return permission;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }
}
