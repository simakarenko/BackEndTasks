package com.gmail.simakarenko93.services.interf;

import com.gmail.simakarenko93.dto.AccountDTO;
import com.gmail.simakarenko93.dto.TaskDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GeneralService {
    void addAccount(AccountDTO accountDTO, List<TaskDTO> tasks);
    void addTask(String login, TaskDTO taskDTO);

    List<TaskDTO> getTasks(String login, Pageable pageable);

    Long count(String login);

    void delete(List<Long> idList);
}
