package com.projects.banking.exception;

import java.time.LocalTime;

public record ErrorDetails(LocalTime time,
                           String message,
                           String details,
                           String errorcode) {

}
