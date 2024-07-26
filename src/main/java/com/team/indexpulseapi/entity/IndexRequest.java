package com.team.indexpulseapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class IndexRequest {
    @Id
    private String id;
    //We just specify the JSON property in camelCase attributes:
    @JsonProperty("user_account_id")
    private String userAccountId;
    private String url;
    private Status status;

    public IndexRequest() {
        this.id = UUID.randomUUID().toString();//UUID generates a random id.
        this.url = "";//Empty URL.
        this.userAccountId = "";//Empty user account ID.
        this.status = Status.NOT_INDEXED;
    }

    public IndexRequest(String url, String userAccountId) {
        this.id = UUID.randomUUID().toString();//UUID generates a random id.
        this.userAccountId = userAccountId;
        this.url = url;
        this.status = Status.NOT_INDEXED;
    }

    public String getId() {
        return id;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
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
