package com.iiuc.sqa;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutomationExerciseLoginTest {

    private static final String BASE_URL = "https://automationexercise.com/login";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // Required for running Chrome inside an online Linux environment.
        options.addArguments(
                "--headless=new",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--window-size=1440,900"
        );

        // Codespaces container provides these environment variables.
        // On a normal computer, Selenium Manager can manage the driver automatically.
        String chromeBinary = System.getenv("CHROME_BIN");
        if (chromeBinary != null && !chromeBinary.isBlank()) {
            options.setBinary(chromeBinary);
        }

        String chromeDriverPath = System.getenv("CHROMEDRIVER_PATH");
        if (chromeDriverPath != null && !chromeDriverPath.isBlank()) {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        }

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get(BASE_URL);
    }

    @Test(priority = 1, description = "Positive test: valid new-user signup starts successfully")
    public void validSignupShouldOpenAccountInformationPage() {
        String uniqueEmail =
                "c213070r." + System.currentTimeMillis() + "@example.com";

        // Locator type 1: name
        WebElement nameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("name"))
        );
        nameField.sendKeys("C213070R Student");

        // Locator type 2: xpath
        WebElement signupEmail = driver.findElement(
                By.xpath("//form[@action='/signup']//input[@name='email']")
        );
        signupEmail.sendKeys(uniqueEmail);

        // Locator type 3: cssSelector
        driver.findElement(By.cssSelector("button[data-qa='signup-button']")).click();

        wait.until(ExpectedConditions.urlContains("/signup"));

        WebElement accountInformationHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//b[contains(" +
                                "translate(normalize-space(.)," +
                                "'abcdefghijklmnopqrstuvwxyz'," +
                                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                                "'ENTER ACCOUNT INFORMATION')]"
                        )
                )
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/signup"),
                "Expected URL to contain /signup."
        );
        Assert.assertTrue(
                accountInformationHeading.isDisplayed(),
                "Account Information heading should be displayed."
        );
    }

    @Test(priority = 2, description = "Negative test: invalid credentials show an error")
    public void invalidLoginShouldShowErrorMessage() {
        WebElement loginEmail = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//form[@action='/login']//input[@name='email']")
                )
        );
        loginEmail.sendKeys("invalid.c213070r@example.com");

        driver.findElement(By.name("password")).sendKeys("WrongPassword123!");

        driver.findElement(By.cssSelector("button[data-qa='login-button']")).click();

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//form[@action='/login']//p[" +
                                "contains(normalize-space(.)," +
                                "'Your email or password is incorrect!')]"
                        )
                )
        );

        Assert.assertTrue(
                errorMessage.isDisplayed(),
                "Invalid-login error message should be visible."
        );
        Assert.assertEquals(
                errorMessage.getText().trim(),
                "Your email or password is incorrect!",
                "Unexpected invalid-login message."
        );
    }

    @Test(priority = 3, description = "Validation test: verify page URL, title and sections")
    public void loginPageShouldDisplayRequiredSections() {
        WebElement loginHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".login-form h2")
                )
        );

        WebElement signupHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".signup-form h2")
                )
        );

        Assert.assertEquals(
                driver.getCurrentUrl(),
                BASE_URL,
                "The browser should remain on the assigned login URL."
        );
        Assert.assertTrue(
                driver.getTitle().contains("Signup / Login"),
                "Page title should contain 'Signup / Login'."
        );
        Assert.assertEquals(
                loginHeading.getText().trim(),
                "Login to your account",
                "Login heading is incorrect."
        );
        Assert.assertEquals(
                signupHeading.getText().trim(),
                "New User Signup!",
                "Signup heading is incorrect."
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
