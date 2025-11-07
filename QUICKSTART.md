# Quick Start Guide

This guide will help you get started with the Java AI projects in this repository.

## Prerequisites

Before running these projects, ensure you have:

- **Java Development Kit (JDK) 17** or higher installed
  - Check with: `java -version`
  - Download from: [https://adoptium.net/](https://adoptium.net/)

- **Apache Maven 3.6** or higher installed
  - Check with: `mvn -version`
  - Download from: [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)

## Project 1: Simple AI Chatbot

### What it does
A beginner-friendly conversational AI that uses pattern matching to respond to user queries.

### How to run

1. Navigate to the project directory:
   ```bash
   cd project1-simple-ai-chatbot
   ```

2. Compile the project:
   ```bash
   mvn clean compile
   ```

3. Run the chatbot:
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.ai.SimpleChatbot"
   ```

4. Or build a JAR and run it:
   ```bash
   mvn clean package
   java -jar target/simple-ai-chatbot-1.0.0.jar
   ```

### Try these commands
- `hello` - Greet the chatbot
- `who are you` - Ask about the chatbot
- `tell me a joke` - Get a programming joke
- `what time` - Get the current time
- `bye` - Exit the chat

---

## Project 2: AI Knowledge Base Application

### What it does
A more advanced AI system that stores knowledge in a database and learns from conversations.

### How to run

1. Navigate to the project directory:
   ```bash
   cd project2-ai-with-database
   ```

2. Build the project with dependencies:
   ```bash
   mvn clean package
   ```

3. Run the application:
   ```bash
   java -jar target/ai-database-app-with-dependencies.jar
   ```

### Menu Options

When you run the application, you'll see a menu with these options:

1. **Chat with AI** - Have a conversation with the AI
2. **View Knowledge Base** - See all stored knowledge entries
3. **Add New Knowledge** - Teach the AI something new
4. **View Conversation History** - See past conversations
5. **View Statistics** - Check system stats
6. **Demonstrate CRUD Operations** - See database operations in action
7. **Exit** - Close the application

### Try these questions in chat mode
- `What is artificial intelligence?`
- `What is machine learning?`
- `What is Java?`
- `What is a database?`
- `How are you?`

---

## Troubleshooting

### "java: command not found"
- Java is not installed or not in your PATH
- Install Java 17 or higher from [Adoptium](https://adoptium.net/)

### "mvn: command not found"
- Maven is not installed or not in your PATH
- Install Maven from [Apache Maven](https://maven.apache.org/download.cgi)

### Project 2 database issues
- The database file (`ai_knowledge_db.mv.db`) may be corrupted
- Solution: Delete the file and restart the application
  ```bash
  rm ai_knowledge_db.mv.db
  ```

### Build fails
- Ensure you have Java 17 or higher
- Clear Maven cache and rebuild:
  ```bash
  mvn clean install -U
  ```

---

## Next Steps

### For beginners
1. Start with **Project 1** to understand basic AI concepts
2. Modify the pattern matching rules in `SimpleChatbot.java`
3. Add new response patterns and categories

### For intermediate developers
1. Move to **Project 2** to learn database integration
2. Add new knowledge entries through the application
3. Explore the database using H2 console
4. Modify the JPA entities to add new fields

### Advanced topics to explore
- Integrate with OpenAI API or local LLM (Ollama)
- Add REST API endpoints using Spring Boot
- Create a web UI with React or Angular
- Implement more sophisticated NLP techniques
- Add user authentication and authorization
- Deploy to cloud platforms (AWS, Azure, GCP)

---

## Additional Resources

- **Java Documentation**: [https://docs.oracle.com/en/java/](https://docs.oracle.com/en/java/)
- **JPA Tutorial**: [https://docs.oracle.com/javaee/7/tutorial/partpersist.htm](https://docs.oracle.com/javaee/7/tutorial/partpersist.htm)
- **Hibernate Guide**: [https://hibernate.org/orm/documentation/](https://hibernate.org/orm/documentation/)
- **Maven Guide**: [https://maven.apache.org/guides/](https://maven.apache.org/guides/)

---

## Support

For issues or questions about these projects:
1. Check the individual project README files
2. Review the troubleshooting section above
3. Open an issue on GitHub

Happy coding! 🚀
