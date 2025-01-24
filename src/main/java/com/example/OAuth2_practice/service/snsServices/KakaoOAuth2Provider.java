package com.example.OAuth2_practice.service.snsServices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KakaoOAuth2Provider implements OAuth2Provider {
//    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
//    private String CLIENT_ID;
//
//    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
//    private String SCOPE;
//
//    @Value("${spring.security.oauth2.client.registration.kakao.scope}")
//    private String REDIRECT_URI;

    @Override
    public String getAuthorizationUrl(String state) {
        return "";
//        return String.format(
//                "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=%s&scope=%s&state=%s&redirect_uri=%s",
//                CLIENT_ID, SCOPE, state, REDIRECT_URI
//        );
    }
}