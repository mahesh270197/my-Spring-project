package com.projects.banking.contollers;

import com.projects.banking.dto.AccountDto;
import com.projects.banking.dto.FundTrasferDto;
import com.projects.banking.dto.TransactionDto;
import com.projects.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping
    public ResponseEntity<AccountDto> addAccount( @RequestBody AccountDto accountDto){
           return new ResponseEntity<>( accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<AccountDto> getaccountById( @PathVariable long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return  ResponseEntity.ok(accountDto);
    }
    @PutMapping("/{id}/deposit")
    public  ResponseEntity<AccountDto>depositamt( @PathVariable long id , @RequestBody Map<String, Double> request){
        AccountDto accountDto = accountService.deposit(id, request.get("amount"));
        return  ResponseEntity.ok(accountDto);
    }
    @PutMapping("/{id}/withdraw")
    public  ResponseEntity<AccountDto>withdrawamt( @PathVariable long id , @RequestBody Map<String, Double> request){
        AccountDto accountDto = accountService.withdraw(id, request.get("amount"));
        return  ResponseEntity.ok(accountDto);
    }
    @GetMapping("/allaccounts")
    public  ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accountsdto = accountService.getAllAcounts();
        return  ResponseEntity.ok(accountsdto);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteAccount( @PathVariable long id){
        accountService.deleteAccount(id);
        return  ResponseEntity.ok("Account Deleted successfully");
    }
    @PostMapping("/fundtransfer")
  public   ResponseEntity<String> fundTransfer( @RequestBody FundTrasferDto fundTrasferDto ){
        accountService.fundTransfer(
                fundTrasferDto.fromaccountId(),
                fundTrasferDto.toaccountId(),
                fundTrasferDto.fund());
        return  ResponseEntity.ok("Fund Transfer successfully");
  }
  @GetMapping("/transactions/{accountid}")
  public  ResponseEntity<List<TransactionDto>> getTransactionById(@PathVariable Long accountid){
            List<TransactionDto> response;
      response = accountService.getAccountTransaction(accountid );
      return ResponseEntity.ok(response);
  }
}
