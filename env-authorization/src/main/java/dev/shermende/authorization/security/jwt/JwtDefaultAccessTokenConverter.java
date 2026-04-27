package dev.shermende.authorization.security.jwt;

import dev.shermende.authorization.security.ExtendedUser;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

import java.util.HashMap;
import java.util.Map;

public class JwtDefaultAccessTokenConverter extends DefaultAccessTokenConverter {

    private final String subject;

    public JwtDefaultAccessTokenConverter(
            String subject,
            UserAuthenticationConverter userAuthenticationConverter
    ) {
        this.subject = subject;
        setUserTokenConverter(userAuthenticationConverter);
    }

    /**
     * Force add id field
     */
    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        // здесь можно добавить данные в jwt::payload
        final ExtendedUser extendedUser = (ExtendedUser) authentication.getPrincipal();
        final Map<String, Object> payload = new HashMap<>(super.convertAccessToken(token, authentication));
        payload.put("id", extendedUser.getId());
        payload.put("sub", subject);
        return payload;
    }

}
