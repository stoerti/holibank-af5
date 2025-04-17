package de.holisticon.holibank.write.createaccount;

import de.holisticon.holibank.configuration.HolibankAxonConfiguration;
import de.holisticon.holibank.holibank.events.AccountCreated;
import de.holisticon.holibank.holibank.write.createaccount.CreateAccount;
import de.holisticon.holibank.holibank.write.createaccount.CreateAccountConfiguration;
import org.axonframework.test.fixture.AxonTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateAccountTest {

    private AxonTestFixture testFixture;

    @BeforeEach
    void setUp() {
        var configurer = new HolibankAxonConfiguration().eventSourcingConfigurer(
                List.of(new CreateAccountConfiguration())
        );
        testFixture = AxonTestFixture.with(configurer);
    }

    @Test
    void givenNotExistingAccount_WhenCreateAccount_ThenSuccess() {
        var accountId = "1234567890";
        var firstName = "John";
        var lastName = "Doe";
        testFixture.given()
                .when()
                .command(new CreateAccount(accountId, firstName, lastName))
                .then()
                .success()
                .events(new AccountCreated(accountId, firstName, lastName, 0));
    }

    @Test
    void givenExistingAccount_WhenCreateAccount_ThenNoEvents() {
        var accountId = "1234567890";
        var firstName = "John";
        var lastName = "Doe";
        testFixture.given()
                .event(new AccountCreated(accountId, firstName, lastName, 0))
                .when()
                .command(new CreateAccount(accountId, firstName, lastName))
                .then()
                .success()
                .noEvents();
    }
}
