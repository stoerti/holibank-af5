package de.holisticon.holibank.configuration;

import org.axonframework.eventsourcing.configuration.EventSourcingConfigurer;

public interface AxonConfigurer {
    /**
     * Configures the Axon framework.
     *
     * @param configurer the Axon configuration
     */
    void configure(EventSourcingConfigurer configurer);
}
