# 🚀 Advanced Selenium & API Automation Framework

### 🌟 Project Overview
This is a hybrid automation framework built to handle both UI (SauceDemo) and API (JSONPlaceholder) testing. It features a scalable architecture using Page Object Model (POM) and is "Cloud-ready" with Docker integration.

---

### 🛠️ Tech Stack & Patterns
* **Language:** Java 17
* **UI Automation:** Selenium WebDriver
* **API Testing:** Rest-Assured
* **Framework:** TestNG, Maven
* **Design Pattern:** Page Object Model (POM)
* **Infrastructure:** Docker & Selenium Grid

---

### 🏗️ Framework Architecture
1. **BaseTest:** Common setup for WebDriver (Incognito mode).
2. **Page Objects:** Separated UI locators and actions.
3. **API Tests:** Schema validation and functional API testing.
4. **Resources:** JSON Schemas and Docker configurations.

---

### 🐳 Docker & Infrastructure (Task 4)
This framework supports distributed testing via Selenium Grid on Docker.
* **Run Grid:** `docker-compose up -d`
* **Check Hub Status:** Navigate to `http://localhost:4444`
* **Scale Chrome Nodes:** `docker-compose up -d --scale chrome=3`

---

### 🧪 Features & Coverage
* **UI Testing:**
    - Valid/Invalid Login workflows.
    - End-to-end "Add to Cart" functionality.
* **API Testing:**
    - JSON Schema Validation using Rest-Assured.
    - Status code and response body assertions.
* **Git Management:** Handled complex merge conflicts during feature integration.

---

### 🏃 How to Run
1. **Pre-requisites:** Ensure Java, Maven, and Docker are installed.
2. **Execution:**
   - For all tests: `mvn test`
   - For specific UI test: `mvn test -Dtest=LoginTest`
   - For API Schema test: `mvn test -Dtest=JsonPlaceholderSchemaTest`

---

### 💡 Pro-Tip for Interviewers
> This framework is designed to be "Environment Agnostic". Whether it's running on a local machine or inside a CI/CD pipeline with Docker, the setup remains consistent and reliable.