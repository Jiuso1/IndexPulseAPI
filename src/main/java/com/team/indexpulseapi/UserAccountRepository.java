package com.team.indexpulseapi;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

interface UserAccountRepository extends CrudRepository<UserAccount, String> {
    ArrayList<UserAccount> findAll();
}
