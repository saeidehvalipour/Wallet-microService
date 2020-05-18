package se.leovegas.wallet.service;

import se.leovegas.wallet.repositories.WalletRepository;
import se.leovegas.wallet.model.Wallet;
import se.leovegas.wallet.exceptionHandling.DeficientFundsException;
import se.leovegas.wallet.exceptionHandling.ErrorMessage;
import se.leovegas.wallet.exceptionHandling.WalletException;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Transactional
    public Wallet createTransaction(Wallet wallet) throws WalletException, DeficientFundsException {

        if (walletRepository.findByTransactionReference(wallet.getTransactionReference()).isPresent()) {
            throw new WalletException(ErrorMessage.NOT_UNIQUE_TRANSACTION_REF_FOUND);
        }
        BigDecimal balance = walletRepository.getBalance(wallet.getAccount().getId());

        if (balance.add(wallet.getAmount()).compareTo(BigDecimal.ZERO) == 1
                | balance.add(wallet.getAmount()).compareTo(BigDecimal.ZERO) == 0) {
            return walletRepository.save(wallet);
        }

        throw new DeficientFundsException(String.format(ErrorMessage.NOT_ENOUGH_FUNDS,
                balance.doubleValue(), wallet.getAmount().doubleValue()));

    }

    public List<Wallet> getList() {
        return Lists.newArrayList(walletRepository.findAll());
    }

    public BigDecimal balanceByAccountID(Long accountId) {
        return walletRepository.getBalance(accountId);
    }

    public List<Wallet> transactions() {
        return Lists.newArrayList(walletRepository.findAll());
    }

}
