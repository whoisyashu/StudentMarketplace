# 🎓 Student Market Place

A comprehensive peer-to-peer marketplace platform built specifically for students to buy and sell goods, communicate through real-time messaging, and build community trust through reviews and ratings.

<img src="https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven&logoColor=fff" alt="Maven" />
<img src="https://img.shields.io/badge/Framework-Spring%20Boot-6DB33F?logo=springboot&logoColor=fff" alt="Spring Boot" />
<img src="https://img.shields.io/badge/Database-MongoDB-13AA52?logo=mongodb&logoColor=fff" alt="MongoDB" />
<img src="https://img.shields.io/badge/Security-JWT-000000?logo=json-web-tokens&logoColor=fff" alt="JWT" />
<img src="https://img.shields.io/badge/Deployed%20on-Render-46E3B7?logo=render&logoColor=fff" alt="Render" />

## 🌐 Deployment

**Live Application**: [Student Market Place](https://studentmarketplace-pi67.onrender.com/)

<img src="./public//Screenshot 2026-04-29 181425.png" alt="Student Market Place" width="600">

---

## 📋 Table of Contents

- [About](#about)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Installation & Setup](#installation--setup)
- [API Endpoints](#api-endpoints)
- [Data Models](#data-models)
- [Authentication](#authentication)
- [Usage Examples](#usage-examples)
- [Deployment](#deployment)

---

## ℹ️ About

**Student Market Place** is a modern e-commerce platform tailored for students. It enables peer-to-peer transactions within the student community, featuring secure authentication, real-time messaging, product management, and a trust-building review system.

### Key Highlights:
- 🔐 Secure JWT-based authentication
- 💬 Real-time messaging between buyers and sellers
- 📦 Comprehensive product management (CRUD operations)
- 🔍 Advanced search and filtering capabilities
- ⭐ Review and rating system
- 📱 Responsive web interface
- 🌍 Cloud-deployed on Render with MongoDB Atlas

---

## ✨ Features

### 1. **User Management**
- User registration with email and password
- Secure login with JWT tokens
- Profile management (view and update profile)
- User information: email, name, college, contact, bio, profile picture
- User rating system based on reviews
- Verification and account status tracking

### 2. **Product Management**
- **Create**: List products with title, description, price, category, images, and condition
- **Read**: Browse all products with pagination and sorting
- **Update**: Edit product details (authorized users only)
- **Delete**: Remove products from marketplace
- **Status Tracking**: Mark products as available, sold, or pending
- **View Tracking**: Monitor product views and favorites
- **Search & Filter**: Find products by keyword, category, or price range
- **Negotiation Support**: Products can be marked as negotiable

### 3. **Product Categories**
- Books
- Gadgets & Electronics
- Furniture
- Clothing & Accessories
- Study Materials
- Sports Equipment
- And more...

### 4. **Messaging System**
- **Send Messages**: Direct messaging between users
- **Conversations**: Group messages between two users
- **Message Status**: Track read/unread messages
- **Product Context**: Attach product information to messages
- **Message Types**: Support text, images, and file attachments
- **Conversation Management**: View all user conversations with last message info

### 5. **Search & Discovery**
- Keyword search across all products
- Filter by category
- Price range filtering
- Sorting options (newest, oldest, price high-to-low, etc.)
- Pagination support for large result sets

### 6. **Review & Rating System**
- Leave reviews with ratings (1-5 stars)
- Product-based or seller-based reviews
- Comment support for detailed feedback
- Automatic calculation of average seller ratings
- Trust building through community feedback

---

## 🛠 Tech Stack

### Backend
- **Framework**: Spring Boot 4.0.6
- **Language**: Java 17
- **Build Tool**: Maven
- **Database**: MongoDB (Atlas Cloud)
- **Security**: JWT (JSON Web Tokens), BCrypt Password Encoding
- **ORM**: Spring Data MongoDB

### Frontend
- **HTML5** - Markup
- **CSS3** - Styling
- **JavaScript (Vanilla)** - Client-side logic
- **REST API Communication** - Fetch/AJAX

### Deployment
- **Platform**: Render
- **Database Hosting**: MongoDB Atlas
- **Container**: Docker

### Additional Libraries
- **Lombok** - Code generation (getters, setters, builders)
- **Spring Security** - Authentication & Authorization
- **Spring Validation** - Input validation
- **CORS** - Cross-Origin Resource Sharing

---

## 📁 Project Structure

```
StudentMarketplace/
│
├── src/
│   ├── main/
│   │   ├── java/com/example/studentmarketplace/
│   │   │   ├── StudentMarketPlaceApplication.java      # Main Spring Boot class
│   │   │   │
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java                 # JWT & Security configuration
│   │   │   │   └── JwtAuthenticationFilter.java        # JWT token validation filter
│   │   │   │
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java                 # Auth endpoints (register, login, verify)
│   │   │   │   ├── ProductController.java              # Product CRUD operations
│   │   │   │   ├── MessageController.java              # Messaging endpoints
│   │   │   │   └── UserProfileController.java          # User profile endpoints
│   │   │   │
│   │   │   ├── service/
│   │   │   │   ├── UserService.java                    # User business logic
│   │   │   │   ├── ProductService.java                 # Product business logic
│   │   │   │   └── MessageService.java                 # Messaging business logic
│   │   │   │
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java                 # User database operations
│   │   │   │   ├── ProductRepository.java              # Product database operations
│   │   │   │   ├── MessageRepository.java              # Message database operations
│   │   │   │   ├── ConversationRepository.java         # Conversation management
│   │   │   │   └── ReviewRepository.java               # Review management
│   │   │   │
│   │   │   ├── domain/
│   │   │   │   ├── User.java                           # User entity
│   │   │   │   ├── Product.java                        # Product entity
│   │   │   │   ├── Message.java                        # Message entity
│   │   │   │   ├── Conversation.java                   # Conversation entity
│   │   │   │   └── Review.java                         # Review entity
│   │   │   │
│   │   │   ├── dto/
│   │   │   │   ├── AuthResponse.java                   # Auth response DTO
│   │   │   │   ├── LoginRequest.java                   # Login request DTO
│   │   │   │   ├── RegisterRequest.java                # Registration request DTO
│   │   │   │   ├── ProductRequest.java                 # Product request DTO
│   │   │   │   ├── ProductResponse.java                # Product response DTO
│   │   │   │   ├── MessageRequest.java                 # Message request DTO
│   │   │   │   └── MessageResponse.java                # Message response DTO
│   │   │   │
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java         # Global error handling
│   │   │   │   ├── ResourceNotFoundException.java      # 404 exception
│   │   │   │   └── UnauthorizedException.java          # 401 exception
│   │   │   │
│   │   │   └── util/
│   │   │       └── [Utility classes]
│   │   │
│   │   └── resources/
│   │       ├── application.properties                  # App configuration
│   │       └── static/
│   │           ├── index.html                          # Home page
│   │           ├── css/
│   │           │   ├── style.css                       # Main styles
│   │           │   └── style.css.backup
│   │           ├── js/
│   │           │   └── api.js                          # API client functions
│   │           └── pages/
│   │               ├── login.html                      # Login page
│   │               ├── register.html                   # Registration page
│   │               ├── products.html                   # Products listing
│   │               ├── product-detail.html             # Product details
│   │               ├── add-product.html                # Add product form
│   │               ├── profile.html                    # User profile
│   │               └── chat.html                       # Messaging interface
│   │
│   └── test/
│       └── [Test classes]
│
├── pom.xml                                             # Maven dependencies
├── Dockerfile                                          # Docker configuration
├── mvnw & mvnw.cmd                                    # Maven wrapper
└── README.md                                          # This file
```

---

## ⚙️ Installation & Setup

### Prerequisites
- Java 17+
- Maven 3.6+
- MongoDB (local or MongoDB Atlas account)
- Git
- Node.js (optional, for frontend development)

### Step 1: Clone the Repository
```bash
git clone https://github.com/whoisyashu/StudentMarketplace.git
cd StudentMarketplace
```

### Step 2: Configure Environment Variables
Update `src/main/resources/application.properties`:

```properties
spring.application.name=StudentMarketPlace

# MongoDB Configuration
spring.data.mongodb.uri=mongodb+srv://YOUR_USERNAME:YOUR_PASSWORD@YOUR_CLUSTER.mongodb.net/YOUR_DATABASE

# JWT Configuration
jwt.secret=your-secret-key-at-least-32-bytes-long
jwt.expiration=86400000  # 24 hours in milliseconds
```

### Step 3: Build the Project
```bash
# Using Maven
mvn clean install

# Or using Maven wrapper (Windows)
mvnw clean install

# Or using Maven wrapper (Linux/Mac)
./mvnw clean install
```

### Step 4: Run the Application
```bash
# Using Maven
mvn spring-boot:run

# Or using Maven wrapper
mvnw spring-boot:run

# Application will start at http://localhost:8080
```

### Step 5: Access the Application
- **Frontend**: Open browser and navigate to `http://localhost:8080`
- **API Documentation**: Swagger/OpenAPI endpoints at `http://localhost:8080/api/**`

---

## 🔌 API Endpoints

### **1. Authentication Endpoints** (`/api/auth`)

#### Register User
```
POST /api/auth/register
Content-Type: application/json

{
  "email": "student@example.com",
  "password": "securePassword123",
  "firstName": "John",
  "lastName": "Doe",
  "college": "Your College"
}

Response: 200 OK
{
  "success": true,
  "message": "User registered successfully",
  "token": "jwt_token_here",
  "userId": "user_id"
}
```

#### Login
```
POST /api/auth/login
Content-Type: application/json

{
  "email": "student@example.com",
  "password": "securePassword123"
}

Response: 200 OK
{
  "success": true,
  "message": "Login successful",
  "token": "jwt_token_here",
  "userId": "user_id"
}
```

#### Verify Token
```
GET /api/auth/verify
Authorization: Bearer <jwt_token>

Response: 200 OK
"Token is valid"
```

---

### **2. Product Endpoints** (`/api/products`)

#### Create Product
```
POST /api/products
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "title": "Used Laptop",
  "description": "Dell XPS 15, excellent condition",
  "price": 45000,
  "category": "gadgets",
  "imageUrls": ["url1", "url2"],
  "condition": "like-new",
  "location": "Campus A",
  "negotiable": true
}

Response: 200 OK
{
  "id": "product_id",
  "title": "Used Laptop",
  "price": 45000,
  "status": "available",
  "createdAt": "2026-04-30T10:00:00"
}
```

#### Get All Products (with Pagination & Sorting)
```
GET /api/products?page=0&size=10&sortBy=createdAt&direction=DESC
Authorization: Bearer <jwt_token>

Response: 200 OK
{
  "content": [
    {
      "id": "product_id",
      "title": "Used Laptop",
      "price": 45000,
      "category": "gadgets",
      "status": "available"
    }
  ],
  "totalElements": 150,
  "totalPages": 15,
  "currentPage": 0
}
```

#### Get Single Product
```
GET /api/products/{productId}
Authorization: Bearer <jwt_token>

Response: 200 OK
{
  "id": "product_id",
  "title": "Used Laptop",
  "description": "...",
  "price": 45000,
  "sellerId": "seller_id",
  "category": "gadgets",
  "condition": "like-new",
  "status": "available",
  "views": 150,
  "favorites": 25
}
```

#### Search Products by Keyword
```
GET /api/products/search?keyword=laptop&page=0&size=10
Authorization: Bearer <jwt_token>

Response: 200 OK
[List of matching products with pagination]
```

#### Filter by Category
```
GET /api/products/category/{category}?page=0&size=10
Authorization: Bearer <jwt_token>

Valid Categories: books, gadgets, electronics, furniture, etc.

Response: 200 OK
[Products in the specified category]
```

#### Filter by Price Range
```
GET /api/products/price-range?minPrice=10000&maxPrice=50000&page=0&size=10
Authorization: Bearer <jwt_token>

Response: 200 OK
[Products within the specified price range]
```

#### Get Products by Seller
```
GET /api/products/seller/{sellerId}
Authorization: Bearer <jwt_token>

Response: 200 OK
[All products listed by the specified seller]
```

#### Update Product
```
PUT /api/products/{productId}
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "title": "Updated Title",
  "price": 40000,
  "description": "Updated description",
  ...
}

Response: 200 OK
[Updated product details]
```

#### Mark Product as Sold
```
PUT /api/products/{productId}/sold
Authorization: Bearer <jwt_token>

Response: 200 OK
"Product marked as sold"
```

#### Delete Product
```
DELETE /api/products/{productId}
Authorization: Bearer <jwt_token>

Response: 200 OK
"Product deleted successfully"
```

---

### **3. User Profile Endpoints** (`/api/users`)

#### Get Current User Profile
```
GET /api/users/profile
Authorization: Bearer <jwt_token>

Response: 200 OK
{
  "id": "user_id",
  "email": "student@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "college": "Your College",
  "contactNumber": "+91XXXXXXXXXX",
  "bio": "Student | Book Lover",
  "profilePictureUrl": "url",
  "averageRating": 4.5,
  "totalReviews": 12,
  "isVerified": true,
  "isActive": true
}
```

#### Get User by ID
```
GET /api/users/{userId}
Authorization: Bearer <jwt_token>

Response: 200 OK
[User information]
```

#### Update User Profile
```
PUT /api/users/profile
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "college": "New College",
  "contactNumber": "+91XXXXXXXXXX",
  "bio": "Updated bio",
  "profilePictureUrl": "new_url"
}

Response: 200 OK
[Updated user information]
```

---

### **4. Messaging Endpoints** (`/api/messages`)

#### Send Message
```
POST /api/messages/send
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "receiverId": "recipient_user_id",
  "content": "Hi, are you still selling this?",
  "productId": "optional_product_id",
  "messageType": "text"
}

Response: 200 OK
{
  "id": "message_id",
  "senderId": "your_user_id",
  "receiverId": "recipient_user_id",
  "content": "Hi, are you still selling this?",
  "sentAt": "2026-04-30T10:30:00",
  "read": false,
  "productId": "product_id"
}
```

#### Get Conversation Messages
```
GET /api/messages/conversation/{conversationId}
Authorization: Bearer <jwt_token>

Response: 200 OK
[
  {
    "id": "message_id",
    "senderId": "...",
    "content": "...",
    "sentAt": "...",
    "read": true
  },
  ...
]
```

#### Get User's Conversations
```
GET /api/messages/conversations
Authorization: Bearer <jwt_token>

Response: 200 OK
[
  {
    "id": "conversation_id",
    "userId1": "...",
    "userId2": "...",
    "lastMessage": "Last message text",
    "lastMessageAt": "2026-04-30T10:30:00",
    "user1Unread": false,
    "user2Unread": true
  },
  ...
]
```

#### Mark Message as Read
```
PUT /api/messages/{messageId}/read
Authorization: Bearer <jwt_token>

Response: 200 OK
"Message marked as read"
```

---

## 🗄️ Data Models

### **User**
```java
{
  id: String (MongoDB ObjectId),
  email: String (unique),
  password: String (BCrypt encrypted),
  firstName: String,
  lastName: String,
  college: String,
  contactNumber: String,
  bio: String,
  profilePictureUrl: String,
  averageRating: Double,
  totalReviews: Integer,
  isVerified: Boolean,
  isActive: Boolean,
  createdAt: LocalDateTime,
  updatedAt: LocalDateTime
}
```

### **Product**
```java
{
  id: String (MongoDB ObjectId),
  sellerId: String (Reference to User),
  title: String,
  description: String,
  price: Double,
  category: String,
  imageUrls: List<String>,
  condition: String (new, like-new, used),
  location: String,
  status: String (available, sold, pending),
  views: Integer,
  favorites: Integer,
  negotiable: Boolean,
  tags: String,
  createdAt: LocalDateTime,
  updatedAt: LocalDateTime,
  soldAt: LocalDateTime
}
```

### **Message**
```java
{
  id: String (MongoDB ObjectId),
  senderId: String (Reference to User),
  receiverId: String (Reference to User),
  conversationId: String,
  content: String,
  messageType: String (text, image, file),
  productId: String (Optional, Reference to Product),
  read: Boolean,
  sentAt: LocalDateTime,
  readAt: LocalDateTime
}
```

### **Conversation**
```java
{
  id: String (MongoDB ObjectId),
  userId1: String (Reference to User),
  userId2: String (Reference to User),
  lastMessageId: String,
  lastMessage: String,
  lastMessageAt: LocalDateTime,
  user1Unread: Boolean,
  user2Unread: Boolean,
  createdAt: LocalDateTime,
  updatedAt: LocalDateTime
}
```

### **Review**
```java
{
  id: String (MongoDB ObjectId),
  reviewerId: String (Reference to User),
  revieweeId: String (Reference to User),
  productId: String (Optional, Reference to Product),
  rating: Integer (1-5),
  comment: String,
  createdAt: LocalDateTime,
  updatedAt: LocalDateTime
}
```

---

## 🔐 Authentication

The application uses **JWT (JSON Web Tokens)** for secure authentication:

### How It Works:
1. User registers or logs in
2. Server validates credentials using BCrypt
3. JWT token is generated with user ID as principal
4. Client sends token in `Authorization: Bearer <token>` header
5. `JwtAuthenticationFilter` validates token on protected endpoints
6. Request proceeds if token is valid

### Token Structure:
- **Type**: Bearer Token
- **Format**: `Authorization: Bearer <token>`
- **Expiration**: 24 hours (configurable)
- **Secret**: Configured in `application.properties`

### Protected Endpoints:
- All `/api/products/**` endpoints (except search/browse)
- All `/api/users/**` endpoints
- All `/api/messages/**` endpoints

### Public Endpoints:
- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/auth/verify`
- Static resources (HTML, CSS, JS)

---

## 💡 Usage Examples

### Example 1: Complete User Flow (Register → List Product → Message)

**Step 1: Register**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "Pass123",
    "firstName": "John",
    "lastName": "Doe",
    "college": "XYZ University"
  }'
```

**Step 2: Login**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "Pass123"
  }'
# Save the JWT token from response
```

**Step 3: Create Product**
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{
    "title": "Python Textbook",
    "description": "Like new condition, used for 1 semester",
    "price": 500,
    "category": "books",
    "condition": "like-new",
    "location": "Library Zone"
  }'
```

**Step 4: Send Message**
```bash
curl -X POST http://localhost:8080/api/messages/send \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{
    "receiverId": "buyer_user_id",
    "content": "Are you interested in my Python book?",
    "productId": "product_id"
  }'
```

---

## 📦 Deployment

### Deployment to Render

The application is currently deployed on **Render** using Docker:

1. **Docker Support**: 
   - Dockerfile is included in the repository
   - Automatically builds and deploys on push to main branch

2. **Environment Variables** (Set in Render Dashboard):
   ```
   MONGODB_URI=your-mongodb-atlas-uri
   JWT_SECRET=your-jwt-secret
   SPRING_PROFILES_ACTIVE=production
   ```

3. **Live URL**: [Student Market Place](https://studentmarketplace-pi67.onrender.com/)

### Local Docker Build
```bash
docker build -t studentmarketplace:latest .
docker run -p 8080:8080 \
  -e MONGODB_URI="your-mongodb-uri" \
  -e JWT_SECRET="your-secret" \
  studentmarketplace:latest
```

---

## 🤝 Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Make changes and commit (`git commit -m 'Add YourFeature'`)
4. Push to branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

---

## 📝 Future Enhancements

- [ ] Real-time messaging with WebSocket
- [ ] Payment gateway integration
- [ ] Wishlist/Favorites feature
- [ ] Advanced analytics for sellers
- [ ] Mobile app (React Native/Flutter)
- [ ] AI-based product recommendations
- [ ] Email verification and notifications
- [ ] OAuth2 social login (Google, Facebook)

---

## 📧 Contact & Support


**GitHub**: [whoisyashu](https://github.com/whoisyashu)  
**Project Repository**: [StudentMarketplace](https://github.com/whoisyashu/StudentMarketplace)  
**Live Deployment**: [studentmarketplace-pi67.onrender.com](https://studentmarketplace-pi67.onrender.com/)

For issues, questions, or suggestions, please open an issue on GitHub or contact via email.

---



---

**Last Updated**: April 30, 2026  
**Version**: 0.0.1
