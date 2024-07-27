package com.team.indexpulseapi.repository;

import com.team.indexpulseapi.entity.IndexRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IndexRequestRepository extends CrudRepository<IndexRequest, String> {
    //All custom queries will return an ArrayList<>, which is much easier to operate with:
    ArrayList<IndexRequest> findAll();
    @Query("SELECT i FROM IndexRequest i WHERE i.url = :url")
    ArrayList<IndexRequest> findByUrl(String url);
    @Query("SELECT i FROM IndexRequest i WHERE i.userAccountId = :userAccountId")
    ArrayList<IndexRequest> findByUserAccountId(String userAccountId);
}
