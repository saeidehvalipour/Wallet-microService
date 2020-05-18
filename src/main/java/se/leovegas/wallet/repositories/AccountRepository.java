package se.leovegas.wallet.repositories;

import se.leovegas.wallet.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
