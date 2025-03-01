package com.kafka.dlq.dto;

import io.hypersistence.tsid.TSID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseMessage {

  @Builder.Default
  private String id = TSID.fast().toString();
  @Builder.Default
  private int retryCount = 0;

}
