package com.getinthere.evaluate.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.getinthere.evaluate.domain.user.Users;
import com.getinthere.evaluate.domain.user.UsersRepository;
import com.getinthere.evaluate.domain.user.emp.Emp;
import com.getinthere.evaluate.domain.user.emp.EmpRepository;
import com.getinthere.evaluate.domain.user.student.Student;
import com.getinthere.evaluate.domain.user.student.StudentRepository;
import com.getinthere.evaluate.domain.user.teacher.Teacher;
import com.getinthere.evaluate.domain.user.teacher.TeacherRepository;
import com.getinthere.evaluate.handler.ex.CustomException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final EmpRepository empRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users userPS = usersRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("사용자를 찾을 수 없습니다"));

        switch (userPS.getRole()) {
            case "ROLE_STUDENT":
                Student studentPS = studentRepository.findByUserId(userPS.getId());
                return new LoginUserDetails(studentPS);
            case "ROLE_TEACHER":
                Teacher teacherPS = teacherRepository.findByUserId(userPS.getId());
                return new LoginUserDetails(teacherPS);
            case "ROLE_EMP":
                Emp empPS = empRepository.findByUserId(userPS.getId());
                return new LoginUserDetails(empPS);
            default:
                return new LoginUserDetails(null);
        }

    }

}
