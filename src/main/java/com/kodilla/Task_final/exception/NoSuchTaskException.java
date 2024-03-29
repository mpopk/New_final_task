package com.kodilla.Task_final.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Task")
public class NoSuchTaskException extends RuntimeException {
}