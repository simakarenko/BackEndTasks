package com.gmail.simakarenko93.model;

import com.gmail.simakarenko93.dto.AccountDTO;
import com.gmail.simakarenko93.model.markers.UserRole;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Task> tasks = new ArrayList<>();

    public Account() {}

    private Account(String login, String password, UserRole role) {
        this.login=login;
        this.password=password;
        this.role=role;
    }
    public static Account of(String login, String password, UserRole role) {
        return new Account(login,password,role);
    }


    public static Account fromDTO(AccountDTO accountDTO) {
        return Account.of(accountDTO.getLogin(),accountDTO.getPassword(),accountDTO.getRole());
    }
    public AccountDTO toDTO() {
        return AccountDTO.of(login,password,role);
    }

    public void addTask(Task task) {
        task.setAccount(this);
        tasks.add(task);
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }
}
