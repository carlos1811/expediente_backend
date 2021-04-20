package com.carlosrey.springboot.backend.apirest.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import static org.springframework.security.jwt.codec.Codecs.utf8Encode;

@Configuration

@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired

	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception { // TODO Auto-generated
																								// method stub
		endpoints.authenticationManager(authenticationManager)
				// .tokenStore(tokenStore()) //<----Es opcional esto ya que ya se realiza
				.accessTokenConverter(accessTokenConverter());
	}

	@Bean
	public JwtTokenStore tokenStore() { // TODO Auto-generated method stub
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		// TODO Auto-generated method stub
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//		logger.info(utf8Encode(JwtConfig.RSA_PRIVADA.trim()).toString());
		
//		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA.trim());
//		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);
		return jwtAccessTokenConverter;
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception { // TODO Auto-generated
																								// method stub
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	// registra los front de angular con las credenciales

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception { // TODO Auto-generated method stub

		clients.inMemory().withClient("angularapp").secret(passwordEncoder.encode("12345"))
				.scopes("read", "write", "webclient")
				.authorizedGrantTypes("password", "client_credentials", "refresh_token")
				.accessTokenValiditySeconds(3600).refreshTokenValiditySeconds(3600);

	}

}
 