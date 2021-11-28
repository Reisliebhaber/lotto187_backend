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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    CommandLineRunner run(UserService userService, TipService tipService, WinService winService, LottoNumberRepo lottoNumberRepo, UserRepo userRepo) {
        return args -> {

            Tip tysTip = new Tip(null, "6;15;4;3;2;1", 6, LocalDateTime.now(),0, new ArrayList<>());
            Tip maxisTip = new Tip(null, "1;2;3;3;2;1", 0, LocalDateTime.now(),0, new ArrayList<>());

            userService.saveUser(new User(null, "Maxis Cock", "maxi", "12345", new ArrayList<>()));
            userService.saveUser(new User(null, "Lams Cock", "lami", "12345", new ArrayList<>()));
            userService.saveUser(new User(null, "Tysriesen Cock", "ty", "12345", new ArrayList<>()));
            userService.saveUser(new User(null, "Trinityforce Cock", "arnold", "12345", new ArrayList<>()));

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
            /*winService.savePlayerWin(maxisTip, rivet1);
            winService.savePlayerWin(tysTip, winClass9);
            LottoNumbers lottoNumbers = Optional.ofNullable(lottoNumberRepo.findFirstByOrderByDrawingTimeDesc(LocalDateTime.now()))
                    .map(list -> list.get(0))
                    .orElse(new LottoNumbers());*/
            LottoNumbers lottoNumber1 = new LottoNumbers(null, "36;34;33;32;31;30", 6, LocalDateTime.now().minusDays(Long.valueOf(1)), new ArrayList<>());
            lottoNumberRepo.save(lottoNumber1);
        };
    }
}
