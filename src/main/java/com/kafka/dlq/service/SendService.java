package com.kafka.dlq.service;

import com.kafka.dlq.code.Constant;
import com.kafka.dlq.dto.TopicMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendService {

  private final StreamBridge streamBridge;

  public void send(String message) {

    TopicMessage msg = TopicMessage.builder()
        .message(message)
        .build();

    streamBridge.send(Constant.NORMAL_TOPIC, msg);
  }

}
