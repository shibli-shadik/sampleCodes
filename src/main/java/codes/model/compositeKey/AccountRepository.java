package codes.model.compositeKey;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, AccountId> 
{
    List<Account> findByAccountType(String accountType);
}
