# Step 1: Use official Maven image with OpenJDK 17
FROM maven:3.8.4-openjdk-17

# Step 2: Set the directory inside the container
# Maine yahan /app define kiya hai
WORKDIR /app

# Step 3: Copy all the project files
# DHAYAN DEIN: Yahan '.' ke baad '/app/' likha hai. 
# Isse Docker ko pata chalta hai ki saari files /app folder ke andar daalni hain.
COPY . /app/

# Step 4: Run maven command to download dependencies
RUN mvn dependency:go-offline

# Step 5: Execute the test suite
CMD ["mvn", "test"]