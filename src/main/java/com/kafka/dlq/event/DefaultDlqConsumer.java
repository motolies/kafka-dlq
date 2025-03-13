package com.kafka.dlq.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.dlq.demain.DeadLetter;
import com.kafka.dlq.dto.BaseMessage;
import com.kafka.dlq.service.DeadLetterService;
import io.hypersistence.tsid.TSID;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultDlqConsumer {

  private final DeadLetterService deadLetterService;
  private final ObjectMapper objectMapper;

  @Bean
  public Consumer<Message<String>> defaultDlq() {
    return message -> {
      String originalTopic = getStringValue(message, "x-original-topic", "anonymous-topic");
      String exceptionMessage = getStringValue(message, "x-exception-message", null);
      String exceptionStackTrace = getStringValue(message, "x-exception-stacktrace", null);

      BaseMessage baseMessage = null;
      try {
        baseMessage = objectMapper.readValue(message.getPayload(), BaseMessage.class);
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }

      deadLetterService.save(DeadLetter.builder()
          .id(TSID.from(baseMessage.getId()).toLong())
          .topic(originalTopic)
          .message(message.getPayload())
          .exceptionMessage(exceptionMessage)
          .exceptionStackTrace(exceptionStackTrace)
          .build());

      log.warn("#### default DLQ from topic [{}] : {}", originalTopic, message.getPayload());
    };
  }

  private String getStringValue(Message<String> message, String headerName){
    return getStringValue(message, headerName, null);
  }

  private String getStringValue(Message<String> message, String headerName, String defaultValue) {
    byte[] bytesMessage = (byte[]) message.getHeaders().get(headerName);
    if (bytesMessage == null) {
      return StringUtils.hasText(defaultValue) ? defaultValue : null;
    }
    return IOUtils.toString(bytesMessage, String.valueOf(StandardCharsets.UTF_8));
  }
}
