# CRON Job Testing Project

This project demonstrates how to implement scheduled tasks using Spring Boot with CRON expressions. It includes the steps necessary to enable scheduling and create scheduled tasks.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Steps](#steps)
- [fixedDelay vs fixedRate](#fixeddelay-vs-fixedrate)
- [Using CRON Expressions](#using-cron-expressions)
  - [CRON Expression Syntax](#cron-expression-syntax)
  - [Special Characters](#special-characters)
  - [Common Examples of CRON Expressions](#common-examples-of-cron-expressions)
  - [Important Considerations](#important-considerations)

## Prerequisites
- Java 17 or higher
- Maven
- Spring Boot

## Steps

### 1. Add `@EnableScheduling`

To enable scheduling in your Spring Boot application, annotate your main application class with `@EnableScheduling`.

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### 2. Create a Component with `@Scheduled` methods

Next, create a component class where you will define your scheduled tasks. Use the `@Scheduled` annotation on the methods you want to execute at specific intervals.

```java
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SimpleCronJob {

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedDelay = 10000) // Runs every 10 seconds after the last execution
    public void executeTaskWithFixedDelay() {
        String formattedTime = LocalDateTime.now().format(TIME_FORMATTER);
        System.out.println("Task executed at (fixedDelay): " + formattedTime);
    }

    @Scheduled(fixedRate = 10000) // Runs every 10 seconds
    public void executeTaskWithFixedRate() {
        String formattedTime = LocalDateTime.now().format(TIME_FORMATTER);
        System.out.println("Task executed at (fixedRate): " + formattedTime);
    }

    @Scheduled(cron = "*/10 * * * * *") // Runs every 10 seconds
    public void executeTaskWithCron() {
        String formattedTime = LocalDateTime.now().format(TIME_FORMATTER);
        System.out.println("Task executed at (cron): " + formattedTime);
    }
}
```

## fixedDelay vs fixedRate

`fixedDelay`: The method will be executed at a fixed interval after the completion of the previous execution. 

For example, if you set `fixedDelay = 10000`, the method will wait for 10 seconds after the previous execution completes before running again.

`fixedRate`: The method will be executed at a fixed interval regardless of the completion of the previous execution. 

For example, if you set `fixedRate = 10000`, the method will run every 10 seconds from the start of the last execution. If a task takes longer than 10 seconds, multiple instances of the task could run concurrently.

## Using CRON Expressions

CRON expressions are a powerful way to schedule tasks in Unix-like operating systems and applications like Spring Boot. A CRON expression is a string consisting of six or seven fields (depending on the implementation) that represent different time units. Each field can contain specific values, ranges, or special characters that define when a scheduled task should run.

### CRON Expression Syntax

A CRON expression in Spring (and most CRON implementations) typically consists of six fields:

```sql
second minute hour day month day-of-week
```

Here's what each field represents:

- **Second (0-59)**: The seconds within a minute.
- **Minute (0-59)**: The minutes within an hour.
- **Hour (0-23)**: The hour of the day.
- **Day of Month (1-31)**: The day of the month.
- **Month (1-12 or JAN-DEC)**: The month of the year.
- **Day of Week (0-6 or SUN-SAT)**: The day of the week (0 for Sunday, 1 for Monday, etc.).

### Special Characters

- `*` **(All values)**: Represents all values for a field. For example, `*` in the minute field means "every minute."
- `?` **(No specific value)**: Used in the day-of-month and day-of-week fields to specify "no specific value." It is mainly used when you need to specify one of the two fields.
- `-` **(Range)**: Used to specify a range of values. For example, `1-5` means from the 1st to the 5th.
- `,` **(List)**: Used to specify a list of values. For example, `MON,WED,FRI` means Monday, Wednesday, and Friday.
- `/` **(Increment)**: Used to specify increments. For example, `*/5` in the minute field means every 5 minutes.

### Common Examples of CRON Expressions

Here are some common CRON expressions and their meanings:

1. Every 10 seconds
```java
*/10 * * * * *
```
2. Every Minute
```java
0 * * * * *
```
3. Every 5 Minutes
```java
0 */5 * * * *
```
4. Every 5 Minutes from 5 PM to 8 PM
```java
0 0/5 17-20 * * ?
```
5. Every Hour
```java
0 0 * * * *
```
6. Every Day at Midnight
```java
0 0 0 * * *
```
7. Every Monday at 10 AM
```java
0 0 10 * * MON
```
8. Every Month on the 1st at Midnight
```java
0 0 0 1 * ?
```

### Important Considerations

- **Time Zone**: By default, CRON expressions are evaluated in the server's local time zone. If you need to use a different time zone, you can specify it using the `zone` attribute of the `@Scheduled` annotation.

```java
@Scheduled(cron = "0 0 12 * * ?", zone = "America/New_York")
```

- **Concurrency**: If your scheduled task takes longer to execute than the interval between executions, you may want to handle concurrency appropriately, possibly by configuring the task executor.


- **Error Handling**: Ensure you have error handling in place for your scheduled tasks, as exceptions thrown during execution can cause the task to fail.# CronJob-Spring-Testing
