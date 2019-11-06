package lk.ijse.dep.sms.entity;

public class UserRole implements SuperEntity {
    private UserRolePK userRolePK;

    public UserRole() {
    }

    public UserRole(UserRolePK userRolePK) {
        this.userRolePK = userRolePK;
    }

    public UserRole(String userId, Integer roleId) {
        this.userRolePK = new UserRolePK(userId, roleId);
    }

    public UserRolePK getUserRolePK() {
        return userRolePK;
    }

    public void setUserRolePK(UserRolePK userRolePK) {
        this.userRolePK = userRolePK;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userRolePK=" + userRolePK +
                '}';
    }
}
