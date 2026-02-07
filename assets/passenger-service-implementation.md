# Passenger Service Implementation

## Overview

The Passenger Service is a bounded context for managing passenger information in the booking microservices system. It follows the **Vertical Slice Architecture**, **CQRS pattern**, and **Domain-Driven Design (DDD)** principles.

## Architecture

```
src/services/passenger/
├── src/main/java/com/collicode/passenger/
│   ├── data/
│   │   ├── jpa/
│   │   │   ├── entities/          # JPA entities for write side
│   │   │   ├── repositories/      # JPA repositories for PostgreSQL
│   │   │   └── seeds/             # Data seeders
│   │   └── mongo/
│   │       ├── documents/         # MongoDB documents for read side
│   │       └── repositories/      # MongoDB repositories
│   ├── grpcserver/                # gRPC service implementation
│   ├── listeners/                 # Event listeners
│   └── passengers/
│       ├── dtos/                  # Data Transfer Objects
│       ├── enums/                 # Enumerations
│       ├── exceptions/            # Custom exceptions
│       ├── features/              # Feature slices (CQRS)
│       ├── models/                # Domain models
│       └── valueobjects/          # Value objects
```

## Implemented Features

### 1. Create Passenger
- **Endpoint**: `POST /api/v1/passenger`
- **Authorization**: ADMIN role required
- **Purpose**: Creates a new passenger record

**Components**:
- `CreatePassengerCommand` - Command record
- `CreatePassengerCommandHandler` - Handles passenger creation in PostgreSQL
- `CreatePassengerMongoCommand` - Command for MongoDB sync
- `CreatePassengerMongoCommandHandler` - Syncs data to MongoDB
- `PassengerCreatedDomainEvent` - Domain event for event-driven architecture
- `CreatePassengerRequestDto` - Request DTO
- `CreatePassengerCommandValidator` - Input validation
- `PassengerController` - REST controller

### 2. Get Passenger By ID
- **Endpoint**: `GET /api/v1/passenger/{id}`
- **Authorization**: ADMIN role required
- **Purpose**: Retrieves a specific passenger by ID from MongoDB (read side)

**Components**:
- `GetPassengerByIdQuery` - Query record
- `GetPassengerByIdQueryHandler` - Handles query from MongoDB
- `GetPassengerByIdQueryValidator` - Input validation
- `GetPassengerByIdController` - REST controller

### 3. Get All Passengers
- **Endpoint**: `GET /api/v1/passenger`
- **Authorization**: ADMIN role required
- **Purpose**: Retrieves all active passengers from MongoDB (read side)

**Components**:
- `GetAllPassengersQuery` - Query record
- `GetAllPassengersQueryHandler` - Handles query from MongoDB
- `GetAllPassengersController` - REST controller

### 4. Update Passenger
- **Endpoint**: `PUT /api/v1/passenger/{id}`
- **Authorization**: ADMIN role required
- **Purpose**: Updates an existing passenger record

**Components**:
- `UpdatePassengerCommand` - Command record
- `UpdatePassengerCommandHandler` - Handles passenger update in PostgreSQL
- `UpdatePassengerMongoCommand` - Command for MongoDB sync
- `UpdatePassengerMongoCommandHandler` - Syncs updated data to MongoDB
- `PassengerUpdatedDomainEvent` - Domain event
- `UpdatePassengerRequestDto` - Request DTO
- `UpdatePassengerCommandValidator` - Input validation
- `UpdatePassengerController` - REST controller

### 5. Delete Passenger
- **Endpoint**: `DELETE /api/v1/passenger/{id}`
- **Authorization**: ADMIN role required
- **Purpose**: Soft deletes a passenger (sets isDeleted flag to true)

**Components**:
- `DeletePassengerCommand` - Command record
- `DeletePassengerCommandHandler` - Handles soft delete in PostgreSQL
- `DeletePassengerMongoCommand` - Command for MongoDB sync
- `DeletePassengerMongoCommandHandler` - Syncs deletion to MongoDB
- `PassengerDeletedDomainEvent` - Domain event
- `DeletePassengerCommandValidator` - Input validation
- `DeletePassengerController` - REST controller

### 6. Complete Passenger Registration
- **Endpoint**: `PATCH /api/v1/passenger/{id}/complete`
- **Authorization**: ADMIN role required
- **Purpose**: Completes a passenger's profile with all required information

**Components**:
- `CompletePassengerCommand` - Command record
- `CompletePassengerCommandHandler` - Handles completion in PostgreSQL
- `CompletePassengerMongoCommand` - Command for MongoDB sync
- `CompletePassengerMongoCommandHandler` - Syncs data to MongoDB
- `PassengerCompletedDomainEvent` - Domain event
- `CompletePassengerRequestDto` - Request DTO
- `CompletePassengerCommandValidator` - Input validation
- `CompletePassengerController` - REST controller

## Domain Model

### Passenger Aggregate
```java
public class Passenger extends AggregateRoot<PassengerId> {
    Name name;
    PassportNumber passportNumber;
    PassengerType passengerType;
    Age age;

    // Factory method for creation
    public static Passenger create(...);

    // Methods for state changes
    public void update(...);
    public void delete();
    public void complete(...);
}
```

### Value Objects
- `PassengerId` - Passenger identifier
- `Name` - Passenger name
- `PassportNumber` - Passport number
- `Age` - Passenger age

### Enums
- `PassengerType` - Male, Female, Baby

## Data Layer

### Write Side (PostgreSQL)
- `PassengerEntity` - JPA entity
- `PassengerRepository` - Spring Data JPA repository

### Read Side (MongoDB)
- `PassengerDocument` - MongoDB document
- `PassengerReadRepository` - Spring Data MongoDB repository

## gRPC Service

The service exposes a gRPC endpoint for inter-service communication:

```protobuf
service PassengerService {
    rpc GetById(PassengerRequestDto) returns (PassengerResponseDto);
}
```

**Implementation**: `PassengerServiceGrpcImpl`

## Exception Handling

- `PassengerNotFoundException` - Thrown when passenger is not found
- `PassengerAlreadyExistException` - Thrown when duplicate passport number

## API Request/Response Examples

### Create Passenger
```json
POST /api/v1/passenger
{
    "name": "John Doe",
    "PassportNumber": "AB123456",
    "passengerType": "Male",
    "age": 30
}
```

### Update Passenger
```json
PUT /api/v1/passenger/{id}
{
    "name": "John Doe Updated",
    "passportNumber": "AB123456",
    "passengerType": "Male",
    "age": 31,
    "isDeleted": false
}
```

### Complete Passenger
```json
PATCH /api/v1/passenger/{id}/complete
{
    "name": "John Doe",
    "passportNumber": "AB123456",
    "passengerType": "Male",
    "age": 30
}
```

## Technologies Used

- **Spring Boot** - Application framework
- **Spring Data JPA** - PostgreSQL persistence
- **Spring Data MongoDB** - MongoDB persistence
- **gRPC Spring** - Inter-service communication
- **Spring Security** - Authentication and authorization
- **Springdoc OpenAPI** - API documentation
- **Lombok** - Boilerplate reduction

## CQRS Pattern

The service follows CQRS (Command Query Responsibility Segregation):

- **Commands** (Write operations) - Use PostgreSQL via JPA
- **Queries** (Read operations) - Use MongoDB for optimized reads
- **Event Sync** - Domain events trigger MongoDB synchronization

## Event-Driven Architecture

Domain events are published for:
- `PassengerCreatedDomainEvent`
- `PassengerUpdatedDomainEvent`
- `PassengerDeletedDomainEvent`
- `PassengerCompletedDomainEvent`

These events enable:
- Read model synchronization (CQRS)
- Inter-service communication via RabbitMQ
- Audit logging

## File Structure Summary

```
features/
├── createpassenger/
│   ├── CreatePassengerCommand.java
│   ├── CreatePassengerCommandHandler.java
│   ├── CreatePassengerCommandValidator.java
│   ├── CreatePassengerMongoCommand.java
│   ├── CreatePassengerMongoCommandHandler.java
│   ├── CreatePassengerRequestDto.java
│   ├── PassengerController.java
│   └── PassengerCreatedDomainEvent.java
├── getpassengerbyid/
│   ├── GetPassengerByIdController.java
│   ├── GetPassengerByIdQuery.java
│   ├── GetPassengerByIdQueryHandler.java
│   └── GetPassengerByIdQueryValidator.java
├── getallpassengers/
│   ├── GetAllPassengersController.java
│   ├── GetAllPassengersQuery.java
│   └── GetAllPassengersQueryHandler.java
├── updatepassenger/
│   ├── UpdatePassengerCommand.java
│   ├── UpdatePassengerCommandHandler.java
│   ├── UpdatePassengerCommandValidator.java
│   ├── UpdatePassengerController.java
│   ├── UpdatePassengerMongoCommand.java
│   ├── UpdatePassengerMongoCommandHandler.java
│   ├── UpdatePassengerRequestDto.java
│   └── PassengerUpdatedDomainEvent.java
├── deletepassenger/
│   ├── DeletePassengerCommand.java
│   ├── DeletePassengerCommandHandler.java
│   ├── DeletePassengerCommandValidator.java
│   ├── DeletePassengerController.java
│   ├── DeletePassengerMongoCommand.java
│   ├── DeletePassengerMongoCommandHandler.java
│   └── PassengerDeletedDomainEvent.java
├── completepassenger/
│   ├── CompletePassengerCommand.java
│   ├── CompletePassengerCommandHandler.java
│   ├── CompletePassengerCommandValidator.java
│   ├── CompletePassengerController.java
│   ├── CompletePassengerMongoCommand.java
│   ├── CompletePassengerMongoCommandHandler.java
│   ├── CompletePassengerRequestDto.java
│   └── PassengerCompletedDomainEvent.java
└── Mappings.java
```