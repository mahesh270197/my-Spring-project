package com.projects.banking.dto;

import java.time.LocalDateTime;

public record TransactionDto(Long id,
                             Long accountid,
                             double amount,
                             String transctiontype,
                             LocalDateTime timedate
                             ) {
}
