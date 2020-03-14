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

    public User(Long user_id, String login, String hash_password, String user_name, String user_last_name, Timestamp date_of_registration) {
        this.userId = user_id;
        this.login = login;
        this.hashPassword = hash_password;
        this.firstName = user_name;
        this.lastName = user_last_name;
        this.dateOfRegistration = date_of_registration;
    }

    public User() { }

    public Long getUser_id() {
        return userId;
    }

    public void setUser_id(Long user_id) {
        this.userId = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash_password() {
        return hashPassword;
    }

    public void setHash_password(String hash_password) {
        this.hashPassword = hash_password;
    }

    public String getUser_name() {
        return firstName;
    }

    public void setUser_name(String user_name) {
        this.firstName = user_name;
    }

    public String getUser_last_name() {
        return lastName;
    }

    public void setUser_last_name(String user_last_name) {
        this.lastName = user_last_name;
    }

    public Timestamp getDate_of_registration() {
        return dateOfRegistration;
    }

    public void setDate_of_registration(Timestamp date_of_registration) {
        this.dateOfRegistration = date_of_registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(login, user.login) &&
                Objects.equals(hashPassword, user.hashPassword) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(dateOfRegistration, user.dateOfRegistration);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, login, hashPassword, firstName, lastName, dateOfRegistration);
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + userId +
                ", login='" + login + '\'' +
                ", hash_password='" + hashPassword + '\'' +
                ", user_name='" + firstName + '\'' +
                ", user_last_name='" + lastName + '\'' +
                ", date_of_registration='" + dateOfRegistration + '\'' +
                '}';
    }
}

enum Role {

    USER,
    MOD,
    ADMIN,
    SUPER_ADMIN,
    BANNED

}

