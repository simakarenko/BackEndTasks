package com.gmail.simakarenko93.dto;

import com.gmail.simakarenko93.model.markers.UserRole;

public class AccountDTO {
    private final String login;
    private final String password;
    private final UserRole role;


    private AccountDTO(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public static AccountDTO of(String login, String password, UserRole role) {
        return new AccountDTO(login,password,role);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }
}
