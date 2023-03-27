package com.geeksforless.tuleninov.assistantweb.config.security;

import com.geeksforless.tuleninov.assistantweb.service.crud.user.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.geeksforless.tuleninov.assistantlib.Routes.*;
import static com.geeksforless.tuleninov.assistantweb.Constants.SCOPE_EMAIL;
import static com.geeksforless.tuleninov.assistantweb.Constants.SCOPE_PASSWORD;

/**
 * Override some of Spring's built-in security protocols to use our database and hashing algorithm.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailService customUserDetailService;
    private final AuthenticationSuccessHandler successHandler;

    public SecurityConfig(CustomUserDetailService customUserDetailService, AuthenticationSuccessHandler successHandler) {
        this.customUserDetailService = customUserDetailService;
        this.successHandler = successHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(URL_INDEX, URL_REGISTER, URL_MESSAGE).permitAll()
                .antMatchers("/static/**", "/css/**", "/images/**").permitAll()
                .antMatchers(URL_ADMIN + "/**").hasRole("ADMIN")
                .antMatchers(URL_MENU + "/**").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage(URL_LOGIN)
                .permitAll()
                .failureUrl(URL_LOGIN + "?error=true")
                .successHandler(successHandler)
                .usernameParameter(SCOPE_EMAIL)
                .passwordParameter(SCOPE_PASSWORD)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(URL_LOGOUT))
                .logoutSuccessUrl(URL_LOGIN)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling();
        http.headers().frameOptions().disable();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Add authentication based upon the custom UserDetailsService that is passed in.
     * It then returns a DaoAuthenticationConfigurer to allow customization of the authentication.
     *
     * @param auth SecurityBuilder - used to create an AuthenticationManager
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService);
    }
}
