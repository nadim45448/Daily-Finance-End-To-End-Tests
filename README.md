#  Daily Finance Management System-End-To-End Tests

### Project Overview     
This project is an **End-to-End Automation Testing Suite** for [DailyFinance](https://dailyfinance.roadtocareer.net/), covering **UI Testing (Selenium TestNG)**.  
It includes user registration, login, password reset, item creation, profile update, and admin panel verification using a proper **Page Object Model (POM)** architecture.  
Uses config.properties, localstorage.json file for managing sensitive credentials


---

##  Tech Stack

| Tool/Library     | Purpose                         |
|------------------|----------------------------------|
| Java             | Programming Language             |
| Selenium WebDriver | UI Automation                   |
| TestNG           | Test Framework                   |
| Rest Assured     | API Testing                      |
| Gradle           | Build Tool                       |
| Allure           | Reporting                        |
| Gmail API        | Email Verification               |
| Apache Commons CSV | CSV File Handling              |
| Java Faker       | Random Data Generation           |

---

##  Features Covered

-  Register user and assert registration email
-  Negative test cases for password reset
-  Valid password reset using Gmail
-  Login with new password
-  Create items (all fields + mandatory only)
-  Update profile email and verify login
-  Admin login and user verification
-  Register multiple users from CSV
-  Export all users from dashboard to text file

---

##  Test Case Documentation

 All standard and negative test cases are documented in this Google Sheet:  
   [Test Cases - DailyFinance Automation Suite](https://docs.google.com/spreadsheets/d/your-link-here)
   
---
## Full Automation Process Recording Video
You can watch the full automation process execution in the recorded video below:  
[Watch Full Automation Demo Video](https://drive.google.com/file/d/1Lwdf3nQptl4tnUl0I94tCMM-Wn0sL2_A/view?usp=sharing)

---

## Allure Report
![overview](https://github.com/user-attachments/assets/21e393d9-f2e9-4228-abf7-d2dd44433bde)

![behaviors](https://github.com/user-attachments/assets/67dcad33-9987-4707-951b-5abe0e3ac04a)




---



##  Pre-requisites

Before running the project, make sure the following are installed:

1. **Java JDK 17+**  
    [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)

2. **Gradle**  
    [Install Gradle](https://gradle.org/install/)

3. **IntelliJ IDEA** (Recommended IDE)

4. **Allure CLI (for reports)**  
    [Install Allure](https://docs.qameta.io/allure/#_installing_a_commandline)

5. **Chrome WebDriver**  
   Ensure it's compatible with your Chrome version. Place it in your system PATH.

6. **Gmail API access**  
   Set up credentials for accessing Gmail inbox programmatically (for password/email assertions).
   
---
##  Project Setup & Execution
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/DailyFinance-FullStackAutomation.git
   ```
    - cd DailyFinance-FullStackAutomation
2. Configure Gmail Credentials:  
    - Setup Gmail API token securely for reading mails.
3. Run Tests via Gradle:
   - gradle clean test
4. Generate and Serve Allure Report
   ```bash
   allure generate allure-results --clean -o allure-report
   allure serve allure-results
   ```
   ---
     
  


