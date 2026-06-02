package com.example.journal_app.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

@Document(collection = "users")
@Data
public class User {
    @org.springframework.data.annotation.Id
    private ObjectId Id;

    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    private List<String> roles;

    @DBRef // make link between jounalentries and user 
    private List<JournalEntry> journalEntry  = new ArrayList<>();

}