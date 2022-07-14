package com.getinthere.evaluate.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getinthere.evaluate.domain.user.Users;
import com.getinthere.evaluate.domain.user.UsersRepository;
import com.getinthere.evaluate.web.dto.auth.JoinReqDto;
import com.getinthere.evaluate.web.dto.auth.UsersRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = RuntimeException.class)
    public UsersRespDto 회원가입(JoinReqDto joinReqDto) {
        Users userPS = usersRepository.save(joinReqDto.toEntity(passwordEncoder));
        return UsersRespDto.toDto(userPS);
    }

}
