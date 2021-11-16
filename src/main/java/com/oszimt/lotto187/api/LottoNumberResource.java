package com.oszimt.lotto187.api;

import com.oszimt.lotto187.domain.LottoNumbers;
import com.oszimt.lotto187.domain.Tip;
import com.oszimt.lotto187.repository.LottoNumberRepo;
import com.oszimt.lotto187.service.TipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LottoNumberResource {
    private final LottoNumberRepo lottoNumberRepo;

    @GetMapping("/lottonumbers")
    public ResponseEntity<List<LottoNumbers>> getUserTips() {
        return ResponseEntity.ok().body(lottoNumberRepo.findAll());
    }
    @PostMapping("/employee/lottonumbers/save")
    public ResponseEntity<LottoNumbers> saveUser(@RequestBody LottoNumbers lottoNumbers) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(lottoNumberRepo.save(lottoNumbers));//instead of default return 200 --> 202 which we personally define
    }
}
