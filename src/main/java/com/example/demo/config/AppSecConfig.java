package com.example.demo.config;

import com.example.demo.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class AppSecConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAppUserService iAppUserService;

    @Autowired
    private CustomSuccessHander customSuccessHander;

    /*@Bean
    public UserDetailsService userDetailsService(){
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(userBuilder.username("admin").password("123456").roles("ADMIN").build());
        manager.createUser(userBuilder.username("student").password("123456").roles("STUDENT").build());
        manager.createUser(userBuilder.username("teacher").password("123456").roles("TEACHER").build());
        return manager;
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) iAppUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/student/**").hasRole("STUDENT")
                .and()
                .authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/teacher/**").hasRole("TEACHER")
                .and()
                .formLogin().loginPage("/login").successHandler(customSuccessHander)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and().exceptionHandling().accessDeniedPage("/page403");
        http.csrf().disable();
    }


}
