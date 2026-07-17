# IIUC SQA Practical — Automation Exercise Login

Student ID: **C213070R**

Assigned page used in this project:

`https://automationexercise.com/login`

## What the project demonstrates

- Java 17
- Maven
- Selenium WebDriver
- TestNG
- Three automated test cases
- Three locator types: name, XPath and CSS selector
- Multiple assertions
- Headless Chromium for browser-only cloud execution

## Run online with GitHub Codespaces

1. Create a new empty GitHub repository.
2. Upload every file and folder from this project.
3. Open the repository and choose **Code → Codespaces → Create codespace on main**.
4. Wait for the browser-based editor and terminal to open.
5. In the terminal, run:

```bash
mvn clean test
```

## Expected terminal result

```text
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

TestNG/Surefire reports are generated under:

```text
target/surefire-reports/
```

## Live explanation

- `@BeforeMethod` opens a clean browser before every test.
- `@Test` marks each automated test case.
- `@AfterMethod` closes the browser even if a test fails.
- `WebDriverWait` is an explicit wait and is more reliable than fixed `Thread.sleep`.
- The positive test uses a timestamp to create a unique email.
- The negative test verifies the exact invalid-login error.
- The validation test checks the URL, title and page headings.
- Assertions compare actual application behavior with expected behavior.

## Important

Use this project only for the website assigned or approved by your instructor. Read and understand every test because the practical examination requires a live code explanation.
