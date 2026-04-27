package dev.shermende.game.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.MessageResolver;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.core.AnnotationLinkRelationProvider;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Order(1)
@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final SecurityProperties securityProperties;

    /**
     * swagger authentication manager
     */
    @Override
    protected void configure(
            AuthenticationManagerBuilder auth
    ) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(securityProperties.getUser().getName())
                .password(passwordEncoder.encode(securityProperties.getUser().getPassword()))
                .authorities(securityProperties.getUser().getRoles().toArray(new String[0]));
    }

    /**
     * swagger security settings
     */
    @Override
    protected void configure(
            HttpSecurity http
    ) throws Exception {
        http.requestMatcher(
                        new OrRequestMatcher(
                                new AntPathRequestMatcher("/webjars/**"),
                                new AntPathRequestMatcher("/v2/api-docs"),
                                new AntPathRequestMatcher("/swagger-resources/**"),
                                new AntPathRequestMatcher("/swagger-ui.html")
                        ))
                .authorizeRequests().anyRequest().authenticated()
                .and().httpBasic()
                .and().csrf().disable();
    }

    /**
     * swagger global settings
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(Links.class, AuthenticationPrincipal.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("dev.shermende"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * hateoas compatibility
     */
    @Bean
    public LinkDiscoverers discoverers() {
        return new LinkDiscoverers(SimplePluginRegistry
                .create(Collections.singletonList(new CollectionJsonLinkDiscoverer())));
    }

    /**
     * hateoas compatibility
     */
    @Bean
    public LinkRelationProvider linkRelationProvider() {
        return new AnnotationLinkRelationProvider();
    }

    /**
     * hateoas compatibility
     */
    @Bean
    public MessageResolver MessageResolver() {
        return MessageResolver.DEFAULTS_ONLY;
    }

}
