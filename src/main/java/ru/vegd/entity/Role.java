package ru.vegd.entity;

public enum Role {

    ROLE_USER(1),
    ROLE_MOD(2),
    ROLE_ADMIN(3),
    ROLE_SUPER_ADMIN(4),
    ROLE_BANNED(0);

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
                role = Role.ROLE_USER;
                break;
            case 2:
                role = Role.ROLE_MOD;
                break;
            case 3:
                role = Role.ROLE_ADMIN;
                break;
            case 4:
                role = Role.ROLE_SUPER_ADMIN;
                break;
            case 0:
                role = Role.ROLE_BANNED;
                break;
                default:
                    role = Role.ROLE_USER;
        }
        return role;
    }

}
