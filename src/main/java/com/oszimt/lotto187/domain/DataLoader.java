package com.oszimt.lotto187.domain;

import com.oszimt.lotto187.repository.WinningClassesRepo;
import com.oszimt.lotto187.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataLoader implements ApplicationRunner {

    private UserService userService;
    private WinningClassesRepo winningClassesRepo;

    @Autowired
    public DataLoader(UserService userService, WinningClassesRepo winningClassesRepo) {
        this.userService = userService;
        this.winningClassesRepo = winningClassesRepo;
    }

    public void run(ApplicationArguments args) {
        userService.saveRole(new Role(null, "ROLE_USER"));
        userService.saveRole(new Role(null, "ROLE_EMPLOYEE"));
        userService.saveRole(new Role(null, "ROLE_ADMIN"));
        userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

        WinningClasses win1 = new WinningClasses(null, "Gewinnklasse 1", 6, true, new ArrayList<>());
        WinningClasses win2 = new WinningClasses(null, "Gewinnklasse 2", 6, false, new ArrayList<>());
        WinningClasses win3 = new WinningClasses(null, "Gewinnklasse 3", 5, true, new ArrayList<>());
        WinningClasses win4 = new WinningClasses(null, "Gewinnklasse 4", 5, false, new ArrayList<>());
        WinningClasses win5 = new WinningClasses(null, "Gewinnklasse 5", 4, true, new ArrayList<>());
        WinningClasses win6 = new WinningClasses(null, "Gewinnklasse 6", 4, false, new ArrayList<>());
        WinningClasses win7 = new WinningClasses(null, "Gewinnklasse 7", 3, true, new ArrayList<>());
        WinningClasses win8 = new WinningClasses(null, "Gewinnklasse 8", 3, false, new ArrayList<>());
        WinningClasses win9 = new WinningClasses(null, "Gewinnklasse 9", 2, true, new ArrayList<>());
        WinningClasses rivet = new WinningClasses(null, "Niete", 0, false, new ArrayList<>());
        winningClassesRepo.save(win1);
        winningClassesRepo.save(win2);
        winningClassesRepo.save(win3);
        winningClassesRepo.save(win4);
        winningClassesRepo.save(win5);
        winningClassesRepo.save(win6);
        winningClassesRepo.save(win7);
        winningClassesRepo.save(win8);
        winningClassesRepo.save(win9);
        winningClassesRepo.save(rivet);
    }
}