package com.projects.banking.dto;

import com.projects.banking.annotations.TransactionTypeValidation;

import java.time.LocalDateTime;

public record TransactionDto(Long id,
                             Long accountid,
                             double amount,
                             @TransactionTypeValidation
                             String transctiontype,
                             LocalDateTime timedate
                             ) {
}
