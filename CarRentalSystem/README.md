
# Car Rental System

## Project Description
The **Car Rental System** is a desktop application designed to streamline car rental operations. It provides a user-friendly interface for administrators, staff, and customers to manage bookings, inventory, and user accounts. The system ensures secure authentication, role-based access, and a seamless user experience.

---

## Features

### Login and Authentication
- **Login Screen**:
  - Role-based access for Admin, Staff, and Customer.
  - Secure login with username/email and password.
  - "Forgot Password" option for account recovery.
- **Password Recovery**:
  - Allows users to recover accounts by providing username, email, phone, and role.

### Admin Functionalities
- **Admin Dashboard**:
  - Manage Cars.
  - Manage Users.
- **Manage Cars**:
  - Add, update, or delete car inventory.
  - View all cars in a table with details (e.g., Make, Model, Price).
- **Manage Users**:
  - Add, update, or delete user accounts (Admin and Customer).
  - Separate tables to view Admin and Customer details.

### Customer Functionalities
- **Customer Dashboard**:
  - Search Cars.
  - View Current Bookings.
- **Search for a Car**:
  - Browse and filter available cars.
  - Display car details in a table.
  - Option to book a selected car.
- **Book Car Form**:
  - Displays selected car details (Make, Model, Price per day).
  - Input rental duration to calculate total price.
  - Confirm booking to finalize the process.
- **View Current Bookings**:
  - Display a list of active bookings.
  - Option to return booked cars.

---

## System Architecture

The system is built using a layered architecture:
1. **User Interface Layer**:
   - Desktop GUI built using Java Swing.
   - Includes screens for login, dashboards, and management functionalities.
2. **Business Logic Layer**:
   - Handles booking validation, car inventory updates, and user account management.
3. **Data Layer**:
   - Relational database for storing users, cars, and bookings.

### Key Components
- **Authentication**:
  - Role-based access control for Admin, Staff, and Customer.
  - Password encryption for secure authentication.
- **Car Management**:
  - Inventory management for Admin.
  - Real-time updates for car availability.
- **Booking and Payment**:
  - Seamless booking process with dynamic price calculation.
  - Customer-friendly booking and return process.

---

## Installation Steps

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd CarRentalSystem
   ```

2. **Set Up the Database**:
   - Import the provided SQL script (`database.sql`) into your MySQL instance:
     1. Open your MySQL command-line client or GUI tool (e.g., phpMyAdmin, MySQL Workbench).
     2. Create a new database:
        ```sql
        CREATE DATABASE car_rental_system;
        ```
     3. Use the newly created database:
        ```sql
        USE car_rental_system;
        ```
     4. Import the SQL file:
        ```bash
        SOURCE path_to_database.sql;
        ```
        Replace `Car-Rental-System/DataBase/car_rental_system.sql` with the actual path to the `database.sql` file provided in the project.

   - Update the database connection details in `config.properties`:
     1. Navigate to the `resources` directory in your project.
     2. Open the `config.properties` file.
     3. Update the following properties:
        ```
        db.url=jdbc:mysql://localhost:3306/car_rental_system
        db.username=<your_mysql_username>
        db.password=<your_mysql_password>
        ```

3. **Run the Application**:
   - Open the project in your IDE (e.g., IntelliJ IDEA, Eclipse, or NetBeans).
   - Build the project and resolve dependencies using Maven or Gradle (if applicable).
   - Locate and run the `Main` class to launch the application.

4. **Login Credentials**:
   - Use the following credentials to log in based on your role:
     - **Admin**: `admin@example.com` / `password123`
     - **Staff**: `staff@example.com` / `password123`
     - **Customer**: Register a new account via the login screen.

5. **Setup Complete**:
   - Once logged in, you can start using the functionalities for managing cars, users, and bookings.

---

## Usage Instructions

### Admin
- After logging in, navigate through the admin dashboard to:
  - **Manage Cars**: Add, update, or delete cars in the inventory.
  - **Manage Users**: Add, update, or delete user accounts for Admin and Customers.

### Customer
- Once logged in, access the customer dashboard to:
  - **Search Cars**: Browse available cars and select one for booking.
  - **Book Cars**: Confirm your booking and calculate the total price based on rental days.
  - **View Current Bookings**: Manage your active rentals or return a car.

---

## Code Documentation

- **Comments**:
  - All classes and methods are documented with comments explaining their purpose.
- **Design Documentation**:
  - Refer to the `docs` folder for class diagrams and detailed design explanations.

---

## Contribution Guidelines

1. Fork the repository.
2. Create a new branch for your feature or fix.
3. Follow Java best practices and include comments in your code.
4. Commit your changes with clear messages.
5. Push your branch and create a pull request.

---

### Screenshots

#### Login and Authentication
![Login Screen](https://i.imgur.com/BpoHmYY.png)

#### Recover Password
![Recover Password](https://i.imgur.com/9pmOxon.png)

#### Admin Dashboard
![Admin Dashboard](https://i.imgur.com/ZxwI2r4.png)

#### Manage Users
![Manage Users](https://i.imgur.com/qBWihdY.png)

#### Manage Cars
![Manage Cars](https://i.imgur.com/XWCwtXx.png)

#### Customer Dashboard
![Customer Dashboard](https://i.imgur.com/PDPpbRe.png)

#### Search for a car
![Search for a car](https://i.imgur.com/nxZku1i.png)

#### Book a car form
![Book a car form](https://i.imgur.com/y7gmBNx.png)

#### View current bookings
![View current bookings](https://i.imgur.com/HC0HNBr.png)

#### Staff Dashboard
![Admin Dashboard](https://i.imgur.com/L2tdXFK.jpeg)

#### Manage Bookings
![Admin Dashboard](https://i.imgur.com/oEYHJUw.jpeg)
