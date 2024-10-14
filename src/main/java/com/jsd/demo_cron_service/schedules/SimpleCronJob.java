package com.jsd.demo_cron_service.schedules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimpleCronJob {

  private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

  // Example: Scheduled every 30s minutes using fixed rate (useful for testing)
  @Scheduled(fixedRate = 30000) // 0.5 minutes in milliseconds
  private void repetitiveTask() {
    String formattedTime = LocalDateTime.now().format(TIME_FORMATTER);
    System.out.println("Repetitive task running at: " + formattedTime);
  }

  // Example: Scheduled every 60s minutes using cron expression
  @Scheduled(cron = "1 * * * * *")
  private void periodicTask() {
    String formattedTime = LocalDateTime.now().format(TIME_FORMATTER);
    System.out.println("Periodic task running at: " + formattedTime);
  }
}
