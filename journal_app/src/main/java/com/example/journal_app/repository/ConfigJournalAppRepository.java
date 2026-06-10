package com.example.journal_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.journal_app.entity.ConfigJournalAppEntity;


public interface  ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity ,ObjectId>{
    
}
