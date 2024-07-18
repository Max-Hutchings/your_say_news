package com.YourSayNews.APIGateway.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {



    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity){

        serverHttpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(authorise ->
                        authorise.anyExchange().permitAll()
                );

        return serverHttpSecurity.build();
    }

}
