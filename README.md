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

# Spring Boot Logging — SLF4J & Logback

## Definition
Spring Boot logging records runtime application events using **SLF4J** (API) 
and **Logback** (implementation), auto-configured with zero setup.

## Architecture
Your Code → SLF4J API → Logback → Appenders (Console / File / External)

## Key Concepts
| Concept | Role |
|---------|------|
| SLF4J | Logging facade/API — decouples code from implementation |
| Logback | Default implementation — formats and writes logs |
| Log Level | TRACE < DEBUG < INFO < WARN < ERROR |
| Appender | Output destination (Console, File, Rolling, External) |
| MDC | Thread-local context (attach requestId, userId) |

## Quick Setup (application.properties)
```properties
logging.level.root=WARN
logging.level.com.myapp=DEBUG
logging.file.name=logs/app.log
logging.pattern.console=%d{HH:mm:ss} %-5level [%logger{20}] - %msg%n
```

## Usage
```java
private static final Logger log = LoggerFactory.getLogger(MyService.class);
log.info("Processing order: {}", orderId);
log.error("Payment failed for order: {}", orderId, exception);
```

## Interview Notes
- SLF4J is an API. Logback is the implementation. Spring Boot wires them.
- Use `logback-spring.xml` (not `logback.xml`) to support Spring profiles.
- Never use string concatenation in log args — use `{}` placeholders.
- To switch to Log4j2: exclude `spring-boot-starter-logging`, add `spring-boot-starter-log4j2`.
- MDC for request tracing: `MDC.put("requestId", uuid)`.

## Cheat Sheet
- Get logger: `LoggerFactory.getLogger(MyClass.class)`
- Levels: TRACE < DEBUG < INFO < WARN < ERROR
- Log with context: `log.info("User {} logged in", userId)`
- Config file: `logback-spring.xml`
- Rolling logs: `RollingFileAppender` + `TimeBasedRollingPolicy`

## locback.xml file Example: 
-> if spring find this file in project directory so it will use this file as configuration for the logging as first choice.
<!-- <configuration>

    <appender name = "myConsoleAppender" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>
                %d{HH:mm:ss} %-5level [%logger{20}] - %msg%n
            </pattern> 
        </encoder>
    </appender>

    <appender name = "myFileAppender" class="ch.qos.logback.core.FileAppender">
    <encoder>
            <pattern>
                %d{HH:mm:ss} %-5level [%logger{20}] - %msg%n
            </pattern> 
        </encoder>
        <file>
            journalApp.log
        </file>
    </appender>

    <root level="INFO">
        <appender-ref ref="myConsoleAppender"/>
        <appender-ref ref="myFileAppender"/>
    </root>

</configuration> -->


## profile annotation : 
it is used when we want to make some classes to restriction or changes the profile of some classes like 5 files have to access when there is a dev. profile is activate so we can use @Profile("dev") on the required classes


