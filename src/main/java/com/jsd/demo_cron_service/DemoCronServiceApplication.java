package com.jsd.demo_cron_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoCronServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoCronServiceApplication.class, args);
  }
}
