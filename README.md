# TestJavaAiProjects

This repository contains two example Java AI projects demonstrating different levels of AI application development in Java.

## Projects Overview

### Project 1: Simple AI Chatbot
**Location:** `project1-simple-ai-chatbot/`

A beginner-friendly Java AI application demonstrating basic AI logic using rule-based pattern matching.

**Features:**
- Rule-based conversational AI
- Pattern matching for intent recognition
- Multiple response variations
- Interactive console interface
- No external dependencies (pure Java)

**Quick Start:**
```bash
cd project1-simple-ai-chatbot
mvn clean compile
mvn exec:java -Dexec.mainClass="com.example.ai.SimpleChatbot"
```

[Full Documentation](project1-simple-ai-chatbot/README.md)

---

### Project 2: AI Knowledge Base with Database Integration
**Location:** `project2-ai-with-database/`

A comprehensive Java AI application with database integration, demonstrating CRUD operations and persistent storage.

**Features:**
- JPA/Hibernate ORM integration
- H2 embedded database
- Full CRUD operations on knowledge base
- AI-powered question answering
- Conversation history tracking
- Confidence scoring system
- Interactive menu-driven interface

**Quick Start:**
```bash
cd project2-ai-with-database
mvn clean package
java -jar target/ai-database-app-with-dependencies.jar
```

[Full Documentation](project2-ai-with-database/README.md)

---

## Prerequisites

- **Java 17** or higher
- **Maven 3.6** or higher

## Project Comparison

| Feature | Project 1 | Project 2 |
|---------|-----------|-----------|
| **Complexity** | Beginner | Intermediate |
| **Database** | None | H2 (JPA/Hibernate) |
| **Persistence** | In-memory | Database storage |
| **Dependencies** | None | Hibernate, H2 |
| **CRUD Operations** | No | Yes |
| **AI Approach** | Pattern matching | Pattern matching + Learning |
| **Use Case** | Learning AI basics | Production-ready AI system |

## Learning Path

1. **Start with Project 1** if you're new to AI in Java
   - Understand basic pattern matching
   - Learn conversational AI concepts
   - No database complexity

2. **Move to Project 2** to learn database integration
   - JPA/Hibernate configuration
   - Entity modeling
   - Repository pattern
   - Transaction management
   - CRUD operations

## Technologies Used

### Project 1
- Java 17
- Maven

### Project 2
- Java 17
- Maven
- JPA 3.1
- Hibernate 6.2
- H2 Database 2.2
- Jakarta Persistence API

## Future Enhancements

Both projects can be extended with:
- Integration with real LLM APIs (OpenAI, Hugging Face, Ollama)
- REST API endpoints using Spring Boot
- Web UI using React or Thymeleaf
- Advanced NLP techniques
- Machine learning integration
- User authentication
- Multi-language support

## Contributing

These are educational sample projects. Feel free to fork and extend them for your learning purposes.

## License

This is a sample educational project.
