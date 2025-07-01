#EPAM-TA-Final-Task

#SauceDemo Login Test Automation

This project automates the login functionality tests for the SauceDemo website. It verifies common login scenarios such as successful login, invalid login, and handling empty credentials.
Prerequisites

    Java 17+

    Maven

    Selenium WebDriver

    TestNG

Setup

    Clone the Repository: https://github.com/kostic-mladen/EPAM-TA-Final-Task.git

Install Dependencies:
Run mvn install to download the necessary dependencies.

WebDriver Setup:
The WebDriver manager will handle downloading the required browser drivers (ChromeDriver, GeckoDriver).

Configure TestNG Parameters:
Edit testng.xml for browser and headless mode parameters:

    <parameter name="browser" value="chrome"/>
    <parameter name="headless" value="true"/>

Running Tests

    Via Maven:

    mvn clean test

    From IntelliJ/Eclipse:
    Right-click testng.xml and select Run.

Test Case (UC-1): Test Login with Empty Credentials

    Enter any credentials.

    Clear both fields (Username and Password).

    Hit the Login button.

    Verify the error message "Username is required".

UC-2: Test Login Form with Credentials by Passing Username

    Type any credentials into the "Username" field.

    Enter a password.

    Clear the "Password" input.

    Hit the "Login" button.

    Check the error messages: "Password is required."

UC-3: Test Login form with credentials bypassing Username & Password

    Type credentials in username which are under Accepted username are sections.

    Enter password as secret sauce.

    Click on Login and validate the title "Swag Labs" in the dashboard.
