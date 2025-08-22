#EPAM-TA-Final-Task

# Selenium Test Automation Framework

This project is a **Test Automation Framework** built with **Java**, **Selenium WebDriver**, **TestNG**, and **Maven**.  
It follows the **Page Object Model (POM)** design pattern for better readability, maintainability, and scalability.
---
## ⚙️ Prerequisites

- Java JDK 8+
- Maven 3.6+
- IntelliJ IDEA / Eclipse (recommended IDE)
- Git (optional)
---
###  Clone Repository

git clone https://github.com/kostic-mladen/EPAM-TA-Final-Task.git

## 📦 Install Dependencies

Run the following command to download all dependencies:


mvn install

The WebDriverManager library will automatically handle downloading required browser drivers (ChromeDriver, GeckoDriver).

Supported browsers:

chrome
firefox

▶️ Running Tests
Using Maven:
mvn clean test

From IntelliJ/Eclipse:

Right-click on testng.xml

Select Run

✅ Test Cases

UC-1: Test Login with Empty Credentials
Enter any credentials.
Clear both fields (Username and Password).
Hit the Login button.
Verify the error message → "Username is required".

UC-2: Test Login with Missing Password
Enter a valid Username.
Enter a Password.
Clear the Password field.
Hit the Login button.
Verify the error message → "Password is required".

UC-3: Test Login with Valid Credentials
Enter valid username → from Accepted usernames section.
Enter password → "secret_sauce".
Click on Login.
Validate the dashboard title → "Swag Labs".

📌 Notes

Parallel execution is supported via TestNG.
Page Object Model ensures separation of Page Actions and Test Logic.