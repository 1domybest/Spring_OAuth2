package com.example.OAuth2_practice.service.snsServices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NaverOAuth2Provider implements OAuth2Provider {

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.naver.scope}")
    private String SCOPE;

    @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
    private String REDIRECT_URI;


    @Override
    public String getAuthorizationUrl(String state) {
        return String.format(
                "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=%s&scope=%s&state=%s&redirect_uri=%s",
                CLIENT_ID, SCOPE, state, REDIRECT_URI
        );
    }
}
