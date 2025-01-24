package com.example.OAuth2_practice.service.snsServices;

public interface OAuth2Provider {
    String getAuthorizationUrl(String state);
}