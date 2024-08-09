# Dressy - POS System Backend

**Dressy** is a backend system for a Point of Sale (POS) application designed for clothing shops. Built with Java EE, it manages all CRUD operations for items, customers, and orders. The backend handles transactions efficiently and connects with the frontend via AJAX. The database is managed using MySQL, while the frontend is created with HTML, CSS, and JavaScript.

## Features

- **Item Management:** CRUD operations for clothing items.
- **Customer Management:** CRUD operations for customer data.
- **Order Management:** Manage customer orders, including creating, and viewing order history.
- **Transaction Handling:** Reliable transaction management with MySQL.
- **AJAX Integration:** Seamless communication with the frontend.

## Technologies Used

- **Java EE**: Backend framework.
- **MySQL**: Database management.
- **HTML/CSS/JavaScript**: Frontend technologies.
- **AJAX**: For dynamic communication between frontend and backend.

## Frontend Repository

The frontend for this project is available at: [Dressy Frontend Repository](https://github.com/tharushiImasha/POS-system--Dressy)

## Installation

### Prerequisites

- **Java Development Kit (JDK)**
- **Apache Tomcat** or another Java EE-compatible server
- **MySQL** database
- **Maven** for dependency management

### Setup Instructions


### Key Points:
- **Clone the repository:** Provides the initial step to get the source code.
- **Navigate to the project directory:** Directs the user to move into the correct folder.
- **Build the backend:** Instructs on how to build the Java backend using Maven.
- **Deploy the backend:** Covers deployment on a Java EE server.
- **Run the frontend:** Details how to start the frontend by opening the `index.html` file.
- **View the application:** Tells users how to access the application through their browser.

This concise format helps users quickly set up and run your POS system without overwhelming them with details.


## API Documentation

You can find the detailed API documentation [API Documentation](https://documenter.getpostman.com/view/35386359/2sA3s1orq5)

### Items

- **Get All Items**
  - **Endpoint:** `GET /api/items`
  - **Description:** Retrieve a list of all clothing items.
  - **Response:** JSON array of item objects.

- **Add a New Item**
  - **Endpoint:** `POST /api/items`
  - **Description:** Add a new item to the inventory.
  - **Request Body:** JSON object containing item details.
  - **Response:** JSON object of the created item.

- **Update an Item**
  - **Endpoint:** `PUT /api/items/{id}`
  - **Description:** Update an existing item by ID.
  - **Request Body:** JSON object with updated item details.
  - **Response:** JSON object of the updated item.

- **Delete an Item**
  - **Endpoint:** `DELETE /api/items/{id}`
  - **Description:** Delete an item by ID.
  - **Response:** Status message indicating success or failure.

### Customers

- **Get All Customers**
  - **Endpoint:** `GET /api/customers`
  - **Description:** Retrieve a list of all customers.
  - **Response:** JSON array of customer objects.

- **Add a New Customer**
  - **Endpoint:** `POST /api/customers`
  - **Description:** Add a new customer to the database.
  - **Request Body:** JSON object containing customer details.
  - **Response:** JSON object of the created customer.

- **Update a Customer**
  - **Endpoint:** `PUT /api/customers/{id}`
  - **Description:** Update an existing customer by ID.
  - **Request Body:** JSON object with updated customer details.
  - **Response:** JSON object of the updated customer.

- **Delete a Customer**
  - **Endpoint:** `DELETE /api/customers/{id}`
  - **Description:** Delete a customer by ID.
  - **Response:** Status message indicating success or failure.

### Orders

- **Get All Orders**
  - **Endpoint:** `GET /api/orders`
  - **Description:** Retrieve a list of all orders.
  - **Response:** JSON array of order objects.

- **Create a New Order**
  - **Endpoint:** `POST /api/orders`
  - **Description:** Create a new order.
  - **Request Body:** JSON object containing order details.
  - **Response:** JSON object of the created order.

## Usage

- **Frontend Integration:** The frontend interacts with these API endpoints via AJAX.
- **Testing:** Use tools like Postman to test the API endpoints.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Feedbacks

Please give your valuable feedback through GitHub.

