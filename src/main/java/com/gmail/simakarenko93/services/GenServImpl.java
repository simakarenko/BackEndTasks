package com.gmail.simakarenko93.services;

import com.gmail.simakarenko93.dto.AccountDTO;
import com.gmail.simakarenko93.dto.TaskDTO;
import com.gmail.simakarenko93.model.Account;
import com.gmail.simakarenko93.model.Task;
import com.gmail.simakarenko93.repos.AccountRepository;
import com.gmail.simakarenko93.repos.TaskRepository;
import com.gmail.simakarenko93.services.interf.GeneralService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenServImpl implements GeneralService {
    private final AccountRepository accountRepository;
    private final TaskRepository taskRepository;

    public GenServImpl(AccountRepository accountRepository, TaskRepository taskRepository) {
        this.accountRepository = accountRepository;
        this.taskRepository = taskRepository;
    }
    @Transactional
    @Override
    public void addAccount(AccountDTO accountDTO, List<TaskDTO> tasks) {
        if (accountRepository.existsByLogin(accountDTO.getLogin()))
            return; // do nothing

        Account account = Account.fromDTO(accountDTO);

        tasks.forEach((x) -> {
            Task task = Task.fromDTO(x);
            account.addTask(task);
        });

        accountRepository.save(account);
    }
    @Transactional
    @Override
    public void addTask(String login, TaskDTO taskDTO) {
        Account account = accountRepository.findByLogin(login);
        Task task = Task.fromDTO(taskDTO);

        account.addTask(task);

        accountRepository.save(account);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TaskDTO> getTasks(String login, Pageable pageable) {
        List<TaskDTO> result = new ArrayList<>();
        List<Task> tasks = taskRepository.findByAccountLogin(login, pageable);

        tasks.forEach((x) -> result.add(x.toDTO()));
        return result;
    }
    @Transactional(readOnly = true)
    @Override
    public Long count(String login) {
        return taskRepository.countByAccountLogin(login);
    }

    @Transactional
    @Override
    public void delete(List<Long> idList) {
        idList.forEach((x) -> taskRepository.deleteById(x));
    }
    @Transactional(readOnly = true)
    public Account findByLogin(String login) {
        return accountRepository.findByLogin(login);
    }
}
