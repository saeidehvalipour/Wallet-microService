package se.leovegas.wallet.controller;

import se.leovegas.wallet.model.Account;
import se.leovegas.wallet.model.Wallet;
import se.leovegas.wallet.model.DTO.AccountDTO;
import se.leovegas.wallet.model.DTO.WalletDTO;
import se.leovegas.wallet.exceptionHandling.DeficientFundsException;
import se.leovegas.wallet.exceptionHandling.WalletException;
import se.leovegas.wallet.service.AccountService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import se.leovegas.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/players")
public class WalletController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private WalletService walletService;

    @GetMapping
    public ResponseEntity getPlayers() {
        List<Account> accounts = accountService.getList();
        return new ResponseEntity<List<AccountDTO>>(AccountDTO.doToDTOList(accounts), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPlayer(@PathVariable("id") Long id) {
        Account account;
        try {
            account = accountService.findAccountByPrimaryKey(id);
        } catch (WalletException ex) {
            Logger.getLogger(WalletController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AccountDTO>(AccountDTO.toAccountDTO(account), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity createPlayer(@RequestBody AccountDTO accountDTO) {
        Account saved;
        try {
            saved = accountService.save(AccountDTO.buildFromAccountDTO(accountDTO));
        } catch (WalletException ex) {
            Logger.getLogger(WalletController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<AccountDTO>(AccountDTO.toAccountDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePlayer(@PathVariable("id") Long accountId, @RequestBody AccountDTO accountDTO) {
        Account saved;
        try {
            saved = accountService.update(AccountDTO.buildFromAccountDTO(accountDTO), accountId);
        } catch (WalletException ex) {
            Logger.getLogger(WalletController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<AccountDTO>(AccountDTO.toAccountDTO(saved), HttpStatus.OK);
    }

    @PostMapping("/{id}/transactions")
    public ResponseEntity createTransaction(@PathVariable("id")Long accountId,@RequestBody WalletDTO walletDTO) {
        Wallet saved;
        try {
            walletDTO.setAccountId(accountId);
            saved = walletService.createTransaction(WalletDTO.buildFromWalletToDO(walletDTO));
        } catch (WalletException | DeficientFundsException ex) {
            Logger.getLogger(WalletController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } 
        return new ResponseEntity<WalletDTO>(WalletDTO.toWalletDTO(saved), HttpStatus.CREATED);
    }
    
  
   

}
