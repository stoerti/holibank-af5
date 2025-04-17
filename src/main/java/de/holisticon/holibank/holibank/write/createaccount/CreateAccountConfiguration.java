package de.holisticon.holibank.holibank.write.createaccount;

import de.holisticon.holibank.configuration.AxonConfigurer;
import org.axonframework.eventsourcing.configuration.EventSourcedEntityBuilder;
import org.axonframework.eventsourcing.configuration.EventSourcingConfigurer;
import org.axonframework.modelling.configuration.StatefulCommandHandlingModule;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountConfiguration implements AxonConfigurer {

    public void configure(EventSourcingConfigurer configurer) {
        var stateEntity = EventSourcedEntityBuilder
                .annotatedEntity(String.class, CreateAccountCommandHandler.State.class);

        var commandHandlingModule = StatefulCommandHandlingModule
                .named("CreateAccount")
                .entities()
                .entity(stateEntity)
                .commandHandlers()
                .annotatedCommandHandlingComponent(c -> new CreateAccountCommandHandler());

        configurer.registerStatefulCommandHandlingModule(commandHandlingModule);
    }
}
