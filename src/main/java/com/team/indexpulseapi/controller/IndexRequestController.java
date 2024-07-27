package com.team.indexpulseapi.controller;

import com.team.indexpulseapi.entity.IndexRequest;
import com.team.indexpulseapi.repository.IndexRequestRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/index_requests")
public class IndexRequestController {
    private final IndexRequestRepository indexRequestRepository;

    public IndexRequestController(IndexRequestRepository indexRequestRepository) {
        this.indexRequestRepository = indexRequestRepository;
        this.indexRequestRepository.saveAll(List.of(new IndexRequest("https://duckduckgo.com/", "12"), new IndexRequest("https://amazon.com/", "12"), new IndexRequest("https://bing.com/", "14")));
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
        IndexRequest indexRequestReturned = null;//Index request to return.
        if (indexRequestRepository.findByUrl(indexRequest.getUrl()).isEmpty()) {//If there isn't an index request with the same url:
            indexRequestReturned = indexRequestRepository.save(indexRequest);//The index request is saved.
        }
        return indexRequestReturned;//The account is returned.
    }

    @DeleteMapping("/{id}")
    void deleteIndexRequest(@PathVariable String id) {
        indexRequestRepository.deleteById(id);
    }

}
