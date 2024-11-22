package com.incetutku.ordermanagementsystem.exception;

import org.springframework.http.HttpStatus;

public record Response(String message, HttpStatus status) {
}
