package com.kafka.dlq.event;

import com.kafka.dlq.dto.TrackingUpdateMessage;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TrackingConsumer {

  /**
   * 아래 3가지는 같아야 함
   * <pre>
   *   - @Bean name
   *   - spring.cloud.function.definition
   *   - spring.cloud.stream.bindings.<channelName>
   *   </pre>
   */

  @Bean
  public Consumer<TrackingUpdateMessage> trackingUpdate() {
    return message -> {
      if (message.getStatus().contains("error")) {
        throw new RuntimeException("Error occurred");
      }
      log.debug("Received message: {}", message);
    };
  }

}
