package com.kafka.dlq.event;

import com.kafka.dlq.dto.TopicMessage;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeadLetterQueConsumer {

  @Bean
  public Consumer<TopicMessage> dlqMessage() {
    return message -> {
      // todo : 여기서 별도 처리 로직을 작성한다.
      log.warn("@@ Received message from DLQ: {}", message);
    };
  }
}
