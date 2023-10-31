package com.gmail.simakarenko93.controllers;

import com.gmail.simakarenko93.dto.AccountDTO;
import com.gmail.simakarenko93.dto.PageCountDTO;
import com.gmail.simakarenko93.dto.TaskDTO;
import com.gmail.simakarenko93.model.Account;
import com.gmail.simakarenko93.services.GenServImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    
    private static final int PAGE_SIZE = 5;

    private final GenServImpl genServ;

    public MainController(GenServImpl genServ) {
        this.genServ = genServ;
    }
    @GetMapping("/login")
    public String showLoginForm(){
        return"login.html";
    }
    @GetMapping("/account")
    public AccountDTO account(Model model) {
        User user = getCurrentUser();
        String login = user.getUsername();
        Account dbUser = genServ.findByLogin(login);
        return AccountDTO.of(login,dbUser.getPassword(),dbUser.getRole());
    }
    private User getCurrentUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @GetMapping("count")
    public PageCountDTO count(Model model) {
        String login = getLogin(model);
        return PageCountDTO.of(genServ.count(login), PAGE_SIZE);
    }

    @GetMapping("tasks")
    public List<TaskDTO> tasks(Model model,
                               @RequestParam(required = false, defaultValue = "0") Integer page) {
        String login = getLogin(model);
        return genServ.getTasks(login,
                PageRequest.of(
                        page,
                        PAGE_SIZE,
                        Sort.Direction.DESC,
                        "id"
                )
        );
    }
    private String getLogin(Model model) {
        return (String) model.getAttribute("login");
    }
}
