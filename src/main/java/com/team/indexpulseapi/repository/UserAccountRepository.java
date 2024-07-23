package com.team.indexpulseapi.repository;

import com.team.indexpulseapi.entity.UserAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserAccountRepository extends CrudRepository<UserAccount, String> {
    //All custom queries will return an ArrayList<>, which is much easier to operate with:
    ArrayList<UserAccount> findAll();
    @Query("SELECT u FROM UserAccount u WHERE u.email = :email")
    ArrayList<UserAccount> findByEmail(String email);
}
