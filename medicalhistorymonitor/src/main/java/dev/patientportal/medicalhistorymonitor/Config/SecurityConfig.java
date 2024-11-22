// package dev.patientportal.medicalhistorymonitor.Config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.web.SecurityFilterChain;

// import dev.patientportal.medicalhistorymonitor.Service.UserService;
// import lombok.AllArgsConstructor;

// @Configuration
// @AllArgsConstructor
// @EnableWebSecurity
// public class SecurityConfig {
//     @Autowired
//     private final UserService appUserService;

//     @Bean
//     public UserDetailsService userDetailsService() {
//         return appUserService;
//     }

//     @Bean
//     public AuthenticationProvider authenticationProvider() {
//         DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//         provider.setUserDetailsService(appUserService);
//         return provider;
//     }

//     @Bean
//     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         return http
//                 .csrf(AbstractHttpConfigurer::disable)
//                 // .authorizeHttpRequests(auth ->
//                 // auth.requestMatchers("css/login.css","js/login.js","js/jquery-3.7.1.min.js","img/stethoscope-blue-background-cardiology-concept_711966-751.avif").permitAll().anyRequest().authenticated())
//                 .formLogin(form -> form.loginPage("/login").permitAll())
//                 .authorizeHttpRequests(auth -> auth.requestMatchers("/img/**", "/js/**", "/css/**").permitAll()
//                         .anyRequest().authenticated())
//                 .build();
//     }
// }
