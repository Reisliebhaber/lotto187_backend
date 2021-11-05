package com.oszimt.lotto187.service;


import com.oszimt.lotto187.domain.Tip;
import com.oszimt.lotto187.domain.Winnings;
import com.oszimt.lotto187.lottofunc.WinningClasses;
import com.oszimt.lotto187.repository.TipRepo;
import com.oszimt.lotto187.repository.UserRepo;
import com.oszimt.lotto187.repository.WinningsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WinServiceImpl implements WinService {
    private final TipRepo tipRepo;
    private final UserRepo userRepo;
    private final WinningsRepo winningsRepo;

    //method, which takes 2 Tip-Objects and compares its attributes 'tips' & 'superNumber' and calculates Lotto algorithm
    // finally  creating a Winnings instance
    @Override
    public WinningClasses calculateWinningClass(Tip lotteryNumbers, Tip playerTip) {
        //WinningClasses[]  winningClass = WinningClasses.values();
        int winningClassCalc;
        List<Integer> playersTips = strTipsToList(playerTip.getTips());
        List<Integer> lotteryNumbs = strTipsToList(lotteryNumbers.getTips());
        winningClassCalc = calculateWinningClass(playersTips, lotteryNumbs);
        winningClassCalc -= (lotteryNumbers.getSuperNumber() == playerTip.getSuperNumber()) ? 1 : 0;
        // Exception can only occur if winningClassCalc > 9, but therefore .count() must return a negative int in calculateWinningClass()
        return WinningClasses.values()[winningClassCalc];//winning class + Number Hits
    }

    //TODO save winningClasses with Tip --> Winnings with Tip and / or User
    @Override
    public Winnings savePlayerWin(Tip playerTip, WinningClasses winningClass) {
        Winnings playerWin = new Winnings(null, winningClass.getWinClass(), winningClass.getHits(), winningClass.isSuperHit(), new ArrayList<>());
        winningsRepo.save(playerWin);
        playerWin.getTips().add(playerTip);
        log.info("save Players {}", winningClass.getWinClass());
        return playerWin;
    }

    /**
     * Takes specific String e.g. "1;3;5;7" and parses it to a List of Integers  [1,3,5,7]
     *
     * @param tipsAsString Tips as String separated tips with ';'
     * @return List of Integers  [1,3,5,7]
     */
    @Override
    public List<Integer> strTipsToList(String tipsAsString) {
        return Arrays.stream(tipsAsString.split(";")).map(Integer::parseInt).collect(Collectors.toList());
    }

    @Override
    public int calculateWinningClass(List<Integer> tips, List<Integer> winningSequence) {
        int winningClass = 10;// index 1 equals 0?
        winningClass -= ((Long) tips.stream().filter(winningSequence::contains).count()).intValue();
        return winningClass;
    }
}
