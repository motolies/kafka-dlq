package com.kafka.dlq.service;

import com.kafka.dlq.demain.DeadLetter;
import com.kafka.dlq.infra.DeadLetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeadLetterService {

  private final DeadLetterRepository deadLetterRepository;

  @Transactional
  public void save(DeadLetter deadLetter) {
    deadLetterRepository.save(deadLetter);
  }

}
