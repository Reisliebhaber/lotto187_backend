package com.oszimt.lotto187.service;

import com.oszimt.lotto187.domain.Tip;
import com.oszimt.lotto187.domain.Winnings;
import com.oszimt.lotto187.lottofunc.WinningClasses;

import java.util.List;

public interface WinService {
    WinningClasses calculateWinningClass(Tip lotteryNumbers, Tip playerTip);
    Winnings savePlayerWin(Tip playerTip, WinningClasses winningClass);
    List<Integer> strTipsToList(String tipsAsString);
    int calculateWinningClass(List<Integer> tips, List<Integer> winningSequence);
}
