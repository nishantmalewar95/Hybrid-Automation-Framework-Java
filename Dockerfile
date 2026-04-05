# Step 1: Base Image
FROM maven:3.8.4-openjdk-17

# Step 2: Work Directory set karein
WORKDIR /app

# Step 3: Pehle sirf pom.xml copy karein (Best Practice)
COPY pom.xml /app/

# Step 4: Saari baaki files copy karein
COPY . /app/

# Step 5: Check karein ki files copy hui ya nahi (Debugging ke liye)
RUN ls -la /app

# Step 6: Dependencies download karein
RUN mvn dependency:go-offline

# Step 7: Test run karein
CMD ["mvn", "test"]