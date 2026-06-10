package com.example.journal_app.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.journal_app.entity.ConfigJournalAppEntity;
import com.example.journal_app.repository.ConfigJournalAppRepository;

import jakarta.annotation.PostConstruct;

@Component
public class AppCache {

    public Map<String, String> cache;

    @Autowired
    private ConfigJournalAppRepository configjournalAppRepository;

    public AppCache() {
        this.cache = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        List<ConfigJournalAppEntity> all = configjournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            cache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }
}
