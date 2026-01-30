package org.example.authdemo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class UserConfig {
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public CommandLineRunner addUser(
        JdbcUserDetailsManager users,
        PasswordEncoder encoder
    ) {
        return args -> {
            if (!users.userExists("demouser")) {
                users.createUser(
                    User.withUsername("demouser")
                        .password(encoder.encode("demopasswd"))
                        .roles("USER")
                        .build()
                );
            }
        };
    }
}
