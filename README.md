What is YAML file ?
  -> It stores configuration same as application.properties file but in this , indentation is used.
  -> Ex. In application.properties : app.name=Journal_app 
         In applicaion.yaml        : app:
                                       name: Journal_app
  -> The Precedence is given to the application.properties file in case of same property is written in both Configuration file.
  -> Commnd Line Arguments > application.properties > application.yml

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

# Mocking in Unit Testing

## What is Mocking?

Mocking is a testing technique used to create **fake versions of real objects**, known as **Test Doubles**, so that code can be tested without relying on actual databases, APIs, services, or external systems.

### Why Use Mocking?

* Test components in isolation.
* Control test conditions.
* Simulate errors and edge cases.
* Make tests faster and more reliable.

---

# Types of Test Doubles

## 1. Mock

A **Mock** is used to verify interactions between objects.

### Characteristics

* Checks whether methods were called correctly.
* Verifies method arguments.
* Fails the test if expectations are not met.

### Example

Verify that `sendEmail()` is called after successful user registration.

```java
verify(emailService).sendEmail(user);
```

---

## 2. Stub

A **Stub** provides predefined responses to method calls.

### Characteristics

* Returns fixed data.
* Does not verify interactions.
* Used to isolate dependencies.

### Example

Return a predefined user object instead of querying a real database.

```java
when(userRepository.findById(1L))
    .thenReturn(user);
```

---

## 3. Fake

A **Fake** is a simplified working implementation of a real component.

### Characteristics

* Contains actual working logic.
* Simpler than the real implementation.
* Commonly used for testing purposes.

### Example

Using an in-memory database instead of a real MySQL database.

```java
Map<Long, User> fakeDatabase = new HashMap<>();
```

---

## 4. Spy

A **Spy** records interactions and allows verification later.

### Characteristics

* Tracks method calls.
* Allows inspection after execution.
* Does not automatically fail tests.

### Example

Track how many times a method is called.

```java
verify(userService, times(2))
    .getUserById(1L);
```

---

# Quick Comparison

| Type | Purpose                   | Verifies Calls    | Returns Data       |
| ---- | ------------------------- | ----------------- | ------------------ |
| Mock | Verify interactions       | ✅ Yes             | Optional           |
| Stub | Provide test data         | ❌ No              | ✅ Yes              |
| Fake | Simplified implementation | ❌ No              | ✅ Yes              |
| Spy  | Record interactions       | ✅ After execution | Uses real behavior |

-------------------------------------------------------------------------------------------------------------------------------------------------------------



**Mocking = Creating fake objects (Test Doubles) to isolate code, control behavior, and make unit tests fast and reliable.**
