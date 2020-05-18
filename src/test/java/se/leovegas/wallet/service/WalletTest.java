package se.leovegas.wallet.service;

import se.leovegas.wallet.model.Account;
import se.leovegas.wallet.model.Wallet;
import se.leovegas.wallet.exceptionHandling.DeficientFundsException;
import se.leovegas.wallet.exceptionHandling.WalletException;
import java.math.BigDecimal;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private WalletService walletService;

    private Account account;

    @Before
    public void setupPlayerAccount() throws WalletException {
        account = accountService.save(new Account("Saeideh", new Date()));
    }

    @Test(expected = DeficientFundsException.class)
    public void withdrawTransaction400() throws WalletException, DeficientFundsException {
        Wallet wallet = new Wallet(account, new BigDecimal(-4000), "debit", new Date(), 150L);
        walletService.createTransaction(wallet);
    }

    @Test
    public void addTransaction8000() throws WalletException, DeficientFundsException {
        Wallet saved1 = new Wallet(account, new BigDecimal(8000), "credit", new Date(), 150L);
        Wallet savedTransaction1 = walletService.createTransaction(saved1);
        assertNotNull(savedTransaction1);
        BigDecimal balance = walletService.balanceByAccountID(account.getId());
        assertTrue(balance.doubleValue() == 8000);
    }
}
