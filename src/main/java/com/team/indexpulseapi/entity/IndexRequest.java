package com.team.indexpulseapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class IndexRequest {
    @Id
    private String id;
    private String url;
    private Status status;

    public IndexRequest() {
        this.id = UUID.randomUUID().toString();//UUID generates a random id.
        this.url = "";//Empty URL.
        this.status = Status.NOT_INDEXED;
    }

    public IndexRequest(String url) {
        this.id = UUID.randomUUID().toString();//UUID generates a random id.
        this.url = url;
        this.status = Status.NOT_INDEXED;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
