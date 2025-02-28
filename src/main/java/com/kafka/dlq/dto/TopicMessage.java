package com.kafka.dlq.dto;

import io.hypersistence.tsid.TSID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicMessage {

  @Builder.Default
  private String id = TSID.fast().toString();
  @Builder.Default
  private int retryCount = 0;
  private String message;

}
