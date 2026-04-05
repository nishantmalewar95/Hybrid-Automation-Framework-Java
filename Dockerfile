# Step 1: Base Image
FROM maven:3.8.4-openjdk-17

# Step 2: Work Directory (Container ke andar ka folder)
WORKDIR /app

# Step 3: Copy command (Yahan galti ho rahi thi)
# Pehla dot (.) ka matlab hai aapka PC
# Doosra path (/app/) ka matlab hai container ka folder
COPY . /app/

# Step 4: Dependencies download karein
RUN mvn dependency:go-offline

# Step 5: Test run karein
CMD ["mvn", "test"]