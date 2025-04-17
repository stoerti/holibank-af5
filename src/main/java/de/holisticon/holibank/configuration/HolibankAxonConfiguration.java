package de.holisticon.holibank.configuration;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.configuration.AxonConfiguration;
import org.axonframework.eventsourcing.configuration.EventSourcingConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HolibankAxonConfiguration {

    @Bean
    public AxonConfiguration axonConfiguration(List<AxonConfigurer> configurers) {
        var configurer = EventSourcingConfigurer.create();
        for (AxonConfigurer axonConfigurer : configurers) {
            axonConfigurer.configure(configurer);
        }

        return configurer.start();
    }

    @Bean
    public CommandGateway commandGateway(AxonConfiguration configurer) {
        return configurer.getComponent(CommandGateway.class);
    }
}
