package com.oszimt.lotto187.api;

import com.oszimt.lotto187.domain.Tip;
import com.oszimt.lotto187.domain.User;
import com.oszimt.lotto187.domain.WinningClasses;
import com.oszimt.lotto187.repository.WinningClassesRepo;
import com.oszimt.lotto187.service.TipService;
import com.oszimt.lotto187.service.WinService;
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
    private final WinningClassesRepo winningClassesRepo;
    private final WinService winService;
    private final TipService tipService;

    // TODO Connect Payout to Tip or User?, Danger would be if a person saves the tip via tip/save and adds a payout to the tip
    @PutMapping("/employee/payout/save")
    public ResponseEntity<Tip> savePayout(@RequestBody TipIdAndPayout tipIdAndPayout) {
        Tip tip = tipService.getTip(tipIdAndPayout.getTipId());
        tip.setPayout(tipIdAndPayout.getPayout());
        return ResponseEntity.ok().body(tipService.saveTip(tip));
    }

    @GetMapping("/payout")
    public ResponseEntity<List<WinningClasses>> getPayout(@RequestBody User user) {
        List<Tip> tips = tipService.getTipsByUsername(user.getUsername());
        //idea is to filter if there are any Winning Classes & Payout for the tip given and then add tip and
        //the winning classes & payout to a List of WinClassToPayout
        List<WinningClasses> winPayoutResponse = tips.stream().filter(tip -> (tip.getPayout() >= 0)
                        && winningClassesRepo.existsByTips_Id(tip.getId()))
                .map(filteredTip -> winningClassesRepo.findByTips_Id(filteredTip.getId()).get(0))
                .distinct()
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(winPayoutResponse);
    }
}

@Data
class TipIdAndPayout {
    private long tipId;
    private double payout;
}
