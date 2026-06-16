package com.example.journal_app.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @org.springframework.data.annotation.Id
    private ObjectId Id;

    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    private List<String> roles;

    private String email;

    private boolean sentimentAnalysis;

    @DBRef // make link between jounalentries and user
    private List<JournalEntry> journalEntry = new ArrayList<>();

}