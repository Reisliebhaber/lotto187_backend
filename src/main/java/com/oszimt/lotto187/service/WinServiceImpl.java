package com.oszimt.lotto187.service;


import com.oszimt.lotto187.domain.LottoNumbers;
import com.oszimt.lotto187.domain.Tip;
import com.oszimt.lotto187.domain.WinningClasses;
import com.oszimt.lotto187.repository.LottoNumberRepo;
import com.oszimt.lotto187.repository.TipRepo;
import com.oszimt.lotto187.repository.UserRepo;
import com.oszimt.lotto187.repository.WinningClassesRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WinServiceImpl implements WinService {
    private final TipRepo tipRepo;
    private final UserRepo userRepo;
    private final WinningClassesRepo winningClassesRepo;
    private final LottoNumberRepo lottoNumberRepo;

    //method, which takes 2 Tip-Objects and compares its attributes 'tips' & 'superNumber' and calculates Lotto algorithm
    // finally  creating a Winnings instance
    @Override
    public WinningClasses calculateWinningClass(LottoNumbers lotteryNumbers, Tip playerTip) {
        List<Integer> playersTips = strTipsToList(playerTip.getTips());
        List<Integer> lotteryNumbs = strTipsToList(lotteryNumbers.getLottoNumbers());
        int hits = calculateHits(playersTips, lotteryNumbs);
        // Exception can only occur if winningClassCalc > 9, but therefore .count() must return a negative int in calculateWinningClass()
        WinningClasses winningClass = winningClassesRepo.findByHitsAndIsSuperHit(hits, (lotteryNumbers.getSuperNumber() == playerTip.getSuperNumber()));
        return savePlayerWin(playerTip, (winningClass != null) ? winningClass : winningClassesRepo.findByHitsAndIsSuperHit(0, false));
    }

    //TODO save winningClasses with Tip --> Winnings with Tip and / or User
    @Override
    public WinningClasses savePlayerWin(Tip playerTip, WinningClasses winningClass) {
        winningClass.getTips().add(playerTip);
        log.info("Player tip equals {}", winningClass.getWinningClass());
        return winningClass;
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
    public int calculateHits(List<Integer> tips, List<Integer> winningSequence) {
        return ((Long) tips.stream().filter(winningSequence::contains).count()).intValue();
    }

    @Override // TODO if Time
    public HashMap<Integer, Integer> calculateLottoNumberStatistic() {
        List<Integer> allLottoNumbers = new ArrayList<>();
        HashMap<Integer, Integer> lottoNumberStatistic = new HashMap<>();
        lottoNumberRepo.findAll().forEach(x -> Arrays.stream(x.getLottoNumbers().split(";"))
                .map(Integer::parseInt).forEachOrdered(allLottoNumbers::add));
        /*lottoNumberStatistic = allLottoNumbers.stream().collect(
                Collectors.groupingBy(num -> Integer.parseInt(num.toString()),
                Collectors.summingInt(a -> 1)));

        Map<Integer, Integer> monthsToCounts =
        people.stream().collect(
                Collectors.groupingBy(p -> p.getBirthday().getMonthValue(),
                Collectors.summingInt(a -> 1)));
         */
        //lottoNumberRepo.findAll().forEach(x -> Arrays.stream(x.getLottoNumbers().split(";")).);.collect(Collectors.toMap(LottoNumbers::getId, Function.identity()));
        return null;
    }
}
