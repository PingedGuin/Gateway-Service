ChatApp Server

This is a backend server for a real-time chat application, built using Spring Boot. It handles user authentication, session management, and real-time messaging.

Features
User Management
Register and login with email and password
Passwords are hashed securely
Update and fetch user information
User sessions stored and cached for performance
Authentication & Security
JWT-based authentication
Stateless sessions
Token validation with expiration
Spring Security integration
Real-Time Messaging
WebSocket support for real-time communication
Simple in-memory message broker (STOMP over WebSocket)
Designed to scale to multiple channels or topics
Caching
Caching user sessions and user info using Caffeine Cache
Reduces database load
Database
Relational database (e.g., PostgreSQL, MySQL, H2 for dev)
Uses Spring Data JPA
Entities mapped to DTOs for secure API responses
Project Structure
com.app
├── user
│   ├── controller       # REST endpoints for user operations
│   ├── service          # Business logic
│   ├── repository       # JPA repositories
│   └── data
│       ├── entity       # Database entities
│       └── dto          # DTOs for API responses
├── service
│   ├── security
│   │   ├── auth         # JWT & authentication services
│   │   ├── config       # Security configuration (Spring Security)
│   │   └── Interceptor  # Optional interceptors (deprecated)
│   └── cache            # Caching configuration
└── ChatAppServerApplication.java
API Endpoints
User Authentication
Method	Endpoint	Description
POST	/api/auth/register	Register a new user
POST	/api/auth/login	Login and receive JWT token
GET	/api/user/@me/info	Get current user info
PATCH	/api/user/@me/updateInfo	Update user info
DELETE	/api/user/@me	Delete current user
WebSocket Messaging
Endpoint: /ws
Topics: /topic/messages, /queue/private
Uses STOMP protocol
Setup Instructions

Clone the repository:

git clone https://github.com/PingedGuin/Gateway-Service

Configure database in application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/chatapp
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

Build and run:

./gradlew bootRun
Access APIs at http://localhost:8080/api/
WebSocket server at ws://localhost:8080/ws
Notes
For development, WebSocket uses in-memory broker. Production can switch to STOMP broker relay with ActiveMQ or RabbitMQ.
JWT secret key should be changed and stored securely (e.g., environment variable).
Caching reduces database load but should be tuned based on server requirements.
