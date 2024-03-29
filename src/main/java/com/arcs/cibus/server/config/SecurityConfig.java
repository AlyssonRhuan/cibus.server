package com.arcs.cibus.server.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.arcs.cibus.server.security.JWTAuthenticationFilter;
import com.arcs.cibus.server.security.JWTAuthorizationFilter;
import com.arcs.cibus.server.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity (prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private Environment env;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    public static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**"
    };

    public static final String[] PUBLIC_MATCHERS_GET = {
            "/category/**",
            "/product/**",
            "/login/**",
            "/dashboard/**",
            "/cash/**",
            "/me/**",
            "/notification/**",
            "/sale/**",
            "/user/**"
    };

    public static final String[] PUBLIC_MATCHERS_POST = {
            "/category/**",
            "/product/**",
            "/login/**",
            "/dashboard/**",
            "/cash/**",
            "/me/**",
            "/notification/**",
            "/sale/**",
            "/user/**"
    };

    public static final String[] PUBLIC_MATCHERS_PUT = {
            "/category/**",
            "/product/**",
            "/login/**",
            "/dashboard/**",
            "/cash/**",
            "/me/**",
            "/notification/**",
            "/sale/**",
            "/user/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        if (Arrays.asList(env.getActiveProfiles()).contains("test"))
        {
            http.headers().frameOptions().disable();
        }

        http.cors().and().csrf().disable();
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
            .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
            .antMatchers(HttpMethod.PUT, PUBLIC_MATCHERS_PUT).permitAll()
            .antMatchers(PUBLIC_MATCHERS).permitAll()
            .anyRequest().authenticated();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setExposedHeaders(Arrays.asList("Authorization", "AuthorizationId", "AuthorizationRole"));
        corsConfiguration.applyPermitDefaultValues();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
