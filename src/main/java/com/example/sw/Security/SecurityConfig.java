package com.example.sw.Security;

import com.example.sw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Value("${jwt.secret}")
    private String secretKey;

    //https://www.youtube.com/watch?v=YEB0Ln6Lcyk&ab_channel=KyeongrokKim 8:51
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                // 특정 API에 대해 모든 사용자에게 접근 허용
                .antMatchers("/user/register").permitAll()
                .antMatchers("/board/return").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/search/recommand").permitAll()
                .antMatchers("/search/updateData").permitAll()
                // --------------------------------------------
                .anyRequest().authenticated() // 나머지 API에 대해서는 인증을 요구
                .and()
                .addFilterBefore(new JwtFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class);
    }
}

