package de.holisticon.holibank.holibank.events;

import org.axonframework.eventsourcing.annotations.EventTag;

public record AccountCreated(
        @EventTag(key = "accountId") String accountId,
        String firstname,
        String lastname,
        int initialBalance
) {
}
