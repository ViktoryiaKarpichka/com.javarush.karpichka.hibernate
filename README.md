<h2> 🌍 Final Project: Postgres + Hibernate + Redis Integration</h2>

This project is a part of the final assignment for the 4th module of JavaRush University.  
The goal is to demonstrate working with multiple technologies: **Postgres**, **Hibernate**, **Redis**, and **Docker**.

---

## 🧠 Project Idea

We have a relational Postgres database with a schema consisting of:
- Country
- City
- CountryLanguage

One of the frequently requested queries is related to cities, which performs slowly due to complex joins and data size.  
To solve this, we decided to move frequently requested data into **Redis**, an in-memory key-value store, for faster access.

---

## 🛠 Technologies Used

- **Postgres**
- **Hibernate** for ORM
- **Redis** for caching
- **Docker** for containerization
- Optional: **RedisInsight** for viewing Redis data

---

## 🚀 How to Run the Project

### 1. Install Required Tools

- IntelliJ IDEA Ultimate
- Docker
- RedisInsight *(optional)*
- Postgres client (e.g. DBeaver, pgAdmin)

### 2. Set Up Postgres

- Run Postgres in Docker
- Load the provided database dump into your Postgres instance

### 3. Set Up Redis

- Run Redis as a Docker container
- *(Optional)* Use RedisInsight to view cached data

### 4. Run Application

- Execute Application#getAllData() to load Redis with optimized data.

---

## 🧩 Project Structure

- **Domain Layer** — `City`, `Country`, `CountryLanguage`, and DTOs
- **Repositories** — direct database access using Hibernate
- **Services** — business logic for:
  - fetching data from Postgres
  - transforming it
  - pushing and reading from Redis
- **Redis Layer** — simple get/set logic using Jedis
- **Performance Comparison** — measure access speed from both Postgres and Redis

---

## 📊 Result

By moving frequently accessed data to Redis and only storing necessary fields, we achieved a significant performance improvement for repeated city queries.
