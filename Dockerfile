# Step 1: Use official Maven image with OpenJDK 17 as the base environment
FROM maven:3.8.4-openjdk-17

# Step 2: Set the directory inside the container where our code will live
WORKDIR /app

# Step 3: Copy all the project files from the local machine to the container
COPY . .

# Step 4: Run maven command to download all dependencies (Go-offline mode)
# This ensures that all jars are available before running tests
RUN mvn dependency:go-offline

# Step 5: Specify the command to execute the test suite when the container starts
CMD ["mvn", "test"]