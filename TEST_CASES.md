# Test Case List

## TC-01 — Positive Signup Navigation

**Purpose:** Verify that a new name and unique email start the signup process.

**Steps:**
1. Open `https://automationexercise.com/login`.
2. Enter a valid name.
3. Enter a unique valid email.
4. Click **Signup**.

**Expected result:**
- The URL contains `/signup`.
- The **ENTER ACCOUNT INFORMATION** heading is visible.

---

## TC-02 — Invalid Login

**Purpose:** Verify error handling for incorrect credentials.

**Steps:**
1. Open the login page.
2. Enter a valid-format but unregistered email.
3. Enter an incorrect password.
4. Click **Login**.

**Expected result:**
- The message **Your email or password is incorrect!** is visible.

---

## TC-03 — Login Page Validation

**Purpose:** Verify navigation and required UI sections.

**Steps:**
1. Open the assigned login URL.
2. Check the URL and page title.
3. Check the login and signup headings.

**Expected result:**
- URL is the assigned login URL.
- Title contains **Signup / Login**.
- **Login to your account** is visible.
- **New User Signup!** is visible.

# Selenium Locator Types Demonstrated

1. `By.name()`
2. `By.xpath()`
3. `By.cssSelector()`

# Assertions Demonstrated

- `Assert.assertTrue()`
- `Assert.assertEquals()`
