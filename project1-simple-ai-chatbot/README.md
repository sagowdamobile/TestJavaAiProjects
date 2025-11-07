# Project 1: Simple AI Chatbot

A beginner-friendly Java AI application demonstrating basic AI logic using a rule-based pattern matching system.

## Overview

This project implements a simple conversational AI chatbot that uses pattern matching and predefined responses to simulate intelligent conversation. It's perfect for beginners learning about AI concepts in Java.

## Features

- **Rule-based AI Logic**: Uses pattern matching to identify user intent
- **Multiple Response Variations**: Provides varied responses to make conversations feel more natural
- **Interactive Console Interface**: Simple command-line chat interface
- **Topic Coverage**: Handles greetings, questions about itself, time queries, jokes, and more

## How It Works

The chatbot uses a knowledge base implemented as a HashMap where:
- **Keys**: Patterns (using `|` to separate multiple keywords)
- **Values**: Arrays of possible responses

When a user types a message, the chatbot:
1. Converts the input to lowercase
2. Searches for matching patterns in the knowledge base
3. Selects a random response from the matching pattern's response array
4. Falls back to default responses if no pattern matches

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Building the Project

```bash
cd project1-simple-ai-chatbot
mvn clean compile
```

## Running the Chatbot

```bash
mvn exec:java -Dexec.mainClass="com.example.ai.SimpleChatbot"
```

Or build and run the JAR:

```bash
mvn clean package
java -jar target/simple-ai-chatbot-1.0.0.jar
```

## Example Conversation

```
╔════════════════════════════════════════════╗
║   Welcome to Simple AI Chatbot!           ║
║   Type 'exit' or 'quit' to end chat       ║
╚════════════════════════════════════════════╝

You: Hello!
Bot: Hi there! What can I do for you?

You: Who are you?
Bot: I'm a simple AI chatbot created to demonstrate basic AI logic!

You: Tell me a joke
Bot: Why do Java developers wear glasses? Because they don't C#! 😂

You: What's the time?
Bot: The current time is: Thu Nov 07 04:30:00 UTC 2025

You: bye
Bot: Goodbye! Have a great day!
```

## Learning Points

This project demonstrates:
- **Pattern Matching**: Basic NLP technique for intent recognition
- **Knowledge Representation**: Using data structures to store AI knowledge
- **Randomization**: Adding variety to responses
- **User Input Handling**: Processing and responding to user queries
- **Object-Oriented Design**: Structuring an AI application

## Extending the Chatbot

You can enhance this chatbot by:
- Adding more patterns and responses to the knowledge base
- Implementing context awareness to remember previous messages
- Adding sentiment analysis
- Integrating with external APIs for real data (weather, news, etc.)
- Implementing machine learning for better pattern recognition

## License

This is a sample educational project.
