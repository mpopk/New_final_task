package com.kodilla.Task_final.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Zero or negative Task identifier")
public class IllegalTaskIdException extends RuntimeException {
}