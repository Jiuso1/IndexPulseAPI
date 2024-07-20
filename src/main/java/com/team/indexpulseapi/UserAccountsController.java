package com.team.indexpulseapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user_accounts")
public class UserAccountsController {
    private final UserAccountRepository userAccountRepository;

    public UserAccountsController(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;

        this.userAccountRepository.saveAll(List.of(
                new UserAccount("test1@gmail.com", "test1", "Test", "One"),
                new UserAccount("test2@gmail.com", "test2", "Test", "Two"),
                new UserAccount("test3@gmail.com", "test3", "Test", "Three")
        ));
    }

    @GetMapping
    Iterable<UserAccount> getUserAccounts() {
        return userAccountRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<UserAccount> getUserAccountsById(@PathVariable String id){
        return userAccountRepository.findById(id);
    }

    @PostMapping
    UserAccount postUserAccount(@RequestBody UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    @DeleteMapping("/{id}")
    void deleteUserAccount(@PathVariable String id) {
        userAccountRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<UserAccount> putUserAccount(@PathVariable String id,
                                     @RequestBody UserAccount userAccount) {
        return (!userAccountRepository.existsById(id))
                ? new ResponseEntity<>(userAccountRepository.save(userAccount),
                HttpStatus.CREATED)
                : new ResponseEntity<>(userAccountRepository.save(userAccount), HttpStatus.OK);
    }

}
