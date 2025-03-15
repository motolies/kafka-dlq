package com.kafka.dlq.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.kafka.default-dlq")
public class KafkaDlqProperty {
  private boolean enableDlq;
  private String dlqName;
  private int maxAttempts;
  private long retryInterval;
}