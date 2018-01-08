package com.noveria.artistservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonConfiguration {

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
    @LoadBalanced
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
