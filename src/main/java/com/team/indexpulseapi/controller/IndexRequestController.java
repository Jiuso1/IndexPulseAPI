package com.team.indexpulseapi.controller;

import com.team.indexpulseapi.entity.IndexRequest;
import com.team.indexpulseapi.repository.IndexRequestRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/index_requests")
public class IndexRequestController {
    private final IndexRequestRepository indexRequestRepository;

    public IndexRequestController(IndexRequestRepository indexRequestRepository) {
        this.indexRequestRepository = indexRequestRepository;
        //this.indexRequestRepository.saveAll(List.of(new IndexRequest("https://duckduckgo.com/", "12"), new IndexRequest("https://amazon.com/", "12"), new IndexRequest("https://bing.com/", "14")));
    }

    @GetMapping
    Iterable<IndexRequest> getIndexRequests() {
        return indexRequestRepository.findAll();
    }

    @GetMapping("/{userAccountId}")
    ArrayList<IndexRequest> getIndexRequestsByUserAccountId(@PathVariable String userAccountId) {
        return indexRequestRepository.findByUserAccountId(userAccountId);
    }

    @PostMapping("/register")
    IndexRequest postIndexRequestRegister(@RequestBody IndexRequest indexRequest) {
        String userAccountId = indexRequest.getUserAccountId();//ID of the user account that created this index request.
        ArrayList<IndexRequest> indexRequestArrayList = indexRequestRepository.findByUserAccountId(userAccountId);//We get all index requests created by this user account.

        IndexRequest indexRequestIterated = null;//Index request variable used to iterate.
        IndexRequest indexRequestReturned = null;//Index request variable used to return.

        int i = 0;//Counter.
        boolean found = false;//Values true when we've found an index request with same URL.

        while (!found && i < indexRequestArrayList.size()) {//While we haven't found same URL and we haven't passed indexRequestArrayList limits:
            indexRequestIterated = indexRequestArrayList.get(i);//Current index request to process is saved.
            if (indexRequestIterated.getUrl().equals(indexRequest.getUrl())) {//If the URL is equal to that of the request to be added:
                found = true;//Found!
            }
            i++;//We prepare the counter to check the next index request.
        }

        if (!found) {//If we don't find an index request with same URL:
            indexRequestReturned = indexRequestRepository.save(indexRequest);//The index request is saved. We return it.
        }

        return indexRequestReturned;//The value stored is returned.
    }

    @DeleteMapping("/{id}")
    void deleteIndexRequest(@PathVariable String id) {
        indexRequestRepository.deleteById(id);
    }

}
