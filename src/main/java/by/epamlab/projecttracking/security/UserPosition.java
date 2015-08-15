package by.epamlab.projecttracking.security;

public enum UserPosition {

    ADMIN(new String[]{UserRoleConstants.ADMIN}),
    PROJECT_MANAGER(new String[]{UserRoleConstants.PR_MANAGER, UserRoleConstants.USER}),
    TEAM_LEAD(new String[]{UserRoleConstants.TEAM_LEAD, UserRoleConstants.USER}),
    JUNIOR(new String[]{UserRoleConstants.USER}),
    MIDDLE(new String[]{UserRoleConstants.USER}),
    SENIOR(new String[]{UserRoleConstants.USER});

    private final String[] ROLES;

    UserPosition(String[] roles) {
        this.ROLES = roles;
    }

    public String[] getRole() {
        return ROLES;
    }

}
