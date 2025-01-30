package com.spring.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ollama")
public class OllamaController {


    private ChatClient client;

    public OllamaController(ChatClient.Builder client) {
        this.client = client.build();
    }

    @GetMapping("/chat")
    public ResponseEntity<Object> getChat(@RequestBody RequestModel requestModel) {
        try {
            Object response = client.prompt(requestModel.getText()).call().content();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}