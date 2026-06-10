package com.example.journal_app.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.journal_app.entity.JournalEntry;
import com.example.journal_app.entity.User;
import com.example.journal_app.repository.JournalEntryRepository;

//  controller calls --> service calls --> repository
@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userservice;

    public void saveEntry(JournalEntry je, String name) throws Exception {
        try {
            User user = userservice.findByname(name);

            if (user == null) {
                throw new RuntimeException("User not found with username : " + name);
            }

            je.setDate(LocalDate.now());
            JournalEntry saved = journalEntryRepository.save(je);
            if (user.getJournalEntry() == null) {
                user.setJournalEntry(new ArrayList<>());
            }
            user.getJournalEntry().add(saved);
            userservice.saveEntry(user); // Save user into database
        } catch (RuntimeException e) {
            throw new Exception("Error : " + e);
        }
    }

    public void saveEntry(@NonNull JournalEntry je) {
        journalEntryRepository.save(je);
    }

    public List<JournalEntry> getEntry() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(String id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(String id, String username) {
        User user = userservice.findByname(username);
        if (user == null) {
            throw new RuntimeException("User not found with username : " + username);
        }
        boolean removeIf = user.getJournalEntry().removeIf(x -> x.getId().equals(id));
        if (removeIf) {
            userservice.saveEntry(user);
            journalEntryRepository.deleteById(id);
        }

    }
}
