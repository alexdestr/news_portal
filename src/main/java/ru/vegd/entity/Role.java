package ru.vegd.entity;

public enum Role {

    USER(1),
    MOD(2),
    ADMIN(3),
    SUPER_ADMIN(4),
    BANNED(0);

    Role(Integer roleId) {
        this.roleID = roleId;
    }

    private final Integer roleID;

    public Integer getRoleID() {
        return roleID;
    }

    public static Role getRoleByID(Integer id) {
        Role role;
        switch (id) {
            case 1:
                role = Role.USER;
                break;
            case 2:
                role = Role.MOD;
                break;
            case 3:
                role = Role.ADMIN;
                break;
            case 4:
                role = Role.SUPER_ADMIN;
                break;
            case 0:
                role = Role.BANNED;
                break;
                default:
                    role = Role.USER;
        }
        return role;
    }

}
