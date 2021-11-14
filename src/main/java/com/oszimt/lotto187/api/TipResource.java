package com.oszimt.lotto187.api;

import com.oszimt.lotto187.domain.Tip;
import com.oszimt.lotto187.domain.User;
import com.oszimt.lotto187.service.TipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class TipResource {
    private final TipService tipService;

    @GetMapping("/tips")
    public ResponseEntity<List<Tip>> getTips() {
        return ResponseEntity.ok().body(tipService.getTipsByUsername(""));
    }
}
