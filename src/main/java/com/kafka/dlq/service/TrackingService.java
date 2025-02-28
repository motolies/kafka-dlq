package com.kafka.dlq.service;

import com.kafka.dlq.code.Constant;
import com.kafka.dlq.dto.TrackingUpdateMessage;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackingService {

  private final StreamBridge streamBridge;

  public void send(String message) {
    TrackingUpdateMessage msg = TrackingUpdateMessage.builder()
        .status(message)
        .build();
    streamBridge.send(Constant.TRACKING_TOPIC, msg);
  }

}
