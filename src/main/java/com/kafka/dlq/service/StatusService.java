package com.kafka.dlq.service;

import com.kafka.dlq.code.Constant;
import com.kafka.dlq.dto.StatusUpdateMessage;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusService {

  private final StreamBridge streamBridge;

  public void send(String message) {
    StatusUpdateMessage msg = StatusUpdateMessage.builder()
        .status(message)
        .build();
    streamBridge.send(Constant.STATUS_TOPIC, msg);
  }

}
