package se.leovegas.wallet.repositories;

import se.leovegas.wallet.model.Wallet;
import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query(value="SELECT wa FROM Wallet wa WHERE wa.transactionReference = :transactionReferenceNo")
    Optional<Wallet> findByTransactionReference(@Param("transactionReferenceNo") Long refNo);

    @Query(value = "SELECT COALESCE(SUM(wa.amount),0.00) FROM Wallet wa JOIN wa.account acc WHERE acc.id = :accountId")
    BigDecimal getBalance(@Param("accountId")Long accountId);
    
}
