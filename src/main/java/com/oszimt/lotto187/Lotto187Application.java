package com.oszimt.lotto187;

import com.oszimt.lotto187.domain.LottoNumbers;
import com.oszimt.lotto187.domain.Tip;
import com.oszimt.lotto187.domain.User;
import com.oszimt.lotto187.repository.LottoNumberRepo;
import com.oszimt.lotto187.repository.UserRepo;
import com.oszimt.lotto187.service.TipService;
import com.oszimt.lotto187.service.UserService;
import com.oszimt.lotto187.service.WinService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class Lotto187Application {

    public static void main(String[] args) {
        SpringApplication.run(Lotto187Application.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService, TipService tipService, WinService winService, LottoNumberRepo lottoNumberRepo, UserRepo userRepo) {
        return args -> {

            Tip tysTip = new Tip(null, "6;15;4;3;2;1", 6, LocalDateTime.now(),0, new ArrayList<>());
            Tip maxisTip = new Tip(null, "1;2;3;3;2;1", 0, LocalDateTime.now(),0, new ArrayList<>());

            userService.saveUser(new User(null, "Maxis Cont", "maxi", "12345", new ArrayList<>()));
            userService.saveUser(new User(null, "Lams Cont", "lami", "12345", new ArrayList<>()));
            userService.saveUser(new User(null, "Tys Cont", "ty", "12345", new ArrayList<>()));
            userService.saveUser(new User(null, "Trinity Cont", "arnold", "12345", new ArrayList<>()));

            userService.addRoleToUser("maxi", "ROLE_USER");
            userService.addRoleToUser("maxi", "ROLE_EMPLOYEE");
            userService.addRoleToUser("lami", "ROLE_USER");
            userService.addRoleToUser("ty", "ROLE_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_USER");
            userService.addRoleToUser("arnold", "ROLE_EMPLOYEE");
            userService.addRoleToUser("arnold", "ROLE_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");

            tipService.saveTipWithUsername("maxi", maxisTip);
            tipService.saveTipWithUsername("maxi", new Tip(null, "4;5;6;7;8;9", 10, LocalDateTime.now(),0, new ArrayList<>()));
            tipService.saveTipWithUsername("ty", tysTip);
            maxisTip.setTips("4;5;6;7;30;31");
            maxisTip.setSuperNumber(6);
            tipService.saveTipWithUsername("lami", maxisTip);
            //tipService.getTips("maxi").forEach(tip -> System.out.println(tip.getTips()));
            //Winning Lotto:
            LottoNumbers lottoNumber = new LottoNumbers(null, "11;12;13;14;15;1", 6, LocalDateTime.now().minusDays(Long.valueOf(5)), new ArrayList<>());
            lottoNumberRepo.save(lottoNumber);
            LottoNumbers lottoNumber1 = new LottoNumbers(null, "36;34;33;32;31;30", 6, LocalDateTime.now().minusDays(Long.valueOf(1)), new ArrayList<>());
            lottoNumberRepo.save(lottoNumber1);
        };
    }
}
