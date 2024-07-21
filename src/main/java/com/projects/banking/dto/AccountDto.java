package com.projects.banking.dto;


import com.projects.banking.annotations.AccountValidation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {

    private  long Id;
    @AccountValidation
    private  String accountholdername;
    private Double balance;

}
