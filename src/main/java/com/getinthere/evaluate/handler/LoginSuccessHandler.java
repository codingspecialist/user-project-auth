package com.getinthere.evaluate.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.getinthere.evaluate.config.auth.LoginUserDetails;
import com.getinthere.evaluate.config.auth.SessionUser;
import com.getinthere.evaluate.domain.user.emp.Emp;
import com.getinthere.evaluate.domain.user.student.Student;
import com.getinthere.evaluate.domain.user.teacher.Teacher;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();
        LoginUserDetails loginUser = (LoginUserDetails) authentication.getPrincipal();
        SessionUser sessionUser = loginUser.getSessionUser();

        injectSession(session, sessionUser);

        String redirectUrl = requestDispatch(sessionUser);

        response.sendRedirect(redirectUrl);

    }

    private void injectSession(HttpSession session, SessionUser sessionUser) {
        if (sessionUser instanceof Student) {
            session.setAttribute("principal", (Student) sessionUser);
        } else if (sessionUser instanceof Teacher) {
            session.setAttribute("principal", (Teacher) sessionUser);
        } else if (sessionUser instanceof Emp) {
            session.setAttribute("principal", (Emp) sessionUser);
        } else {
            session.setAttribute("principal", sessionUser);
        }
    }

    private String requestDispatch(SessionUser sessionUser) {
        String redirectUrl = "/";
        if (sessionUser.getUser().getRole().equals("ROLE_GUEST")) {
            switch (sessionUser.getUser().getRequestRole()) {
                case "ROLE_STUDENT":
                    redirectUrl = "/auth/studentForm";
                    break;
                case "ROLE_TEACHER":
                    redirectUrl = "/auth/teacherForm";
                    break;
                case "ROLE_EMP":
                    redirectUrl = "/auth/empForm";
                    break;
            }
        }
        return redirectUrl;
    }
}
