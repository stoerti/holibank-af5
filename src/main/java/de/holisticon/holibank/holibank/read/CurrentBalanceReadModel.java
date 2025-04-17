package de.holisticon.holibank.holibank.read;

import de.holisticon.holibank.holibank.events.AccountCreated;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CurrentBalanceReadModel {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(CurrentBalanceReadModel.class);

    private final Map<String, Integer> balances = new HashMap<>();

    @EventHandler
    public void on(AccountCreated event) {
        LOG.debug("Account {} created with initial balance {}", event.accountId(), event.initialBalance());
        balances.put(event.accountId(), event.initialBalance());
    }

    public Integer getCurrentBalance(String accountId) {
        return balances.get(accountId);
    }
}
