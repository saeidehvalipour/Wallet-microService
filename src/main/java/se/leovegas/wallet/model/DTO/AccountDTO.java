package se.leovegas.wallet.model.DTO;

import se.leovegas.wallet.model.Account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDTO {

    private Long id;
    private String playerName;
    private Date dateCreated;
    private BigDecimal balance;

    public AccountDTO() {
    }

    public AccountDTO(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public static Account buildFromAccountDTO(AccountDTO a) {
        Account account = new Account();
        account.setDateCreated(new Date());
        account.setId(a.getId());
        account.setPlayerName(a.getPlayerName());
        return account;
    }

    public static AccountDTO toAccountDTO(Account a) {
        double balance = a.getTransactions().stream().mapToDouble(o -> o.getAmount().doubleValue()).sum();
        AccountDTO dto = new AccountDTO(a.getId());
        dto.setDateCreated(a.getDateCreated());
        dto.setPlayerName(a.getPlayerName());
        dto.setId(a.getId());
        dto.setBalance(new BigDecimal(balance));
        return dto;
    }

    public static List<AccountDTO> doToDTOList(List<Account> accountList) {
        return accountList.stream()
                .map(AccountDTO::toAccountDTO)
                .collect(Collectors.toList());
    }

}
