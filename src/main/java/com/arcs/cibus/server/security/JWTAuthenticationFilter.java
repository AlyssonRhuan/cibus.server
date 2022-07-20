package com.arcs.cibus.server.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.arcs.cibus.server.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{

    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil)
    {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // TENTA AUTENTICAR
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException
    {
        try
        {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user.getLogin(), user.getPass(), new ArrayList<>());

            Authentication auth = authenticationManager.authenticate(authToken);

            return auth;
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    // CASO CONSIGA
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException
    {
        UserSS userSS = (UserSS) authResult.getPrincipal();
        String username = userSS.getUsername();
        String token = jwtUtil.generateToken(username);
        Long userId = userSS.getId();
        String userRole = userSS.getProfile().name();

        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("AuthorizationId", userId.toString());
        response.addHeader("AuthorizationRole", userRole);
    }

    private String jsonIsEmailNotConfirmed()
    {
        long date = new Date().getTime();
        return "{\"timestamp\": " + date + ", "
                + "\"status\": 403, "
                + "\"error\": \"Forbbiden\", "
                + "\"message\": \"Email was not confirmed!\", "
                + "\"path\": \"/login\"}";
    }

    // CASO NÃO CONSIGA
    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler
    {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException
        {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().append(json());
        }

        private String json()
        {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                    + "\"status\": 401, "
                    + "\"error\": \"Not allowed\", "
                    + "\"message\": \"Email or password wrong!\", "
                    + "\"path\": \"/login\"}";
        }
    }
}
