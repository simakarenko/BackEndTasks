package com.gmail.simakarenko93.config;

import com.gmail.simakarenko93.dto.AccountDTO;
import com.gmail.simakarenko93.dto.TaskDTO;
import com.gmail.simakarenko93.model.markers.UserRole;
import com.gmail.simakarenko93.services.interf.GeneralService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppConfig extends GlobalMethodSecurityConfiguration {
    @Bean
    public CommandLineRunner demo(final GeneralService generalService,
                                  final PasswordEncoder encoder) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                AccountDTO accountDTO = AccountDTO.of(
                        "test",
                        encoder.encode("1234"), UserRole.USER);

                List<TaskDTO> tasks = Arrays.asList(
                        TaskDTO.of(new Date(), "Test task 1"),
                        TaskDTO.of(new Date(), "Test task 2"),
                        TaskDTO.of(new Date(), "Test task 3"),
                        TaskDTO.of(new Date(), "Test task 4"),
                        TaskDTO.of(new Date(), "Test task 5"),
                        TaskDTO.of(new Date(), "Test task 6")
                );
                generalService.addAccount(accountDTO,tasks);
            }
        };
    }
}
