package com.projects.banking.mapper;

import com.projects.banking.dto.AccountDto;
import com.projects.banking.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountdto){
        Account account = new Account(
                accountdto.getId(),
                accountdto.getAccountholdername(),
                accountdto.getBalance());

        return account;
    }

    public static  AccountDto mapToAccountDto(Account account){
        AccountDto accountdto = new AccountDto(account.getId(),
                account.getAccountHolderName(),
                account.getBalance());
        return  accountdto;
    }
}
