package org.com.SSO.controller;

import com.github.scribejava.core.model.OAuth2AccessToken;

import org.com.SSO.service.loginService;
import org.com.uss.LoginVO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class SSOController {

    @Autowired
    private loginService loginService;

    @RequestMapping(value = "/")
    public String index(LoginVO vo, HttpServletRequest request, HttpSession session, ModelMap model) {
        vo = (LoginVO) session.getAttribute("USER_INFO");

        if (vo != null) {
            model.addAttribute("USER_INFO", vo);
        }

        return "/index";
    }

    @RequestMapping(value = "/login")
    public String Login(ModelMap model, HttpSession session) {
        /*네이버 로그인 url 생성*/
        String url = loginService.getAuthorizationUrl(session);

        model.addAttribute("naverAuthUrl", url);

        return "/sso/login";
    }

    @RequestMapping(value = "/logout")
    public String Logout(HttpSession session) {
        if(session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/oauth2/login")
    public String Oauth2Login(@RequestParam String code, @RequestParam String state, HttpSession session, ModelMap model) throws IOException, ParseException {
        OAuth2AccessToken oauthToken;
        oauthToken = loginService.getAccessToken(session, code, state);

        /*네이버 프로필 정보 가져오기*/
        String result = loginService.getUserProfile(oauthToken);

        Object obj = null;

        JSONParser parser = new JSONParser();

        try {
            obj = parser.parse(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject jobj = (JSONObject) obj;
        JSONObject res_obj = (JSONObject) jobj.get("response");

        LoginVO user = new LoginVO();

        user.setId((String) res_obj.get("email"));
        user.setName((String) res_obj.get("nickname"));
        user.setProfileImage((String) res_obj.get("profile_image"));
        user.setAge((String) res_obj.get("age"));
        user.setGender((String) res_obj.get("gender"));
        user.setLoginType("naver");

        session.setAttribute("USER_INFO", user);

        return "redirect:/";
    }
}
