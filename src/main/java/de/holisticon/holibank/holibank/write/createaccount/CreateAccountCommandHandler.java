package de.holisticon.holibank.holibank.write.createaccount;

import de.holisticon.holibank.holibank.events.AccountCreated;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.gateway.EventAppender;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.annotation.EventSourcedEntity;
import org.axonframework.modelling.annotation.InjectEntity;

import java.util.List;

public class CreateAccountCommandHandler {

    @CommandHandler
    void handle(
            CreateAccount command,
            @InjectEntity(idProperty = "accountId") State state,
            EventAppender eventAppender
    ) {
        eventAppender.append(decide(command, state));
    }

    private List<AccountCreated> decide(CreateAccount command, State state) {
        if (state.created) {
            return List.of();
        }

        return List.of(new AccountCreated(command.accountId(), command.firstname(), command.lastname(), 0));
    }

    @EventSourcedEntity(tagKey = "accountId")
    static final class State {

        private boolean created = false;

        @EventSourcingHandler
        State evolve(AccountCreated event) {
            this.created = true;
            return this;
        }
    }

}
