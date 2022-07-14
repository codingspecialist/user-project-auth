package com.getinthere.evaluate.web;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.getinthere.evaluate.config.auth.LoginUserDetails;
import com.getinthere.evaluate.service.AuthService;
import com.getinthere.evaluate.util.Script;
import com.getinthere.evaluate.web.dto.auth.JoinReqDto;
import com.getinthere.evaluate.web.dto.auth.UsersRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "auth/loginForm";
    }

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "auth/joinForm";
    }

    @PostMapping("/auth/join")
    public String join(@Valid JoinReqDto joinReqDto, BindingResult bindingResult) {
        UsersRespDto usersRespDto = authService.회원가입(joinReqDto);

        String redirectUrl = "";
        System.out.println("log : " + usersRespDto.getRequestRole() + "======");
        switch (usersRespDto.getRequestRole()) {
            case "REQUEST_STUDENT":
                redirectUrl = "/auth/studentForm";
                break;
            case "REQUEST_TEACHER":
                redirectUrl = "/auth/teacherForm";
                break;
            case "REQUEST_EMP":
                redirectUrl = "/auth/empForm";
                break;
        }
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/auth/teacherForm")
    public String teacherForm() {
        return "auth/teacherForm";
    }

    @PostMapping("/auth/teacher")
    public @ResponseBody String teacher() {
        return Script.href("/", "관리자에게 승인을 요청하세요.");
    }
}
