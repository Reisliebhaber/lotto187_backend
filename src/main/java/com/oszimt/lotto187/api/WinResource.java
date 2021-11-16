package com.oszimt.lotto187.api;

import com.oszimt.lotto187.domain.Payout;
import com.oszimt.lotto187.domain.Tip;
import com.oszimt.lotto187.domain.User;
import com.oszimt.lotto187.domain.WinningClasses;
import com.oszimt.lotto187.repository.PayoutRepo;
import com.oszimt.lotto187.repository.WinningClassesRepo;
import com.oszimt.lotto187.service.TipService;
import com.oszimt.lotto187.service.WinService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WinResource {
    private final PayoutRepo payoutRepo;
    private final WinningClassesRepo winningClassesRepo;
    private final WinService winService;
    private final TipService tipService;

    @PostMapping("/employee/payout/save")//TODO rename
    public ResponseEntity<Payout> savePayout(@RequestParam Payout payout) {
        return ResponseEntity.ok().body(payoutRepo.save(payout));
    }

    @GetMapping("/payout")
    public ResponseEntity<List<WinClassToPayout>> getPayout(@RequestParam User user) {
        List<Tip> tips = tipService.getTipsByUsername(user.getUsername());
        //idea is to filter if there are any Winning Classes & Payout for the tip given and then add tip and
        //the winning classes & payout to a List of WinClassToPayout
        List<WinClassToPayout> winPayoutResponse = tips.stream().filter(tip -> payoutRepo.existsByTips_Id(tip.getId())
                        && winningClassesRepo.existsByTips_Id(tip.getId()))
                .map(filteredTip -> new WinClassToPayout(filteredTip,
                        payoutRepo.findByTips_Id(filteredTip.getId()).get(0),
                        winningClassesRepo.findByTips_Id(filteredTip.getId()).get(0)))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(winPayoutResponse);
    }
}

@Data
@AllArgsConstructor
class WinClassToPayout {
    private Tip tip;
    private Payout payout;
    private WinningClasses winningClasses;
}

