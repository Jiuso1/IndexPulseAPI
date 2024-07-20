package com.team.indexpulseapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class IndexPulseApiController {
    private final UserAccountRepository userAccountRepository;

    public IndexPulseApiController(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;

        this.userAccountRepository.saveAll(List.of(
                new UserAccount("test1@gmail.com", "test1", "Test", "One"),
                new UserAccount("test2@gmail.com", "test2", "Test", "Two"),
                new UserAccount("test3@gmail.com", "test3", "Test", "Three")
        ));
    }

    @GetMapping("/users")
    Iterable<UserAccount> getUsers() {
        return userAccountRepository.findAll();
    }

}
