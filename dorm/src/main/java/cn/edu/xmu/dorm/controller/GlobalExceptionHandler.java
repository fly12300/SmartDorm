package cn.edu.xmu.dorm.controller;


import cn.edu.xmu.dorm.exception.DormException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import cn.edu.xmu.dorm.utils.ReturnNo;
import cn.edu.xmu.dorm.utils.ReturnObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DormException.class)
    public ResponseEntity<Object> handleSeckillExceptions(
            DormException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getReturnNo().getMessage());
        return ResponseEntity.badRequest().header("Content-Type", "application/json; charset=utf-8").body(new ReturnObject(ex.getReturnNo(), "", errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(
            Exception ex) {
        return ResponseEntity.badRequest().header("Content-Type", "application/json; charset=utf-8").body(new ReturnObject(ReturnNo.ERROR, "",ex.getMessage()));
    }

}