package com.example.ai;

import java.util.*;

/**
 * A simple rule-based AI chatbot demonstrating basic AI logic.
 * This chatbot uses pattern matching and predefined responses to simulate conversation.
 */
public class SimpleChatbot {
    private final Map<String, String[]> knowledgeBase;
    private final Random random;
    private final Scanner scanner;

    public SimpleChatbot() {
        this.knowledgeBase = new HashMap<>();
        this.random = new Random();
        this.scanner = new Scanner(System.in);
        initializeKnowledgeBase();
    }

    /**
     * Initialize the chatbot's knowledge base with patterns and responses
     */
    private void initializeKnowledgeBase() {
        // Greetings
        knowledgeBase.put("hello|hi|hey|greetings", new String[]{
            "Hello! How can I help you today?",
            "Hi there! What can I do for you?",
            "Hey! Nice to meet you!"
        });

        // Questions about the bot
        knowledgeBase.put("who are you|what are you|your name", new String[]{
            "I'm a simple AI chatbot created to demonstrate basic AI logic!",
            "I'm an AI assistant built with rule-based patterns.",
            "I'm a beginner-friendly chatbot example!"
        });

        // Weather queries
        knowledgeBase.put("weather|temperature|forecast", new String[]{
            "I'm a simple chatbot and don't have access to real weather data, but I'd suggest checking a weather website!",
            "I can't check the weather, but I hope it's nice where you are!"
        });

        // How are you
        knowledgeBase.put("how are you|how do you do", new String[]{
            "I'm doing great! Thanks for asking. How about you?",
            "I'm just a program, but I'm functioning perfectly!",
            "I'm excellent! Ready to chat!"
        });

        // Help queries
        knowledgeBase.put("help|what can you do|capabilities", new String[]{
            "I can chat with you about various topics! Try asking me about myself, the weather, or just say hello!",
            "I'm a demonstration of rule-based AI. I can respond to greetings, questions about myself, and simple queries!"
        });

        // Time queries
        knowledgeBase.put("time|date|what time", new String[]{
            "The current time is: " + new Date(),
            "Right now it's: " + new Date()
        });

        // Jokes
        knowledgeBase.put("joke|funny|laugh", new String[]{
            "Why did the programmer quit his job? Because he didn't get arrays! 😄",
            "What do you call a programmer from Finland? Nerdic! 😊",
            "Why do Java developers wear glasses? Because they don't C#! 😂"
        });

        // Farewell
        knowledgeBase.put("bye|goodbye|exit|quit|see you", new String[]{
            "Goodbye! Have a great day!",
            "See you later! Take care!",
            "Bye! Thanks for chatting with me!"
        });

        // Default responses
        knowledgeBase.put("default", new String[]{
            "That's interesting! Tell me more.",
            "I'm not sure I understand. Could you rephrase that?",
            "Hmm, I'm still learning. Can you ask something else?",
            "I see. What else would you like to talk about?"
        });
    }

    /**
     * Find a matching pattern in the knowledge base
     */
    private String findResponse(String userInput) {
        String input = userInput.toLowerCase().trim();

        // Check each pattern in the knowledge base
        for (Map.Entry<String, String[]> entry : knowledgeBase.entrySet()) {
            String pattern = entry.getKey();
            String[] responses = entry.getValue();

            // Split the pattern by | to check multiple keywords
            String[] keywords = pattern.split("\\|");
            for (String keyword : keywords) {
                if (input.contains(keyword.trim())) {
                    // Return a random response from the available responses
                    return responses[random.nextInt(responses.length)];
                }
            }
        }

        // Return default response if no pattern matches
        String[] defaultResponses = knowledgeBase.get("default");
        return defaultResponses[random.nextInt(defaultResponses.length)];
    }

    /**
     * Start the chatbot conversation
     */
    public void startChat() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   Welcome to Simple AI Chatbot!           ║");
        System.out.println("║   Type 'exit' or 'quit' to end chat       ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println();

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if (userInput == null || userInput.trim().isEmpty()) {
                continue;
            }

            // Check for exit commands
            if (userInput.toLowerCase().matches(".*(exit|quit|bye|goodbye).*")) {
                System.out.println("Bot: " + findResponse(userInput));
                break;
            }

            // Get and display response
            String response = findResponse(userInput);
            System.out.println("Bot: " + response);
            System.out.println();
        }

        scanner.close();
    }

    /**
     * Main method to run the chatbot
     */
    public static void main(String[] args) {
        SimpleChatbot chatbot = new SimpleChatbot();
        chatbot.startChat();
    }
}
