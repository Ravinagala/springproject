package img3.project.springproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class InMemory {
    @Bean
    public UserDetailsService userDetailsService
            (PasswordEncoder passwordEncoder) {
        UserDetails user1 = User.withUsername("Ravi")
            .password(passwordEncoder.encode("ravi@"))
            .roles("USER")
            .build();
        UserDetails user2 = User.withUsername("Vardhan")
            .password(passwordEncoder.encode("vardhan@"))
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }
}