package com.projects.banking.advices;

import java.time.LocalTime;
import java.util.List;

public record ErrorDetails(LocalTime time,
                           List<String> message,
                           String details,
                           String errorcode) {

}
