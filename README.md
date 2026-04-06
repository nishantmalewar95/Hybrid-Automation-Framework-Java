#**🛡️ Hybrid Test Automation Framework (SDET-L2)**

##**📋 Project Overview**
Architecture design for high-performance UI and API testing. This framework is built to be environment-agnostic, supporting local execution as well as containerized CI/CD pipelines.

-------------------------------------------------------------------------------------------------------------------------------------------
##**🏗️ Technical Stack**
UI Automation: Selenium WebDriver 4.x

API Testing: Rest-Assured

Execution Engine: TestNG

Architecture: Page Object Model (POM) with Fluent Interface

Thread Safety: Custom ThreadLocal implementation

Containerization: Docker & Selenium Grid

CI/CD: GitHub Actions (with Auto-Artifact Recovery)
------------------------------------------------------------------------------------------------------------------------------------------
##**🧪 Core Framework Features**
Headless Execution: Pre-configured for Chrome Headless mode (headless=new) to optimize resources in Cloud/Linux environments.

Thread-Safe Driver: Custom ThreadLocal<WebDriver> wrapper to ensure zero race conditions during parallel execution.

Auto-Artifact Recovery: Intelligent logic in GitHub Actions to extract failure screenshots directly from the Docker container.

Scalability: Integrated with docker-compose for horizontal scaling of Selenium nodes.

--------------------------------------------------------------------------------------------------------------------------------------
##**🚀 Execution Guide**
###1. Standard Execution
mvn clean test (Executes the entire regression suite)

###2. Specific Test Execution
mvn test -Dtest=LoginTests (Runs specific UI tests)
mvn test -Dtest=JsonPlaceholderSchemaTest (Runs API tests)

###3. Docker Infrastructure
docker-compose up -d --scale chrome=3 (Spins up a scalable Selenium Grid)

--------------------------------------------------------------------------------------------------------------------------------------
##**📂 Project Structure**
.github/workflows: CI/CD Pipeline Definitions

src/test/java/pages: Page Objects and UI Actions

src/test/java/tests: Functional UI & API Test Cases

Screenshots: Automated failure captures

Dockerfile: Container environment configuration

pom.xml: Dependency and Plugin management
--------------------------------------------------------------------------------------------------------------------------------------
##**👨‍💻 Author**
###Nishant Malewar
SDET / Automation Engineer
