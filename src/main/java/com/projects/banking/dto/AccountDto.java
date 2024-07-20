package com.projects.banking.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {

    private  long Id;
    private  String accountholdername;
    private Double balance;

}
