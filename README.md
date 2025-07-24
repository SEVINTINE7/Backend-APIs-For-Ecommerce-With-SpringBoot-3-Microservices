# Spring Boot Microservices Project for an E-commerce backend with secured authentication

## Project Overview

This project consists of microservices with role-based access control (Admin, Staff, Customer) implemented using Spring Security 6 and Spring Boot 3. Each services are secured with JWT authentication.


## Services

1. Identity Service (Port 8082): Handles authentication, authorization, and user management
2. Product Service (Port 8081): Manages products, categories, and inventory
** Others are still under development **


## Security Implementation

* Spring Security 6 with JWT authentication
* Role-based authorization (ADMIN, STAFF, CUSTOMER)
* Password encryption using BCrypt
* Secure API endpoints with appropriate access controls


# API Documentation


### Identity Service (Port: 8082)

#### Admin Endpoints

| Method |	Endpoint |	Description |
| :------- | :------------------ | :-------------- |
| POST |	` /api/admin/login ` |	Admin login |
| POST | `	/api/admin/register ` |	Register new admin |
| POST | `	/api/role/add-new-role ` |	Add new role |
| POST | `	/api/staff/register ` |	Register new staff member |
| POST | `	/api/user/add-address ` |	Add user address |
| DELETE | `	/api/user/delete-address-by-id/{adId} ` |	Delete address by ID |
| GET |	` /api/user/get-all-addresses ` |	Get all addresses |
| PUT |	` /api/user/update-address/{adId} ` |	Update address by ID |
| PUT |	` /api/user/update-profile ` |	Update user profile |


#### Staff Endpoints

| Method |	Endpoint |	Description |
| :------- | :------------------ | :-------------- |
| POST |	` /api/staff/login ` |	Staff login |
| POST |	` /api/user/add-address ` |	Add user address |
| DELETE |	` /api/user/delete-address-by-id/{adId} ` |	Delete address by ID |
| GET |	` /api/user/get-all-addresses ` |	Get all addresses |
| PUT |	` /api/user/update-address/{adId} ` |	Update address by ID |
| PUT |	` /api/user/update-profile ` |	Update user profile |


#### Customer Endpoints

| Method |	Endpoint |	Description |
| :------- | :------------------ | :-------------- |
| POST |	` /api/auth/login ` |	Customer login |
| POST |	` /api/auth/register ` |	Customer registration |
| POST |	` /api/user/add-address ` |	Add address |
| DELETE |	` /api/user/delete-address-by-id/{adId} ` |	Delete address by ID |
| GET |	` /api/user/get-all-addresses ` |	Get all addresses |
| PUT |	` /api/user/update-address/{adId} ` |	Update address by ID |
| PUT | ` /api/user/update-profile ` |	Update profile |


### Product Service (Port: 8081)

#### Admin Endpoints

| Method |	Endpoint |	Description |
| :------- | :------------------ | :-------------- |
| DELETE | `	/api/category/delete-category-with-products/{id} ` |	Delete category with products |
| DELETE | `	/api/category/delete-category/{id} ` |	Delete category |
| DELETE | `	/api/products/delete-product-by-id/{id} ` |	Delete product by ID |
| POST | `	/api/category/save-category ` |	Create new category |
| POST | `	/api/products/save-product ` |	Create new product |
| PUT | `	/api/category/update-category/{id} ` |	Update category |
| PUT | `	/api/inventory/update-product-inventory/{id} ` |	Update product inventory |
| PUT | `	/api/products/update-product/{id} ` |	Update product |
| GET | `	/api/products/get-product-by-id/{id} ` |	Get product by ID |
| GET | `	/api/category/get-category-by-id/{id} ` |	Get category by ID |
| GET |	` /api/inventory/get-product-inventory-by-id/{id} ` |	Get product inventory by ID |
| GET | `	/api/category/get-all-categories ` |	Get all categories |


#### Staff Endpoints

| Method |	Endpoint |	Description |
| :------- | :------------------ | :-------------- |
| DELETE | ` /api/products/delete-product-by-id/{id} ` |	Delete product by ID |
| POST | ` /api/products/save-product ` |	Create new product |
| PUT | `	/api/inventory/update-product-inventory/{id} ` |	Update product inventory |
| PUT | `	/api/products/update-product/{id} ` |	Update product |
| GET | `	/api/products/get-product-by-id/{id} ` |	Get product by ID |
| GET | `	/api/category/get-category-by-id/{id} ` |	Get category by ID |
| GET | `	/api/inventory/get-product-inventory-by-id/{id} ` |	Get product inventory by ID |
| GET | ` /api/category/get-all-categories ` |	Get all categories |


#### Customer Endpoints
| Method |	Endpoint |	Description |
| :------- | :------------------ | :-------------- |
| GET | `	/api/products/get-all-products ` |	Get all products |
| GET | `	/api/products/get-product-by-id/{id} ` |	Get product by ID |
| GET | `	/api/category/get-category-by-id/{id} ` |	Get category by ID |
| GET | `	/api/category/get-all-categories ` |	Get all categories |


## Setup Instructions

### Prerequisites
* Java 17+
* Maven 3.8+
* Spring Boot 3.0+
* MySQL


### Configuration

1. Clone the repository:
  `git clone https://github.com/your-username/your-repo.git`

2. Configure databases in application.properties for each service:
  ```
  spring.datasource.url=jdbc:mysql://localhost:3306/user_db_1?createDatabaseIfNotExist=true
  spring.datasource.username=*****
  spring.datasource.password=*******
  ```
    
3. Set JWT secret in identity-service/config/JwtUtilImpl:
  ```
      private String secret = "your-secret";
      private long expiration = expiration time in miliseconds;
  ```

### Running the Services

Run each service separately:

```
  # For Identity Service
  cd identity-service
  mvn spring-boot:run 

  # For Product Service
  cd product-service
  mvn spring-boot:run
```

#### Testing

1. Register users first through Identity Service
2. Obtain JWT token via login endpoints
3. Include token in requests:
  `Authorization: Bearer <your_token>`


## Security Configuration Highlights

  ```
  @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
          http.csrf().disable()
                  .authorizeHttpRequests((authorizeHttpRequests) ->
                          authorizeHttpRequests
                                  .requestMatchers("/api/auth/register", "/api/auth/login", "/api/admin/register", "/api/admin/login", "api/staff/login").permitAll()
                                  .anyRequest().authenticated())
                  .sessionManagement((sessionManagement) ->
                          sessionManagement
                                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
          http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
          return http.build();
      }
  ```


## Dependencies

* Spring Boot Starter Web
* Spring Boot Starter Security
* Spring Boot Starter Data JPA
* JJWT (Java JWT)
* MySQL Driver
* Lombok
* Spring Boot DevTools

For the complete dependency list, check ```pom.xml``` in each service directory.
