# Smart Parking Management System

A desktop-based **Smart Parking Management System** built using **Java Swing**, **JDBC**, and **MySQL**.
The application provides a graphical interface to manage vehicle parking records efficiently while following a clean layered architecture.

---

## Features

* Add vehicle parking entries through GUI
* View all parking records in a table (JTable)
* Delete parking records by ID
* Fetch vehicle details by ID *(backend ready)*
* Update parking information *(backend ready)*
* Input validation through service layer

---

## Tech Stack

* Java (Swing)
* JDBC
* MySQL
* SQL

---

## Project Structure

```id="spm1"
Smart-Parking-Management-System/
│
├── src/
│   ├── dao/        → database interaction (JDBC)
│   ├── dto/        → parking record data model
│   ├── service/    → business logic & validation
│   ├── ui/         → Swing GUI
│   └── mysql-connector-j-9.x.x.jar
│
├── .gitignore
└── README.md
```

---

## Architecture Overview

The project follows a layered architecture to ensure clear separation of responsibilities:

* **DTO** – represents vehicle parking data
* **DAO** – handles database operations using JDBC
* **Service** – manages validations and business rules
* **UI** – provides a graphical interface for user interaction

---

## Database Setup

```sql id="spm2"
CREATE DATABASE rnsitdb;
USE rnsitdb;

CREATE TABLE parking_records (
    id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_number VARCHAR(50),
    owner_name VARCHAR(100),
    vehicle_type VARCHAR(50),
    parking_slot VARCHAR(50)
);
```

---

## Running the Project

1. Ensure MySQL server is running
2. Update database credentials in:

   ```
   dao/ParkingDAOImpl.java
   ```
3. Compile the project:

   ```bash
   javac -d . -cp src/mysql-connector-j-9.x.x.jar src/*/*.java
   ```
4. Run the application:

   ```bash
   java -cp ".;src/mysql-connector-j-9.x.x.jar" ui.ParkingUI
   ```

---

## Future Improvements

* Parking slot availability tracking
* Automatic parking fee calculation
* Search vehicles by number
* Add authentication system
* Real-time parking analytics dashboard

---

## What I Learned

* Structuring Java applications using layered architecture
* Integrating Java Swing with MySQL using JDBC
* Managing vehicle parking records using SQL
* Building interactive desktop-based applications

---

## Author

Harshitha K.
