package com.noveria.integration.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

@org.springframework.boot.test.context.TestConfiguration
@ComponentScan(value = {"com.noveria.integration"})
public class TestConfiguration {

    @Value("${security.auth.id}")
    private String authId;

    @Value("${security.auth.client.id}")
    private String authClientId;

    @Value("${security.auth.client.secret}")
    private String authClientSecret;

    @Value("${security.auth.grant.type}")
    private String grantType;

    @Value("${security.auth.token.url}")
    private String tokenUrl;

    @Bean
    public RestTemplate restTemplate() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();

        resourceDetails.setId(authId);
        resourceDetails.setClientId(authClientId);
        resourceDetails.setClientSecret(authClientSecret);
        resourceDetails.setAccessTokenUri(tokenUrl);
        resourceDetails.setGrantType(grantType);

        DefaultOAuth2ClientContext context = new DefaultOAuth2ClientContext();
        return new OAuth2RestTemplate(resourceDetails,context);
    }

}