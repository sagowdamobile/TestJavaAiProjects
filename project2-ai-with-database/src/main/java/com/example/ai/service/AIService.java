package com.example.ai.service;

import com.example.ai.model.Conversation;
import com.example.ai.model.KnowledgeEntry;
import com.example.ai.repository.ConversationRepository;
import com.example.ai.repository.KnowledgeRepository;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * AI Service that uses the knowledge base to generate responses.
 * This demonstrates a simple AI system that learns from and stores conversations in a database.
 */
public class AIService {
    private static final double CONFIDENCE_INCREMENT = 0.1;
    
    private final KnowledgeRepository knowledgeRepository;
    private final ConversationRepository conversationRepository;
    private final String sessionId;
    private final Random random;

    public AIService(KnowledgeRepository knowledgeRepository, 
                     ConversationRepository conversationRepository) {
        this.knowledgeRepository = knowledgeRepository;
        this.conversationRepository = conversationRepository;
        this.sessionId = UUID.randomUUID().toString();
        this.random = new Random();
    }

    /**
     * Process user query and generate response
     */
    public String processQuery(String userQuery) {
        // Search for matching knowledge entries
        List<KnowledgeEntry> matches = knowledgeRepository.searchByQuestion(userQuery);
        
        String response;
        if (!matches.isEmpty()) {
            // Use the best matching entry (first result, sorted by confidence)
            KnowledgeEntry bestMatch = matches.get(0);
            response = bestMatch.getAnswer();
            
            // Update confidence score
            bestMatch.setConfidenceScore(bestMatch.getConfidenceScore() + CONFIDENCE_INCREMENT);
            knowledgeRepository.update(bestMatch);
        } else {
            // Generate a default response
            response = generateDefaultResponse(userQuery);
        }
        
        // Save the conversation
        Conversation conversation = new Conversation(userQuery, response, sessionId);
        conversationRepository.create(conversation);
        
        return response;
    }

    /**
     * Generate a default response when no knowledge entry matches
     */
    private String generateDefaultResponse(String query) {
        String[] defaultResponses = {
            "That's an interesting question! I'm still learning about that topic.",
            "I don't have specific information about that yet, but I'm always learning!",
            "Could you tell me more about that? I'd like to learn!",
            "I'm not sure about that right now, but I'll remember this conversation for future reference."
        };
        return defaultResponses[random.nextInt(defaultResponses.length)];
    }

    /**
     * Add new knowledge to the knowledge base
     */
    public KnowledgeEntry addKnowledge(String question, String answer, String category) {
        KnowledgeEntry entry = new KnowledgeEntry(question, answer, category);
        entry.setConfidenceScore(1.0);
        return knowledgeRepository.create(entry);
    }

    /**
     * Get all knowledge entries
     */
    public List<KnowledgeEntry> getAllKnowledge() {
        return knowledgeRepository.findAll();
    }

    /**
     * Get knowledge entries by category
     */
    public List<KnowledgeEntry> getKnowledgeByCategory(String category) {
        return knowledgeRepository.findByCategory(category);
    }

    /**
     * Get conversation history for current session
     */
    public List<Conversation> getConversationHistory() {
        return conversationRepository.findBySessionId(sessionId);
    }

    /**
     * Get all conversations
     */
    public List<Conversation> getAllConversations() {
        return conversationRepository.findAll();
    }

    /**
     * Initialize the knowledge base with sample data
     */
    public void initializeSampleKnowledge() {
        // Check if knowledge base is empty
        if (knowledgeRepository.count() > 0) {
            return; // Already initialized
        }

        // Add sample knowledge entries
        addKnowledge("What is artificial intelligence?", 
                    "Artificial Intelligence (AI) is the simulation of human intelligence by machines, especially computer systems.", 
                    "AI Basics");
        
        addKnowledge("What is machine learning?", 
                    "Machine Learning is a subset of AI that enables systems to learn and improve from experience without being explicitly programmed.", 
                    "AI Basics");
        
        addKnowledge("What is Java?", 
                    "Java is a high-level, class-based, object-oriented programming language designed to have as few implementation dependencies as possible.", 
                    "Programming");
        
        addKnowledge("What is a database?", 
                    "A database is an organized collection of structured information, or data, typically stored electronically in a computer system.", 
                    "Database");
        
        addKnowledge("What is JPA?", 
                    "JPA (Java Persistence API) is a Java specification for accessing, persisting, and managing data between Java objects and relational databases.", 
                    "Database");
        
        addKnowledge("How are you?", 
                    "I'm doing great! Thanks for asking. I'm ready to help you with any questions!", 
                    "General");
        
        addKnowledge("What can you do?", 
                    "I can answer questions based on my knowledge base, learn from conversations, and store information in a database!", 
                    "General");
    }

    /**
     * Get statistics about the knowledge base
     */
    public String getStatistics() {
        long knowledgeCount = knowledgeRepository.count();
        long conversationCount = conversationRepository.count();
        
        return String.format(
            "Knowledge Base Stats:\n" +
            "- Total Knowledge Entries: %d\n" +
            "- Total Conversations: %d\n" +
            "- Current Session: %s",
            knowledgeCount, conversationCount, sessionId
        );
    }
}
