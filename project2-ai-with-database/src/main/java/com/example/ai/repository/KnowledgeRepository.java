package com.example.ai.repository;

import com.example.ai.model.KnowledgeEntry;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Repository for performing CRUD operations on KnowledgeEntry entities.
 */
public class KnowledgeRepository {
    private final EntityManager entityManager;

    public KnowledgeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Create a new knowledge entry
     */
    public KnowledgeEntry create(KnowledgeEntry entry) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entry);
            transaction.commit();
            return entry;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to create knowledge entry", e);
        }
    }

    /**
     * Find knowledge entry by ID
     */
    public Optional<KnowledgeEntry> findById(Long id) {
        KnowledgeEntry entry = entityManager.find(KnowledgeEntry.class, id);
        return Optional.ofNullable(entry);
    }

    /**
     * Find all knowledge entries
     */
    public List<KnowledgeEntry> findAll() {
        TypedQuery<KnowledgeEntry> query = entityManager.createQuery(
            "SELECT k FROM KnowledgeEntry k ORDER BY k.createdAt DESC", 
            KnowledgeEntry.class
        );
        return query.getResultList();
    }

    /**
     * Find knowledge entries by category
     */
    public List<KnowledgeEntry> findByCategory(String category) {
        TypedQuery<KnowledgeEntry> query = entityManager.createQuery(
            "SELECT k FROM KnowledgeEntry k WHERE k.category = :category ORDER BY k.createdAt DESC", 
            KnowledgeEntry.class
        );
        query.setParameter("category", category);
        return query.getResultList();
    }

    /**
     * Search knowledge entries by question pattern
     */
    public List<KnowledgeEntry> searchByQuestion(String searchTerm) {
        TypedQuery<KnowledgeEntry> query = entityManager.createQuery(
            "SELECT k FROM KnowledgeEntry k WHERE LOWER(k.question) LIKE LOWER(:searchTerm) ORDER BY k.confidenceScore DESC", 
            KnowledgeEntry.class
        );
        query.setParameter("searchTerm", "%" + searchTerm + "%");
        return query.getResultList();
    }

    /**
     * Update an existing knowledge entry
     */
    public KnowledgeEntry update(KnowledgeEntry entry) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            KnowledgeEntry updated = entityManager.merge(entry);
            transaction.commit();
            return updated;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update knowledge entry", e);
        }
    }

    /**
     * Delete a knowledge entry by ID
     */
    public boolean delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            KnowledgeEntry entry = entityManager.find(KnowledgeEntry.class, id);
            if (entry != null) {
                entityManager.remove(entry);
                transaction.commit();
                return true;
            }
            transaction.commit();
            return false;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to delete knowledge entry", e);
        }
    }

    /**
     * Count total knowledge entries
     */
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(k) FROM KnowledgeEntry k", 
            Long.class
        );
        return query.getSingleResult();
    }
}
