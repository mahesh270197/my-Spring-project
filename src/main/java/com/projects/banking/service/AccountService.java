package com.projects.banking.service;

import com.projects.banking.dto.AccountDto;
import com.projects.banking.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {
    AccountDto createAccount(AccountDto accountdto);
    Optional<AccountDto> getAccountById(long id);
    AccountDto deposit(Long id, double depositamt);
    AccountDto withdraw(Long id, double depositamt);
    List<AccountDto> getAllAcounts();
    void deleteAccount(long id);
    void fundTransfer(long fromaccountId, long toaccountId, double fund);
    List<TransactionDto> getAccountTransaction(Long accountid);
}
