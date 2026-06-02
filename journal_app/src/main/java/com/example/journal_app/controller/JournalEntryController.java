package com.example.journal_app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.journal_app.entity.JournalEntry;
import com.example.journal_app.entity.User;
import com.example.journal_app.services.JournalEntryService;
import com.example.journal_app.services.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService jrs;

    @Autowired
    private UserService userservice;

    private List<JournalEntry> collect;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntryofUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userservice.findByname(username);
        if (user == null) {
            return new ResponseEntity<>("User not Found !", HttpStatus.NOT_FOUND);
        }
        List<JournalEntry> li = user.getJournalEntry();
        if (li != null && !li.isEmpty()) {
            return new ResponseEntity<>(li, HttpStatus.OK);
        }
        return new ResponseEntity<>(li, HttpStatus.NOT_FOUND);
    }

    @GetMapping("getContent/{id}")
    public ResponseEntity<?> getContentById(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userservice.findByname(username);

        List<JournalEntry> collect = user.getJournalEntry().stream().filter(x -> x.getId().equals(id))
                .collect(Collectors.toList());

        if (!collect.isEmpty()) {
            Optional<JournalEntry> user_entry = jrs.findById(id);
            if (user_entry.isPresent())
                return new ResponseEntity<>(user_entry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteIdEntry(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        jrs.deleteById(id, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry entry) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        jrs.saveEntry(entry, username);
        return new ResponseEntity<>(entry, HttpStatus.CREATED);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> UpdateById(@PathVariable String id, @RequestBody JournalEntry new_entry) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user = auth.getName();

        User user_detail = userservice.findByname(user);
        List<JournalEntry> entry = user_detail.getJournalEntry().stream().filter(x -> x.getId().equals(id))
                .collect(Collectors.toList());

        if (!entry.isEmpty()) {
            Optional<JournalEntry> old_entry = jrs.findById(id);
            if (old_entry.isPresent()) {
                JournalEntry old = old_entry.get();
                old.setTitle(!new_entry.getTitle().equals("") ? new_entry.getTitle()
                        : old.getTitle());
                old.setContent(
                        new_entry.getContent() != null && !new_entry.getContent().equals("") ? new_entry.getContent()
                                : old.getContent());
                jrs.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
