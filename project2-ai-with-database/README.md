# Project 2: AI Knowledge Base Application with Database Integration

A comprehensive Java AI application demonstrating database integration using JPA/Hibernate with CRUD operations and AI functionality.

## Overview

This project showcases a complete AI system that:
- Stores and retrieves knowledge from a database
- Processes user queries using pattern matching against the knowledge base
- Maintains conversation history
- Performs full CRUD (Create, Read, Update, Delete) operations
- Uses JPA/Hibernate for ORM (Object-Relational Mapping)
- Utilizes H2 in-memory database for easy setup

## Features

### Database Integration
- **JPA/Hibernate**: Enterprise-grade ORM framework
- **H2 Database**: Lightweight, embedded database (file-based)
- **Two Entity Models**: KnowledgeEntry and Conversation
- **Repository Pattern**: Clean separation of data access logic

### CRUD Operations
- **Create**: Add new knowledge entries and conversations
- **Read**: Query knowledge base and conversation history
- **Update**: Modify existing knowledge entries and confidence scores
- **Delete**: Remove outdated or incorrect entries

### AI Functionality
- **Knowledge Base Search**: Pattern matching for question answering
- **Confidence Scoring**: Tracks accuracy of knowledge entries
- **Learning System**: Updates confidence scores based on usage
- **Conversation Tracking**: Maintains session-based conversation history

## Architecture

```
project2-ai-with-database/
├── src/main/java/com/example/ai/
│   ├── AIKnowledgeBaseApp.java          # Main application
│   ├── model/
│   │   ├── KnowledgeEntry.java          # Knowledge base entity
│   │   └── Conversation.java            # Conversation history entity
│   ├── repository/
│   │   ├── KnowledgeRepository.java     # CRUD for knowledge entries
│   │   └── ConversationRepository.java  # CRUD for conversations
│   └── service/
│       └── AIService.java               # AI logic and business layer
└── src/main/resources/
    └── META-INF/
        └── persistence.xml              # JPA configuration
```

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Building the Project

```bash
cd project2-ai-with-database
mvn clean compile
```

## Running the Application

```bash
mvn exec:java -Dexec.mainClass="com.example.ai.AIKnowledgeBaseApp"
```

Or build and run the JAR:

```bash
mvn clean package
java -jar target/ai-database-app-1.0.0.jar
```

## Application Menu

When you run the application, you'll see:

```
╔══════════════════════════════════════════════════════╗
║     AI Knowledge Base Application                    ║
║     With Database Integration (JPA + H2)             ║
╚══════════════════════════════════════════════════════╝

═══════════════ Main Menu ═══════════════
1. Chat with AI
2. View Knowledge Base
3. Add New Knowledge
4. View Conversation History
5. View Statistics
6. Demonstrate CRUD Operations
7. Exit
═════════════════════════════════════════
```

## Usage Examples

### 1. Chat with AI
Ask questions that match entries in the knowledge base:
```
You: What is artificial intelligence?
AI: Artificial Intelligence (AI) is the simulation of human intelligence 
    by machines, especially computer systems.

You: What is machine learning?
AI: Machine Learning is a subset of AI that enables systems to learn and 
    improve from experience without being explicitly programmed.
```

### 2. View Knowledge Base
See all stored knowledge entries with their IDs, categories, questions, answers, and confidence scores.

### 3. Add New Knowledge
Teach the AI new information:
```
Enter question: What is Spring Boot?
Enter answer: Spring Boot is a Java framework that makes it easy to create 
              stand-alone, production-grade Spring applications.
Enter category: Java Frameworks
✓ Knowledge added successfully!
```

### 4. View Conversation History
Review all past interactions with timestamps and session IDs.

### 5. View Statistics
See summary information:
```
Knowledge Base Stats:
- Total Knowledge Entries: 8
- Total Conversations: 15
- Current Session: a7b3c9d4-...
```

### 6. Demonstrate CRUD Operations
Watch automated demonstration of all CRUD operations:
- Create a new entry
- Read the entry back
- Update the entry's data
- Delete demonstration (preserved for reference)

## Database Details

### KnowledgeEntry Table
- `id`: Auto-generated primary key
- `question`: The question text (max 500 chars)
- `answer`: The answer text (max 1000 chars)
- `category`: Category classification
- `confidence_score`: Accuracy tracking (0.0 - 10.0)
- `created_at`: Creation timestamp
- `updated_at`: Last modification timestamp

### Conversation Table
- `id`: Auto-generated primary key
- `user_query`: User's question (max 1000 chars)
- `ai_response`: AI's answer (max 2000 chars)
- `timestamp`: When the conversation occurred
- `session_id`: Unique session identifier

## Database Location

The H2 database is stored as a file: `ai_knowledge_db.mv.db` in the project directory.

To reset the database, simply delete this file and restart the application.

## Learning Points

This project demonstrates:
- **JPA/Hibernate Configuration**: Setting up ORM with persistence.xml
- **Entity Mapping**: Using annotations (@Entity, @Table, @Id, etc.)
- **Repository Pattern**: Abstracting data access logic
- **Transaction Management**: Ensuring data consistency
- **JPQL Queries**: Type-safe database queries
- **Service Layer**: Business logic separation
- **Entity Relationships**: Understanding database design
- **AI Integration**: Combining AI logic with persistent storage

## Extending the Application

You can enhance this application by:
- Adding user authentication and multiple user support
- Implementing REST API endpoints
- Integrating with real LLM APIs (OpenAI, Hugging Face, etc.)
- Adding more sophisticated NLP for better query matching
- Implementing full-text search
- Adding entity relationships (e.g., tags, categories as separate entities)
- Creating a web UI using Spring Boot + Thymeleaf/React
- Adding export/import functionality for knowledge base
- Implementing backup and restore features

## Technologies Used

- **Java 17**: Programming language
- **Maven**: Build and dependency management
- **JPA 3.1**: Java Persistence API specification
- **Hibernate 6.2**: ORM implementation
- **H2 Database 2.2**: Embedded SQL database
- **Jakarta Persistence**: Modern Java EE persistence

## License

This is a sample educational project.
