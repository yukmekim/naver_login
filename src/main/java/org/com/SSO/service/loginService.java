package org.com.SSO.service;

import com.github.scribejava.core.model.OAuth2AccessToken;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface loginService {

    String getAuthorizationUrl(HttpSession session);

    OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException;

    String getUserProfile(OAuth2AccessToken oauthToken) throws IOException;
}
