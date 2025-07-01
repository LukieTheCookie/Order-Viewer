# Order Viewer

A lightweight web application to browse and filter e-commerce orders, built with **Spring Boot** (backend) and **Angular** (frontend).

---

## Overview

The Order Viewer app allows users to:

- View a list of orders with key information (Customer, Status, Total, Created Date)  
- Filter orders by date range, status, and cost   
- View order details including line items, quantities, and prices and mark as paid  
- See live statistics on filtered orders (count and grand total)  

---

## Tech Stack

- **Backend:** Spring Boot, Spring Data JPA, Hibernate, MySQL  
- **Frontend:** Angular, Angular Material 
- **Build Tools:** Maven, Node.js/NPM  
- **Testing:** JUnit (backend) and Mockito
- **Postman:** API Testing

---

## Features

- Responsive UI  
- Asynchronous RESTful API with filtering capabilities  
- Database seeding
- Live statistics widget with real-time updates  
- Efficient backend filtering using JPA Specifications  
- Single repository hosting both frontend and backend code

---

### Design Decisions

- Filtering in Backend: Uses JPA Specifications for efficient DB queries rather than client-side filtering.

- Single Repository: Frontend and backend code managed together for simplicity.

- Order & OrderItem Relationship: One-to-many bidirectional mapping for easy data access and persistence.

- CORS Configuration: Allows frontend requests during development.

- Asynchronous Updates: Live statistics and paid status updates via REST and Angular reactive services.

---

## Setup Instructions

### Prerequisites

- Java 17+  
- Maven 3.6+  
- Node.js 14+ and npm  
- MySQL
- Postman : "./OrderViewer..."



### Backend

- Navigate to backend/src/main/resources
    - update db connection (if neccessary)
- cd backend
    -  ./mvnw clean install
    - ./mvnw spring-boot:run
 
### Frontend

- cd frontend
    - npm install
    - npm start
 
### Running the Application

- Backend runs on http://localhost:8080
- Frontend runs on http://localhost:4200
- Ensure backend is running before starting frontend for full functionality



### Open Items

- Mobile style
- Frontend Tests
