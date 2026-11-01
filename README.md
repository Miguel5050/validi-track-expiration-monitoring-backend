# ValidiTrack Expiration Monitoring Backend

## Student Information
- Student Name: Mihigo Tristan Miguel
- Student ID: 26449
- Course: INSY 8322 Web Technology and Internet
- Institution: Adventist University of Central Africa (AUCA)

## Overview
This project is a backend application for monitoring document and license expirations. It supports the management of holders, compliance officers, branches, items, and renewal-related records in a structured system for tracking compliance obligations.

## Prerequisites
- Java 21 (JDK 21)
- Maven
- PostgreSQL database

## Environment & Database Configuration
The application is configured to run on port `8082`.

Database configuration is set in `src/main/resources/application.properties` and uses PostgreSQL with Hibernate configured as `ddl-auto=update`.

## Setup & Running Instructions
1. Open a terminal in the project root.
2. Build the project:
   ```bash
   ./mvnw clean install
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. On Windows, use:
   ```bash
   .\mvnw.cmd clean install
   .\mvnw.cmd spring-boot:run
   ```

## Postman Testing
The Postman collection for API verification is located in the root folder as `validi-track-expiration-monitoring-midterm.postman_collection.json`. This collection can be imported into Postman to test the implemented endpoints, including A1–F2 and the Bonus endpoints.
