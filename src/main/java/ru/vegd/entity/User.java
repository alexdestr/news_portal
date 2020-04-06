package ru.vegd.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class User {

    private Long userId;
    private String login;
    private String hashPassword;
    private String firstName;
    private String lastName;
    private Timestamp dateOfRegistration;
    private Role role;

    public User(Long userId, String login, String hashPassword, String firstName, String lastName, Timestamp dateOfRegistration, Role role) {
        this.userId = userId;
        this.login = login;
        this.hashPassword = hashPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfRegistration = dateOfRegistration;
        this.role = role;
    }

    public User() { }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long user_id) {
        this.userId = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hash_password) {
        this.hashPassword = hash_password;
    }

    public String getUserName() {
        return firstName;
    }

    public void setUserName(String user_name) {
        this.firstName = user_name;
    }

    public String getUserLastName() {
        return lastName;
    }

    public void setUserLastName(String user_last_name) {
        this.lastName = user_last_name;
    }

    public Timestamp getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Timestamp date_of_registration) {
        this.dateOfRegistration = date_of_registration;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(login, user.login) &&
                Objects.equals(hashPassword, user.hashPassword) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(dateOfRegistration, user.dateOfRegistration) &&
                role == user.role;
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, login, hashPassword, firstName, lastName, dateOfRegistration, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfRegistration=" + dateOfRegistration +
                ", role=" + role +
                '}';
    }

}


