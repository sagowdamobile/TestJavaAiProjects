package com.example.ai.repository;

import com.example.ai.model.Conversation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Repository for performing CRUD operations on Conversation entities.
 */
public class ConversationRepository {
    private final EntityManager entityManager;

    public ConversationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Create a new conversation record
     */
    public Conversation create(Conversation conversation) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(conversation);
            transaction.commit();
            return conversation;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to create conversation", e);
        }
    }

    /**
     * Find conversation by ID
     */
    public Optional<Conversation> findById(Long id) {
        Conversation conversation = entityManager.find(Conversation.class, id);
        return Optional.ofNullable(conversation);
    }

    /**
     * Find all conversations
     */
    public List<Conversation> findAll() {
        TypedQuery<Conversation> query = entityManager.createQuery(
            "SELECT c FROM Conversation c ORDER BY c.timestamp DESC", 
            Conversation.class
        );
        return query.getResultList();
    }

    /**
     * Find conversations by session ID
     */
    public List<Conversation> findBySessionId(String sessionId) {
        TypedQuery<Conversation> query = entityManager.createQuery(
            "SELECT c FROM Conversation c WHERE c.sessionId = :sessionId ORDER BY c.timestamp ASC", 
            Conversation.class
        );
        query.setParameter("sessionId", sessionId);
        return query.getResultList();
    }

    /**
     * Get recent conversations (last N)
     */
    public List<Conversation> findRecent(int limit) {
        TypedQuery<Conversation> query = entityManager.createQuery(
            "SELECT c FROM Conversation c ORDER BY c.timestamp DESC", 
            Conversation.class
        );
        query.setMaxResults(limit);
        return query.getResultList();
    }

    /**
     * Delete a conversation by ID
     */
    public boolean delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Conversation conversation = entityManager.find(Conversation.class, id);
            if (conversation != null) {
                entityManager.remove(conversation);
                transaction.commit();
                return true;
            }
            transaction.commit();
            return false;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to delete conversation", e);
        }
    }

    /**
     * Count total conversations
     */
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(c) FROM Conversation c", 
            Long.class
        );
        return query.getSingleResult();
    }
}
