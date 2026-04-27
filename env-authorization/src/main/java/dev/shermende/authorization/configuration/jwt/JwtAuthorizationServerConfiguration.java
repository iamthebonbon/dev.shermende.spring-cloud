package dev.shermende.authorization.configuration.jwt;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * Jwt authorization server configuration
 */
@Slf4j
@Configuration
@Profile({"jwt"})
@RequiredArgsConstructor
@EnableAuthorizationServer
public class JwtAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Qualifier("dataSource")
    private final DataSource dataSource;
    @Qualifier("jwtAuthorizationServerTokenStore")
    private final TokenStore tokenStore;
    @Qualifier("appUserDetailsService")
    private final UserDetailsService userDetailsService;
    @Qualifier("jwtAuthorizationServerTokenConverter")
    private final JwtAccessTokenConverter accessTokenConverter;
    @Qualifier("jwtAuthorizationServerAuthenticationManager")
    private final AuthenticationManager authenticationManager;

    /**
     * Oauth applications storage
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    /**
     * Authorization server endpoints access rules
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
            .tokenKeyAccess("isAuthenticated()")
            .checkTokenAccess("isAuthenticated()");
    }

    /**
     * JWT authorization server settings
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            .tokenStore(tokenStore)
            .accessTokenConverter(accessTokenConverter)
            .userDetailsService(userDetailsService)
            .authenticationManager(authenticationManager);
    }

    /**
     *
     */
    @RestController
    @Profile({"jwt"})
    @RequiredArgsConstructor
    public static class WellKnownConfiguration {
        private final KeyPair keyPair;

        @GetMapping("/.well-known/jwks.json")
        public Map<String, Object> getKey() {
            final RSAPublicKey publicKey = (RSAPublicKey) this.keyPair.getPublic();
            final RSAKey key = new RSAKey.Builder(publicKey).build();
            return new JWKSet(key).toJSONObject();
        }
    }

}
