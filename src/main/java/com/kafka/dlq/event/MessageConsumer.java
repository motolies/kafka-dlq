package com.kafka.dlq.event;

import com.kafka.dlq.dto.TopicMessage;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConsumer {

  /**
   *   아래 3가지는 같아야 함
   *   <pre>
   *   - @Bean name
   *   - spring.cloud.function.definition
   *   - spring.cloud.stream.bindings.<channelName>
   *   </pre>
   */

  @Bean
  public Consumer<TopicMessage> normalMessage() {
    return message -> {
      if (message.getMessage().contains("error")) {
        throw new RuntimeException("Error occurred");
      }
      log.debug("Received message: {}", message);
    };
  }

}
