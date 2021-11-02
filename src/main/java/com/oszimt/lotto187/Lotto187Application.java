package com.oszimt.lotto187;

import com.oszimt.lotto187.domain.Role;
import com.oszimt.lotto187.domain.Tip;
import com.oszimt.lotto187.domain.User;
import com.oszimt.lotto187.service.TipService;
import com.oszimt.lotto187.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

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
    CommandLineRunner run(UserService userService, TipService tipService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_EMPLOYEE"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "Maxis Cock", "maxi", "12345", new ArrayList<>()));
            userService.saveUser(new User(null, "Lams Cock", "lami", "12345", new ArrayList<>()));
            userService.saveUser(new User(null, "Tysriesen Cock", "ty", "12345", new ArrayList<>()));
            userService.saveUser(new User(null, "Trinityforce Cock", "arnold", "12345", new ArrayList<>()));


            userService.addRoleToUser("maxi", "ROLE_USER");
            userService.addRoleToUser("maxi", "ROLE_EMPLOYEE");
            userService.addRoleToUser("lami", "ROLE_EMPLOYEE");
            userService.addRoleToUser("ty", "ROLE_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_USER");
            userService.addRoleToUser("arnold", "ROLE_EMPLOYEE");
            userService.addRoleToUser("arnold", "ROLE_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");

            tipService.saveTipWithUsername("maxi", new Tip(null, "1;2;3;3;2;1", 0, new ArrayList<>()));
            tipService.saveTipWithUsername("maxi", new Tip(null, "4;5;6;7;8;9", 10, new ArrayList<>()));
            tipService.saveTipWithUsername("ty", new Tip(null, "6;5;4;3;2;1", 6, new ArrayList<>()));
            //tipService.getTips("maxi").forEach(tip -> System.out.println(tip.getTips()));
        };
    }
}
