package com.board.controller.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Log4j2
public class CustomRestAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleBindException(BindException e){
        log.error(e);

        Map<String, String> errorMap = new HashMap<>();

        if(e.hasErrors()){
            BindingResult bindingResult = e.getBindingResult();

            bindingResult.getFieldErrors().forEach(fieldError -> {
               errorMap.put(fieldError.getField(), fieldError.getCode());
            });
        }
        return ResponseEntity.badRequest().body(errorMap);
    }

    // 서버가 아니라 데이터가 문제라 클라이언트에게 알리기 위한 예외 메시지
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleFKException(Exception e ){
        // 에러로그
        log.error(e);

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("time", " " + System.currentTimeMillis());
        errorMap.put("msg", "constraint fails");
        return ResponseEntity.badRequest().body(errorMap);
    }

    // 특정  댓글 조회 시 예외 처리  , 없는 번호 삭제시 처리도 추가
    @ExceptionHandler({NoSuchElementException.class,
                        EmptyResultDataAccessException.class}) // 추가
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleNoSuchElement (Exception e){
        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("time", " " + System.currentTimeMillis());
        errorMap.put("msg", "No Such Element Exception ");
        return ResponseEntity.badRequest().body(errorMap);
    }
}
