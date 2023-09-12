package com.example.demo.controllers;

import com.example.demo.exception.NoMessageException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/message")
public class RestController {
    private int count = 3;
    private Map<Integer, String> messages = new HashMap<>() {{
        put(1, "First message");
        put(2, "Second message");
        put(3, "Third message");
    }};

    @GetMapping
    public Map<Integer, String> getMessage() {
        return messages;
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id) {
        if (messages.get(id) == null) {
            throw new NoMessageException();
        }
        return messages.get(id);
    }

    @PostMapping
    public String addMessage(@RequestBody String newMessage) {
        messages.put(count++, newMessage);
        return newMessage;
    }

    @PutMapping("/{id}")
    public String updateMessage(@PathVariable Integer id, @RequestBody String newMessage) {
        messages.remove(id);
        messages.put(id,newMessage);
        return messages.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        messages.remove(id);
    }


}
