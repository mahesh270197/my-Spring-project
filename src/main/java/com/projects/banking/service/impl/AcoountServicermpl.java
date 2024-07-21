package com.projects.banking.service.impl;

import com.projects.banking.annotations.TransactionTypeValidation;
import com.projects.banking.dto.AccountDto;
import com.projects.banking.dto.TransactionDto;
import com.projects.banking.entity.Account;
import com.projects.banking.entity.Transaction;
import com.projects.banking.exception.AccountException;
import com.projects.banking.mapper.AccountMapper;
import com.projects.banking.repository.AccountRepository;
import com.projects.banking.repository.TransactionRepository;
import com.projects.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

@Service
public class AcoountServicermpl implements AccountService {

@TransactionTypeValidation
    private static final String TRANSACTION_TYPE_DEPOSIT = "DEPOSI";
    private static final String TRANSACTION_TYPE_WITHDRAW = "WITHDRAW";
    private AccountRepository accountrepository;
    private TransactionRepository transactionRepository;

    public AcoountServicermpl(AccountRepository accountrepository,TransactionRepository transactionRepository) {
        this.accountrepository = accountrepository;
        this.transactionRepository=transactionRepository;
    }
    @Override
    public AccountDto createAccount(AccountDto accountdto) {

        Account account = AccountMapper.mapToAccount(accountdto);
        Account saveaccount = accountrepository.save(account);
        return AccountMapper.mapToAccountDto(saveaccount);
    }

    @Override
    public AccountDto getAccountById(long id) {
             Account account =accountrepository.findById(id).orElseThrow(()-> new AccountException("Account does not exists"));
             return  AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double depositamt) {
        Account account =accountrepository.findById(id).orElseThrow(()-> new AccountException("Account does not exists"));
        double v = account.getBalance() + depositamt;
        account.setBalance(v);
        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmmount(depositamt);
        transaction.setTransactiontype(TRANSACTION_TYPE_DEPOSIT);
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);
        Account saveaccount = accountrepository.save(account);
        return AccountMapper.mapToAccountDto(saveaccount);
    }

    @Override
    public AccountDto withdraw(Long id, double withdrawamt) {
        Account account =accountrepository.findById(id).orElseThrow(()-> new AccountException("Account does not exists"));
        if(account.getBalance() < withdrawamt) {
            throw new RuntimeException("insufficient balance");
        }
        double v = account.getBalance() - withdrawamt;
        account.setBalance(v);
        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmmount(withdrawamt);
        transaction.setTransactiontype(TRANSACTION_TYPE_WITHDRAW);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
        Account saveaccount = accountrepository.save(account);
        return AccountMapper.mapToAccountDto(saveaccount);
    }

    @Override
    public List<AccountDto> getAllAcounts() {
               List<Account>  accounts= accountrepository.findAll();
        List<AccountDto> accountsdto = accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
        return accountsdto ;
    }

    @Override
    public void deleteAccount(long id) {
        Account account =accountrepository.findById(id).orElseThrow(()-> new AccountException("Account does not exists"));
             accountrepository.deleteById(id);
    }

    @Override
    public void fundTransfer(long fromaccountId, long toaccountId, double fund) {

        Account fromAccount = accountrepository.findById(fromaccountId)
                .orElseThrow(()->new AccountException("Account does not exits"));

        Account toaccount = accountrepository.findById(toaccountId)
                .orElseThrow(()->new AccountException("Account does not exits"));

        if (fromAccount.getBalance()< fund){
            throw new RuntimeException("insufficient balance ");

        }
         double fromaccoutbalance = fromAccount.getBalance() - fund;

        double toaccountbalance = toaccount.getBalance() + fund;
        fromAccount.setBalance(fromaccoutbalance);
        toaccount.setBalance(toaccountbalance);

        Transaction transaction = new Transaction();

        accountrepository.save(fromAccount);
        accountrepository.save(toaccount);






    }
    @Override
    public List<TransactionDto> getAccountTransaction(Long accountid) {
       List<Transaction>  list  = transactionRepository.findByAccountIdOrderByTimestampDesc(accountid);

        return    list.stream().
                map(transaction -> convertjpaentitytodto(transaction)).collect(Collectors.toList());
    }

    private  TransactionDto convertjpaentitytodto(Transaction transaction){
        return new TransactionDto(
                transaction.getTransactionId(),
                transaction.getAccountId(),
                transaction.getAmmount(),
                transaction.getTransactiontype(),

                transaction.getTimestamp()
        );
    }

}
