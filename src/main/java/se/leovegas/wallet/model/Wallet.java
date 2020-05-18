package se.leovegas.wallet.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class Wallet {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Account account;
    @NotNull
    private BigDecimal amount;
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date transactionDate;
    @NotNull
    @Column(unique = true)
    private Long transactionReference;
    //for concurrency
    @Version
    private int version;

    public Wallet() {
    }

    public Wallet(Account account, BigDecimal amount, String description, Date transactionDate, Long transactionReference) {
        this.account = account;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
        this.transactionReference = transactionReference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
//manually added
    public void setAccount(Long accountId) {
        this.account.setId(accountId);
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Wallet other = (Wallet) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
