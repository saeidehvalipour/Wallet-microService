package se.leovegas.wallet.model.DTO;

import se.leovegas.wallet.model.Wallet;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class WalletDTO {

    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private String description;
    private Date transactionDate;
    private Long transactionReference;

    public WalletDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(Long transactionReference) {
        this.transactionReference = transactionReference;
    }

    public static Wallet buildFromWalletToDO(WalletDTO w) {
        Wallet wallet = new Wallet();
        wallet.setAccount(w.getAccountId());
        wallet.setAmount(w.getAmount());
        wallet.setId(w.getId());
        wallet.setDescription(w.getDescription());
        wallet.setTransactionDate(w.getTransactionDate());
        wallet.setTransactionReference(w.getTransactionReference());
        return wallet;
    }

    public static WalletDTO toWalletDTO(Wallet w) {
        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setAccountId(w.getAccount().getId());
        walletDTO.setAmount(w.getAmount());
        walletDTO.setId(w.getId());
        walletDTO.setDescription(w.getDescription());
        walletDTO.setTransactionDate(w.getTransactionDate());
        walletDTO .setTransactionReference(w.getTransactionReference());
        return walletDTO;
    }

}
