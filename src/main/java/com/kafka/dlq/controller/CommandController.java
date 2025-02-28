package com.kafka.dlq.controller;

import com.kafka.dlq.service.SendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/cmd", produces = "application/json")
public class CommandController {

  private final SendService sendService;

  @GetMapping("/send")
  public ResponseEntity<?> send() {
    sendService.send("Hello Kafka");
    return ResponseEntity.ok().build();
  }

  @GetMapping("/send/error")
  public ResponseEntity<?> sendError() {
    sendService.send("error");
    return ResponseEntity.ok().build();
  }

}
