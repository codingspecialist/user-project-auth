package com.getinthere.evaluate.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.getinthere.evaluate.handler.ex.CustomApiException;
import com.getinthere.evaluate.handler.ex.CustomException;
import com.getinthere.evaluate.util.Script;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHanlder {
    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(Exception e) { // fetch 요청시 발동
        log.error("에러 발생 : " + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> htmlException(Exception e) { // 일반적인 요청 Get(a태그), Post(form태그) 요청
        log.error("에러 발생 : " + e.getMessage());

        return new ResponseEntity<>(Script.back(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
