package com.oszimt.lotto187.api;

import com.oszimt.lotto187.domain.Tip;
import com.oszimt.lotto187.domain.User;
import com.oszimt.lotto187.service.TipService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TipResource {
    private final TipService tipService;
/*
    @GetMapping("/tips")
    public ResponseEntity<List<Tip>> getTips() {
        return ResponseEntity.ok().body(tipService.getTipsByUsername(""));
    }*/

    @PostMapping("/tips")
    public ResponseEntity<List<Tip>> getUserTips(@RequestBody User user) {
        return ResponseEntity.ok().body(tipService.getTipsByUsername(user.getUsername()));
    }

    @PostMapping("/tip/save")
    public ResponseEntity<Tip> saveUser(@RequestBody TipAndUser tipAndUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(tipService.saveTipWithUsername(tipAndUser.getUser().getUsername(), tipAndUser.getTip()));//instead of default return 200 --> 202 which we personally define
    }
}

@Data
class TipAndUser {
    private Tip tip;
    private User user;
}