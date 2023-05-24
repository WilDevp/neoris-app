package com.neoris.app.domain.exception;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public class ArgumentsException {
    public static List<String> handleExceptions(
            BindingResult resultRequest) {

        return resultRequest.getAllErrors().stream().map(error ->
                error.getDefaultMessage()
        ).collect(Collectors.toList());
    }
}
