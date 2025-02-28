package com.kafka.dlq.infra;

import com.kafka.dlq.demain.DeadLetter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeadLetterRepository extends JpaRepository<DeadLetter, Long> {

}
