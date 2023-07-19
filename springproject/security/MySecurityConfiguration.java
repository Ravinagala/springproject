package img3.project.springproject.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class MySecurityConfiguration {
 		@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			
 			
 		
			http.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
 			
			
			
 			// Needs authentication
 			http.authorizeHttpRequests().anyRequest().authenticated();
 			
     	
 			http.httpBasic();
 			//http.formLogin();
  			http.csrf().disable();
		
	        return http.build();
	   }
}