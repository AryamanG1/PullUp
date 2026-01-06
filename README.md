# PullUp 
PullUp is an educational backend prototype inspired by Uber’s large scale event driven architecture.  
It explores real system design concepts such as domain driven design, state machines, Kafka based coordination, Redis geospatial indexing and eventual consistency while deliberately avoiding CRUD centric or synchronous designs.


## Disclaimer

Disclaimer PullUp is an educational prototype inspired by Uber’s backend architecture.  
It is not affiliated with endorsed by or representative of Uber Technologies Inc.  
All trademarks names and references to Uber belong to their respective owners.


## Project Gist

This project models a ride hailing backend where rides and drivers are independent state machines and services communicate only through events. Redis is used strictly as a derived performance index and Kafka acts as the coordination backbone.

The goal is not feature completeness but architectural correctness and learning through real failure modes.


## Motivation

PullUp intentionally avoids synchronous REST calls between services shared databases and CRUD driven domain models.

This project was built to  
- Model business workflows using explicit state machines  
- Use Kafka for coordination not RPC  
- Treat Redis as derived state never a source of truth  
- Embrace eventual consistency and failure aware design  
- Experience and document real distributed systems pitfalls


## High Level Architecture

The system consists of independently owned microservices each with clear responsibilities

### RiderService
Owns the ride lifecycle and ride state machine

### DriverService
Owns driver lifecycle availability and location

### MatchingService
Stateless coordination service responsible for driver selection

### Apache Kafka
Event backbone for all inter service communication

### Redis
Geospatial index of available drivers derived state only

- All communication is asynchronous.  
- No service makes synchronous calls to another service.  
- No databases are shared.


## Core Event Flow

1. Rider creates a ride publishes ride.requested  
2. MatchingService queries Redis GEO index for nearby drivers  
3. MatchingService publishes driver.assignment.proposed  
4. DriverService accepts or rejects based on driver state  
5. RiderService updates ride state upon acceptance  

### Sequence Diagram
![Ride Assignment Sequence Diagram](UML_Diagrams/SequenceDiagram.png)

---

## State Machines

### Ride State Machine
REQUESTED → SEARCHING_DRIVER → ASSIGNED → IN_PROGRESS → COMPLETED or CANCELLED

### Driver State Machine
OFFLINE → AVAILABLE → BUSY → AVAILABLE

State transitions are enforced via intent based methods.  
Entities expose no public setters ensuring domain invariants remain intact.

### Ride State Machine Diagram
![Ride State Machine Diagram](UML_Diagrams/StateMachineDiagram.png)

---

## Domain Models and DTOs

The domain model is explicitly separated from external facing DTOs to prevent leakage of internal state and invariants.

### Rider Service

#### Entity Model
![Rider Service Entities](UML_Diagrams/RiderServiceEntities.png)

---

#### DTO Model
![Rider Service DTOs](UML_Diagrams/RiderServiceDtoDiagram.png)

---

### Driver Service

#### Entity Model
![Driver Service Entities](UML_Diagrams/DriverServiceEntities.png)

---
#### DTO Model
![Driver Service DTOs](UML_Diagrams/DriverServiceDtoDiagram.png)

---

## Service Interaction View

A swimlane diagram is used to visualize responsibility boundaries across services and the flow of events between them.

### Swimlane Diagram
![Swimlane Diagram](UML_Diagrams/SwimlaneDiagram.png)

---

## Redis Design

Redis is used strictly as a derived non authoritative data store.

- Key drivers:available  
- Type GEO sorted set  
- Updated via events driver.location.updated and driver.status.changed  
- Queried by MatchingService only  

Redis never owns correctness it enables fast lookup not enforcement.

## Kafka and Event Design

- Explicit JSON contracts  
- Events contain only facts known at emit time  
- UUIDs used consistently  
- At least once delivery semantics  
- No distributed transactions  
- No shared event modules across services  

Kafka is treated as a coordination log not a message queue.

## Technology Stack

### Core Backend Technologies

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring Kafka](https://img.shields.io/badge/Spring%20Kafka-6DB33F?style=for-the-badge&logo=apache-kafka&logoColor=white)
![Spring Data Redis](https://img.shields.io/badge/Spring%20Data%20Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)


### Messaging and Streaming

![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-000000?style=for-the-badge&logo=apache-kafka&logoColor=white)


### Databases and Storage

![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)

### Containerization and Runtime

![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

### Architecture and Design

![Event Driven Architecture](https://img.shields.io/badge/Event%20Driven%20Architecture-000000?style=for-the-badge)
![Explicit State Machines](https://img.shields.io/badge/Explicit%20State%20Machines-FF6F00?style=for-the-badge)
![Eventually Consistent Workflows](https://img.shields.io/badge/Eventually%20Consistent%20Workflows-607D8B?style=for-the-badge)


## Known Issues and Learnings

This project intentionally documents real development pitfalls.

- Early messages were produced with Kafka type headers which caused deserialization issues during contract evolution  
- Kafka message immutability requires topic hygiene and versioning  
- Redis derived state can temporarily diverge from database state  
- Consumer group offsets strongly affect replay behavior  

In a production system these would be addressed using topic versioning DLQs retention policies and schema governance.


## Current Debugging Work

- Cleaning historical Kafka messages with legacy type headers  
- Improving consumer error isolation  

## Future Enhancements

- WebSocket server for real time ride and driver heartbeat delivery  
- QUIC and HTTP 3 exploration for low latency UDP based communication  

- Observability : Prometheus metrics , Grafana dashboards , Kafka consumer lag monitoring  

- Infrastructure : Kubernetes orchestration , Spring Cloud features , Centralized configuration management  

These features can be layered without changing core domain logic.

## Final Note

PullUp is intentionally incomplete.

Its value lies not in feature parity but in architectural decisions explicit trade offs and lessons learned while building real distributed systems.
