package com.kafka.dlq.demain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.hypersistence.tsid.TSID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Entity
@Table(name = "`dead_letter`")
@Getter
@Setter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class DeadLetter {

  @Id
  private Long id;

  @JsonGetter("id")
  public String getHexId() {
    return TSID.from(this.id).toString();
  }

  @JsonSetter("id")
  public void setHexId(String id) {
    this.id = TSID.from(id).toLong();
  }

  @Column(nullable = false, length = 64)
  private String topic;

  @Column(nullable = false, length = 512)
  private String message;

  @Column(columnDefinition = "TEXT")
  private String exceptionMessage;

  @Column(columnDefinition = "TEXT")
  private String exceptionStackTrace;

  private LocalDateTime messageCreatedOn;

  @PrePersist
  public void prePersist() {
    if (this.messageCreatedOn == null) {
      this.messageCreatedOn = Instant.ofEpochMilli(TSID.from(this.id).getUnixMilliseconds())
          .atZone(ZoneId.systemDefault())
          .toLocalDateTime();
    }
  }

}
