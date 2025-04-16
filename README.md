# Energy Management Web Application

A distributed web application for monitoring and managing energy consumption of smart devices, built using a microservices architecture with real-time communication features.


## Overview

The system is designed for both administrators and clients. It allows:
- User registration and authentication
- Device registration, management, and monitoring
- Real-time energy data simulation
- Real-time alerts and admin-client chat via WebSocket


## Tech Stack


### Backend
- **Spring Boot** (REST APIs, JWT auth, microservices)
- **RabbitMQ** (message broker between services)
- **PostgreSQL** (database for users, devices, and monitoring)
- **Docker** (multi-container deployment)

### Frontend
- **Angular** (modular web UI)
- **WebSocket** (chat & live notifications)
- **STOMP + SockJS** (real-time messaging)

---

## System Architecture

- **Microservices:**
  - `User Service` – user auth, roles (CLIENT/ADMIN)
  - `Device Service` – device CRUD linked to users
  - `Monitoring Service` – energy data via sensors
  - `Chat Service` – admin-user real-time chat

- **Sensor Simulation App:**
  - Sends measurement data via RabbitMQ queues

- **Communication:**
  - Services communicate via RabbitMQ (AMQP)
  - WebSocket used for notifications and chat

---

## Security

- JWT-based authentication with role checks
- Spring Security configured in the User Service
- Token shared across services for validation

---

## Database Schemas

- **Users:** `username`, `name`, `password`, `role`
- **Devices:** `name`, `description`, `address`, `clientId`
- **Monitoring:** 
  - `Device`: `id`, `clientId`, `maxConsumption`
  - `Sensor`: `deviceId`, `timestamp`, `value`, `totalConsumption`

---

