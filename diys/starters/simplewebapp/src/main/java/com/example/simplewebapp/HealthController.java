package com.example.simplewebapp;

import com.example.simplewebapp.domain.Health;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    private Health getHealth() {
        Health h = new Health();
        h.setStatus("OK");
        return h;
    }

}
