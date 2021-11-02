package com.oszimt.lotto187.service;

import com.oszimt.lotto187.domain.Tip;
import com.oszimt.lotto187.domain.Winnings;

import java.util.List;

public interface TipService {
    Tip saveTipWithUsername(String username, Tip tip);
    Tip getTip(Long id);
    List<Tip> getTipsByUsername(String username);
    List<Winnings> getWinningsByUsername(String username);
}
