package com.oszimt.lotto187.service;

import com.oszimt.lotto187.domain.Tip;
import com.oszimt.lotto187.domain.User;
import com.oszimt.lotto187.domain.WinningClasses;
import com.oszimt.lotto187.repository.TipRepo;
import com.oszimt.lotto187.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TipServiceImpl implements TipService {
    private final TipRepo tipRepo;
    private final UserRepo userRepo;
    private final WinServiceImpl winService;

    @Override
    public Tip saveTip(Tip tip) {
        log.info("Saving new Tip {} to the database");
        return tipRepo.save(tip);
    }

    @Override
    public Tip saveTipWithUsername(String username, Tip tip) {
        User player = userRepo.findByUsername(username);
        log.info("Saving new Tip of User {} to the database", player.getName());
        Tip tipSaved = tipRepo.save(tip);
        tipSaved.getUsers().add(player);
        winService.calculateWinningClass(winService.calculateCurrentLottoNumbers(), tipSaved);
        return tipSaved;
    }

    @Override
    public Tip getTip(Long id) {
        User player = tipRepo.getById(id).getUsers().iterator().next();
        log.info("Fetching tip {} of user {}", id, player.getName());
        return tipRepo.getById(id);
    }

    @Override
    public List<Tip> getTipsByUsername(String username) {
        User player = userRepo.findByUsername(username);
        log.info("Fetching all tips of user {}", username);
        return tipRepo.findAllByUsers(player);
    }

    @Override//TODO can this be removed?
    public List<WinningClasses> getWinningsByUsername(String username) {
        User player = userRepo.findByUsername(username);
        log.info("Fetching all winnings of user {}", username);
        return null;//winningsRepo.findAllByUsers(player);
    }
/*
    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }*/

}
