package com.example.ai.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a conversation in the AI system.
 * Stores user queries and AI responses.
 */
@Entity
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_query", nullable = false, length = 1000)
    private String userQuery;

    @Column(name = "ai_response", nullable = false, length = 2000)
    private String aiResponse;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "session_id")
    private String sessionId;

    public Conversation() {
        this.timestamp = LocalDateTime.now();
    }

    public Conversation(String userQuery, String aiResponse, String sessionId) {
        this();
        this.userQuery = userQuery;
        this.aiResponse = aiResponse;
        this.sessionId = sessionId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserQuery() {
        return userQuery;
    }

    public void setUserQuery(String userQuery) {
        this.userQuery = userQuery;
    }

    public String getAiResponse() {
        return aiResponse;
    }

    public void setAiResponse(String aiResponse) {
        this.aiResponse = aiResponse;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", userQuery='" + userQuery + '\'' +
                ", aiResponse='" + aiResponse + '\'' +
                ", timestamp=" + timestamp +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
