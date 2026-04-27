package dev.shermende.authorization.configuration.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles({"test", "jwt"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JwtAuthorizationServerConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void jwtPublicKeyEndpointTest() throws Exception {
        this.mockMvc.perform(get("/.well-known/jwks.json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Sql(scripts = "/scripts/JwtAuthorizationServerConfigurationTest/getTokenEndpointTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void getTokenEndpointIsUnauthorizedTest() throws Exception {
        this.mockMvc.perform(post("/oauth/token"))
                .andDo(print())
                .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    @Transactional
    @Sql(scripts = "/scripts/JwtAuthorizationServerConfigurationTest/getTokenEndpointTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void getTokenEndpointIsMissingGrantTypeTest() throws Exception {
        this.mockMvc.perform(post("/oauth/token")
                        .header("Authorization", "Basic Y2xpZW50OnNlY3JldA=="))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("invalid_request"))
                .andExpect(jsonPath("$.error_description").value("Missing grant type"))
        ;
    }

    @Test
    @Transactional
    @Sql(scripts = "/scripts/JwtAuthorizationServerConfigurationTest/getTokenEndpointTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void getTokenEndpointIsBadCredentialsTest() throws Exception {
        this.mockMvc.perform(post("/oauth/token")
                        .header("Authorization", "Basic Y2xpZW50OnNlY3JldA==")
                        .param("grant_type", "password"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("invalid_grant"))
                .andExpect(jsonPath("$.error_description").value("Bad credentials"))
        ;
    }

    @Test
    @Transactional
    @Sql(scripts = "/scripts/JwtAuthorizationServerConfigurationTest/getTokenEndpointTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void getTokenEndpointTest() throws Exception {
        this.mockMvc.perform(post("/oauth/token")
                        .header("Authorization", "Basic Y2xpZW50OnNlY3JldA==")
                        .param("grant_type", "password")
                        .param("username", "username")
                        .param("password", "secret"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").exists())
                .andExpect(jsonPath("$.expires_in").exists())
                .andExpect(jsonPath("$.scope").exists())
                .andExpect(jsonPath("$.jti").exists())
        ;
    }

}
