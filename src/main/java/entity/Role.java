package entity;

import java.util.Arrays;

public enum Role {

    USER(0), ADMIN(1);
    private int roleId;

    Role(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name().toLowerCase();
    }

    public int getRoleId() {
        return roleId;
    }

    public static Role of(int id) {
        return Arrays.stream(Role.values())
                .filter(role -> role.roleId == id)
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
