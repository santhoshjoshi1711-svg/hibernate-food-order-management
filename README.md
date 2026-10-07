# Hibernate Food Order Management

A console-based Food Order Management application built with **Java, Hibernate ORM 6 and Oracle DB**.
It demonstrates full CRUD operations using Hibernate annotations, the DAO pattern and transactions.

## Features
- Place a food order
- View all orders
- Find an order by ID
- Update order status (PLACED / PREPARING / DELIVERED)
- Cancel an order

## Tech Stack
- Java 17
- Hibernate ORM 6.6.1.Final
- Jakarta Persistence API 3.1
- Oracle Database (ojdbc11)
- Maven

## Project Structure
```
hibernate-food-order-management
├── pom.xml
├── README.md
└── src/main
    ├── java/com/example
    │   ├── FoodOrder.java      (Entity)
    │   ├── FoodOrderDAO.java   (CRUD operations)
    │   └── Main.java           (Console menu)
    └── resources
        └── hibernate.cfg.xml   (DB + Hibernate config)
```

## How to Run
1. Install Oracle DB and Java 17+.
2. Open `src/main/resources/hibernate.cfg.xml` and put your own DB username and password.
3. Run `mvn compile exec:java -Dexec.mainClass="com.example.Main"` or run `Main.java` from your IDE.
4. The `food_orders` table is created automatically (`hbm2ddl.auto=update`).

## Concepts Covered
- `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`
- `SessionFactory`, `Session`, `Transaction`
- `persist()`, `get()`, `remove()`, HQL (`from FoodOrder`)
- Commit and rollback handling

## Author
Santhosh, final-year CSD student
