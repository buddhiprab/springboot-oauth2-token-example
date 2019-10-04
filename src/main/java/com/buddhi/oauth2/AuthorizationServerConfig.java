package com.buddhi.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
				.withClient("a")
				.secret(passwordEncoder().encode("a"))
				.authorities("ROLE_A")
				.scopes("all")
				.authorizedGrantTypes("client_credentials")
				.and()
				.withClient("b")
				.secret(passwordEncoder().encode("b"))
				.authorities("ROLE_B")
				.scopes("all")
				.authorizedGrantTypes("client_credentials");
	}

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
    }

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(4);
    }
}
