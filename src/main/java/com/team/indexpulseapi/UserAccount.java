package com.team.indexpulseapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class UserAccount {
    @Id
    private String id;
    private String email;
    private String password;
    //We just specify the JSON property in camelCase attributes:
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;

    public UserAccount() {
        //Attributes are initialized with no content:
        this.id = "";
        this.email = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
    }

    public UserAccount(String email, String password, String firstName, String lastName) {
        this.id = UUID.randomUUID().toString();//UUID generates a random id.
        //Attributes are initialized with parameters:
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
