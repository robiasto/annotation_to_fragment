package de.robiasto.app.infrastructure.configuration;

import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.user.infrastructure.UserId;
import de.robiasto.app.user.login.SecurityUserService;
import de.robiasto.app.user.login.infrastructure.SecurityRoute;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, SecurityUserService securityUserService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(securityUserService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(httpSecurityCsrfConfigurer ->
                          httpSecurityCsrfConfigurer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        );

        http.authorizeHttpRequests(
                    requests ->
                            requests
                                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                    .requestMatchers("/svg/*").permitAll()
                                    .requestMatchers("/webjars/**").permitAll()
                                    .anyRequest().authenticated()
            )
            .formLogin(
                    securityFormLoginConfigurer ->
                            securityFormLoginConfigurer
                                    .loginPage(SecurityRoute.LOGIN)
                                    .defaultSuccessUrl(RouteConfiguration.getList(new UserId()), true)
                                    .permitAll()
            )
            .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}
