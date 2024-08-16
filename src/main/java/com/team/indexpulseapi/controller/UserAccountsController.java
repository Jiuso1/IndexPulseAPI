package com.team.indexpulseapi.controller;

import com.team.indexpulseapi.entity.UserAccount;
import com.team.indexpulseapi.repository.UserAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user_accounts")
public class UserAccountsController {
    private final UserAccountRepository userAccountRepository;

    public UserAccountsController(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userAccountRepository.saveAll(List.of(new UserAccount("test1@gmail.com", "test1", "Test", "One"), new UserAccount("test2@gmail.com", "test2", "Test", "Two"), new UserAccount("test3@gmail.com", "test3", "Test", "Three")));
    }

    @GetMapping
    Iterable<UserAccount> getUserAccounts() {
        return userAccountRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<UserAccount> getUserAccountsById(@PathVariable String id) {
        return userAccountRepository.findById(id);
    }

    @PostMapping("/register")
    UserAccount postUserAccountRegister(@RequestBody UserAccount userAccount) {
        UserAccount userAccountReturned = null;//User account to return.
        if (userAccountRepository.findByEmail(userAccount.getEmail()).isEmpty()) {//If there isn't an account with the same email:
            userAccountReturned = userAccountRepository.save(userAccount);//The account is saved.
        }
        return userAccountReturned;//The account is returned.
    }

    @PostMapping("/login")
    String postUserAccountLogin(@RequestBody UserAccount userAccount) {
        String id = "";//ID of the account if successfully logged in. It values "" otherwise.
        int i = 0;
        ArrayList<UserAccount> userAccountArrayList = userAccountRepository.findAll();//ArrayList of all user accounts registered.
        UserAccount userAccountIterated = null;

        //We search an account with the same email and password:
        while (id.isEmpty() && i < userAccountArrayList.size()) {
            userAccountIterated = userAccountArrayList.get(i);
            if (userAccountIterated.getEmail().equals(userAccount.getEmail())) {
                if (userAccountIterated.getPassword().equals(userAccount.getPassword())) {
                    id = userAccountIterated.getId();//If we have found an account, we get its id.
                }
            }
            i++;
        }

        return id;
    }

    @DeleteMapping("/{id}")
    void deleteUserAccount(@PathVariable String id) {
        userAccountRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    UserAccount putUserAccount(@PathVariable String id, @RequestBody UserAccount userAccount) {
        UserAccount userAccountReturned = null;//User account to return.
        if (!userAccountRepository.findById(id).isEmpty()) {//If we have found the same account by id:
            userAccountReturned = userAccountRepository.findById(id).get();//We get the account.
            //We set the new values that come with userAccount:
            userAccountReturned.setEmail(userAccount.getEmail());
            userAccountReturned.setPassword(userAccount.getPassword());
            userAccountReturned.setFirstName(userAccount.getFirstName());
            userAccountReturned.setLastName(userAccount.getLastName());
            userAccountRepository.save(userAccountReturned);//The account is updated in the repository. When we save an account with same id, it just updates the values.
        }
        return userAccountReturned;//The account is returned.
    }

}
