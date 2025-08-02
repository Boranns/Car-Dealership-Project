# Car-Dealership-Project
Car Dealership Backend API

A comprehensive backend application designed for managing a car dealership platform, built with Java 17, Spring Boot, and PostgreSQL. This API facilitates user authentication, car listing management, and secure purchasing with real-time currency conversion between Turkish Lira (TRY) and US Dollars (USD). It employs best practices in security, architecture, and integration to provide a robust, scalable solution.

---

Project Overview

This system supports multiple user roles:

- Customer: Registers, logs in, browses car listings, and purchases vehicles.
- Gallerist: Registers, logs in, and manages car listings by adding new vehicles for sale.

Key features include:

- Secure user authentication and authorization using JWT and Spring Security.
- Management of core entities: `Customer`, `Gallerist`, `Car`, `UserAccount`, `Address`, `RefreshToken`, `SoldCar` and etc.
- Real-time currency conversion: When a customer wants to buy a car priced in USD but holds balance in TRY, the system automatically converts TRY to USD based on live exchange rates from the Turkish Central Bank (TCMB).
- Currency exchange rates update in real time on weekdays, ensuring accurate pricing.
- Refresh token mechanism to maintain secure user sessions.
- Global exception handling and modular project structure following SOLID principles.

---

## Tech Stack


|                  |                                          |
| Java 17          | Programming language                     |
| Spring Boot      | Application framework                    |
| Spring Security  | Authentication and authorization         |
| JWT              | Token-based secure user sessions         |
| PostgreSQL       | Relational database management system    |
| Postman          | API testing                              |
| TCMB API         | Real-time USD/TRY exchange rates         |


## Project Structure

src  
 main  
     java  
         com.borangalleri  
             config        # Spring configuration classes  
             controller    # REST API controllers  
             dto           # Data Transfer Objects  
             enums         # Enum types  
             exception     # Custom exceptions  
             handler       # Global error handlers  
             jwt           # JWT services and filters  
             model         # JPA entities  
             repository    # Spring Data JPA repositories  
             service       # Business logic layer  
             utils         # Utility classes  

---

## Security Overview

The application uses JWT-based authentication integrated with Spring Security, ensuring secure access to protected endpoints. Passwords are hashed with `BCryptPasswordEncoder` for enhanced security.

Key security components:

- `SecurityConfig.java`: Defines security rules and filters.
- `AppConfig.java`: Bean configurations including password encoder and user details service.
- `JWTAuthenticationFilter.java`: Validates JWT tokens on incoming requests.
- `JWTService.java`: Handles generation and verification of JWT tokens.
- `AuthEntryPoint.java`: Manages unauthorized access responses.
- `RefreshToken` mechanism to enable seamless session continuation.

---

## Currency Conversion Integration

- The API integrates with the Central Bank of Turkey's (TCMB) API to fetch live USD/TRY exchange rates.
- If a customer holds balance in one currency but wants to purchase a car priced in another, the system automatically performs currency conversion.
- Real-time rates ensure pricing accuracy; however, on weekends (Saturday and Sunday), currency markets are closed, so exchange rates may not update, affecting conversion availability.
- Currency data is retrieved efficiently using Spring's `RestTemplate` or `WebClient`.

---

## User Flow

### Customer

1. Register a new account.  
2. Log in with credentials to receive a JWT token.  
3. Browse the list of available cars.  
4. Select and purchase a car if the user’s balance is sufficient.  
   - If the car price and user balance are in different currencies, the system converts the amount automatically using real-time rates.  
5. Upon successful purchase, the car is marked as sold, and the user's balance is updated accordingly.

### Gallerist

1. Register a new account.  
2. Log in to receive a JWT token.  
3. Create and manage car listings for sale.  






