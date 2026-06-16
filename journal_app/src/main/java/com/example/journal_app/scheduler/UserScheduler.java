package com.example.journal_app.scheduler;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.journal_app.entity.JournalEntry;
import com.example.journal_app.entity.User;
import com.example.journal_app.enums.Sentiment;
import com.example.journal_app.repository.UserRepositoryImpl;
import com.example.journal_app.services.EmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepositorylmpl;

    @Autowired
    private EmailService emailService;

    
    @Scheduled(cron = "*/10 * * * * *")
    public void fetchUserAndSendSaMail() {

        List<User> users = userRepositorylmpl.getUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntry();
            List<Sentiment> filteredEntries = journalEntries.stream()
                    .filter(x -> x.getDate().isAfter(LocalDate.now().minus(7, ChronoUnit.DAYS)))
                    .map(x -> x.getSentiment()).collect(toList());

            Map<Sentiment, Integer> SentimentCounts = new HashMap<>();

            for (Sentiment s : filteredEntries) {
                if (s != null) {
                    SentimentCounts.put(s, SentimentCounts.getOrDefault(s, 0) + 1);
                }
            }
            Sentiment mostUsed = null;
            int maxCount = 0;

            for (Map.Entry<Sentiment, Integer> en : SentimentCounts.entrySet()) {
                if (en.getValue() > maxCount) {
                    mostUsed = en.getKey();
                    maxCount = en.getValue();
                }
            }
            if (mostUsed != null) {
                emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days ", mostUsed.toString());
            }
        }
    }

    // int i = 2;

    // @Scheduled(cron = "*/2 * * * * *")
    // public void ScheduledTest() {
    // log.info("hi this is the output at :" + i + " seconds");
    // i = i + 2;
    // }
}
