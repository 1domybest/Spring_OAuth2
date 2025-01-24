package com.example.OAuth2_practice.controller;

import com.example.OAuth2_practice.service.snsServices.GoogleOAuth2Provider;
import com.example.OAuth2_practice.service.snsServices.KakaoOAuth2Provider;
import com.example.OAuth2_practice.service.snsServices.NaverOAuth2Provider;
import com.example.OAuth2_practice.service.snsServices.OAuth2Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final NaverOAuth2Provider naverOAuth2Provider;

    private final GoogleOAuth2Provider googleOAuth2Provider;

    private final KakaoOAuth2Provider kakaoOAuth2Provider;


    @GetMapping("/snsLogin/{provider}")
    public String snsLogin(@RequestParam String state, @PathVariable String provider) {
        OAuth2Provider oauth2Provider;

        switch (provider.toLowerCase()) {
            case "naver":
                oauth2Provider = naverOAuth2Provider;
                break;
            case "google":
                oauth2Provider = googleOAuth2Provider;
                break;
            case "kakao":
                oauth2Provider = kakaoOAuth2Provider;
                break;
            default:
                throw new IllegalArgumentException("Unsupported provider");
        }

        return oauth2Provider.getAuthorizationUrl(state);
    }
}
