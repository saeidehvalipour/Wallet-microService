package se.leovegas.wallet.service;

import se.leovegas.wallet.model.Account;
import se.leovegas.wallet.exceptionHandling.WalletException;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTest {

    @Autowired
    private AccountService accountService;

    @Before
    public  void setupAccount() throws WalletException {
        accountService.save(new Account(10L, "Daniel", new Date()));
    }

    @Test(expected = WalletException.class)
    public void createPlayerWithEmptyPlayerName() throws WalletException {
        accountService.save(new Account(10L, "", new Date()));
    }

    @Test(expected = WalletException.class)
    public void createPlayerWithPlayerNameLessThan5Characters() throws WalletException {
        accountService.save(new Account(10L, "", new Date()));
    }

    @Test(expected = WalletException.class)
    public void createPlayerWithNullPlayerName() throws WalletException {
        accountService.save(new Account(10L, null, new Date()));
    }

    @Test
    public void createPlayerWithValidDetails() throws WalletException {
        accountService.save(new Account(10L, "gumball darwin", new Date()));
    }

}
