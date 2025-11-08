package com.example.ai;

import com.example.ai.model.Conversation;
import com.example.ai.model.KnowledgeEntry;
import com.example.ai.repository.ConversationRepository;
import com.example.ai.repository.KnowledgeRepository;
import com.example.ai.service.AIService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class for the AI Knowledge Base System.
 * Demonstrates database integration with JPA and AI functionality.
 */
public class AIKnowledgeBaseApp {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static AIService aiService;
    private static Scanner scanner;

    public static void main(String[] args) {
        try {
            // Initialize JPA
            initializeJPA();
            
            // Initialize repositories
            KnowledgeRepository knowledgeRepo = new KnowledgeRepository(em);
            ConversationRepository conversationRepo = new ConversationRepository(em);
            
            // Initialize AI Service
            aiService = new AIService(knowledgeRepo, conversationRepo);
            
            // Initialize sample knowledge
            aiService.initializeSampleKnowledge();
            
            // Start the application
            scanner = new Scanner(System.in);
            showWelcomeMessage();
            runMainMenu();
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cleanup();
        }
    }

    private static void initializeJPA() {
        System.out.println("Initializing database connection...");
        emf = Persistence.createEntityManagerFactory("AIKnowledgeBasePU");
        em = emf.createEntityManager();
        System.out.println("Database connection established!\n");
    }

    private static void showWelcomeMessage() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║     AI Knowledge Base Application                    ║");
        System.out.println("║     With Database Integration (JPA + H2)             ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private static void runMainMenu() {
        boolean running = true;
        
        while (running) {
            showMainMenu();
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    chatWithAI();
                    break;
                case "2":
                    viewKnowledgeBase();
                    break;
                case "3":
                    addKnowledge();
                    break;
                case "4":
                    viewConversationHistory();
                    break;
                case "5":
                    viewStatistics();
                    break;
                case "6":
                    demonstrateCRUD();
                    break;
                case "7":
                    running = false;
                    System.out.println("\nThank you for using the AI Knowledge Base Application!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n═══════════════ Main Menu ═══════════════");
        System.out.println("1. Chat with AI");
        System.out.println("2. View Knowledge Base");
        System.out.println("3. Add New Knowledge");
        System.out.println("4. View Conversation History");
        System.out.println("5. View Statistics");
        System.out.println("6. Demonstrate CRUD Operations");
        System.out.println("7. Exit");
        System.out.println("═════════════════════════════════════════");
        System.out.print("Enter your choice (1-7): ");
    }

    private static void chatWithAI() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║     AI Chat Mode                     ║");
        System.out.println("║     Type 'exit' to return to menu    ║");
        System.out.println("╚══════════════════════════════════════╝\n");
        
        while (true) {
            System.out.print("You: ");
            String query = scanner.nextLine().trim();
            
            if (query.equalsIgnoreCase("exit")) {
                break;
            }
            
            if (query.isEmpty()) {
                continue;
            }
            
            String response = aiService.processQuery(query);
            System.out.println("AI: " + response + "\n");
        }
    }

    private static void viewKnowledgeBase() {
        System.out.println("\n═══════════════ Knowledge Base ═══════════════");
        List<KnowledgeEntry> entries = aiService.getAllKnowledge();
        
        if (entries.isEmpty()) {
            System.out.println("No knowledge entries found.");
        } else {
            for (KnowledgeEntry entry : entries) {
                System.out.println("\nID: " + entry.getId());
                System.out.println("Category: " + entry.getCategory());
                System.out.println("Question: " + entry.getQuestion());
                System.out.println("Answer: " + entry.getAnswer());
                System.out.println("Confidence: " + String.format("%.2f", entry.getConfidenceScore()));
                System.out.println("─────────────────────────────────────────────");
            }
        }
        
        System.out.println("\nTotal entries: " + entries.size());
    }

    private static void addKnowledge() {
        System.out.println("\n═══════════════ Add New Knowledge ═══════════════");
        
        System.out.print("Enter question: ");
        String question = scanner.nextLine().trim();
        
        if (question.isEmpty()) {
            System.out.println("Question cannot be empty.");
            return;
        }
        
        System.out.print("Enter answer: ");
        String answer = scanner.nextLine().trim();
        
        if (answer.isEmpty()) {
            System.out.println("Answer cannot be empty.");
            return;
        }
        
        System.out.print("Enter category: ");
        String category = scanner.nextLine().trim();
        
        if (category.isEmpty()) {
            category = "General";
        }
        
        KnowledgeEntry entry = aiService.addKnowledge(question, answer, category);
        System.out.println("\n✓ Knowledge added successfully! (ID: " + entry.getId() + ")");
    }

    private static void viewConversationHistory() {
        System.out.println("\n═══════════════ All Conversations ═══════════════");
        List<Conversation> conversations = aiService.getAllConversations();
        
        if (conversations.isEmpty()) {
            System.out.println("No conversations found.");
        } else {
            for (Conversation conv : conversations) {
                System.out.println("\nID: " + conv.getId());
                String sessionIdDisplay = conv.getSessionId();
                if (sessionIdDisplay != null && sessionIdDisplay.length() > 8) {
                    sessionIdDisplay = sessionIdDisplay.substring(0, 8) + "...";
                }
                System.out.println("Session: " + sessionIdDisplay);
                System.out.println("Time: " + conv.getTimestamp());
                System.out.println("User: " + conv.getUserQuery());
                System.out.println("AI: " + conv.getAiResponse());
                System.out.println("─────────────────────────────────────────────");
            }
        }
        
        System.out.println("\nTotal conversations: " + conversations.size());
    }

    private static void viewStatistics() {
        System.out.println("\n═══════════════ System Statistics ═══════════════");
        System.out.println(aiService.getStatistics());
    }

    private static void demonstrateCRUD() {
        System.out.println("\n═══════════════ CRUD Operations Demo ═══════════════");
        System.out.println("Demonstrating Create, Read, Update, Delete operations...\n");
        
        // CREATE
        System.out.println("1. CREATE: Adding a test knowledge entry...");
        KnowledgeEntry testEntry = aiService.addKnowledge(
            "What is CRUD?",
            "CRUD stands for Create, Read, Update, and Delete - the four basic database operations.",
            "Demo"
        );
        System.out.println("   ✓ Created entry with ID: " + testEntry.getId());
        
        // READ
        System.out.println("\n2. READ: Retrieving the entry...");
        List<KnowledgeEntry> demoEntries = aiService.getKnowledgeByCategory("Demo");
        if (!demoEntries.isEmpty()) {
            System.out.println("   ✓ Found entry: " + demoEntries.get(0).getQuestion());
        }
        
        // UPDATE
        System.out.println("\n3. UPDATE: Updating the entry's confidence score...");
        testEntry.setConfidenceScore(5.0);
        testEntry.setAnswer("CRUD stands for Create, Read, Update, and Delete - essential database operations for data management.");
        KnowledgeRepository repo = new KnowledgeRepository(em);
        repo.update(testEntry);
        System.out.println("   ✓ Updated entry successfully");
        
        // DELETE (optional - commented out to preserve demo data)
        System.out.println("\n4. DELETE: Demo complete (entry preserved for reference)");
        System.out.println("   Note: You can view this entry in the Knowledge Base");
        
        System.out.println("\n✓ CRUD demonstration completed!");
    }

    private static void cleanup() {
        if (scanner != null) {
            scanner.close();
        }
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
        System.out.println("Database connection closed.");
    }
}
