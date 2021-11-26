package com.oszimt.lotto187.service;

import com.oszimt.lotto187.domain.LottoNumbers;
import com.oszimt.lotto187.domain.Tip;
import com.oszimt.lotto187.domain.WinningClasses;

import java.util.HashMap;
import java.util.List;

public interface WinService {
    WinningClasses calculateWinningClass(LottoNumbers lotteryNumbers, Tip playerTip);
    WinningClasses savePlayerWin(Tip playerTip, WinningClasses winningClass);
    List<Integer> strTipsToList(String tipsAsString);
    int calculateHits(List<Integer> tips, List<Integer> winningSequence);
    HashMap<Integer,Integer> calculateLottoNumberStatistic();
    LottoNumbers calculateCurrentLottoNumbers();
    WinningClasses determineWinClassWithSpecificTipOnly(long tipId);
}
