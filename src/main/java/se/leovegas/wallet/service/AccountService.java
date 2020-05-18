package se.leovegas.wallet.service;

import se.leovegas.wallet.repositories.AccountRepository;
import se.leovegas.wallet.model.Account;
import se.leovegas.wallet.exceptionHandling.ErrorMessage;
import se.leovegas.wallet.exceptionHandling.WalletException;
import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account findAccountByPrimaryKey(Long accountId) throws WalletException {
        return accountRepository.findById(accountId).
                orElseThrow(() -> new WalletException(String.format(ErrorMessage.NO_ACCOUNT_FOUND, accountId)));
    }

    @Transactional
    public Account save(Account account) throws WalletException {
        if (account.getPlayerName() != null) {
            if (account.getPlayerName().length() < 5) {
                throw new WalletException(ErrorMessage.PLAYER_NAME_RESTRICTION);
            }
            return accountRepository.save(account);
        }
        throw new WalletException(ErrorMessage.IS_MANDATORY_FIELD);
    }

    @Transactional
    public Account update(Account account, Long accountId) throws WalletException {
        if (account.getPlayerName() != null) {
            account.setId(accountId);
            try {
                return accountRepository.save(account);
            } catch (ObjectOptimisticLockingFailureException e) {
                throw new WalletException(ErrorMessage.REFRESH_TO_GET_UPDATED_PLAYERS);
            }
        }
        throw new WalletException(ErrorMessage.IS_MANDATORY_FIELD);

    }

    public List<Account> getList() {
        return Lists.newArrayList(accountRepository.findAll());
    }

}
