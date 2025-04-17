package de.holisticon.holibank.rest;

import de.holisticon.holibank.holibank.read.CurrentBalanceReadModel;
import de.holisticon.holibank.holibank.write.createaccount.CreateAccount;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AccountController {

    private final CommandGateway commandGateway;
    private final CurrentBalanceReadModel currentBalanceReadModel;

    public AccountController(CommandGateway commandGateway, CurrentBalanceReadModel currentBalanceReadModel) {
        this.commandGateway = commandGateway;
        this.currentBalanceReadModel = currentBalanceReadModel;
    }

    @PutMapping("/account/")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountRequest request) {
        String accountId = UUID.randomUUID().toString();

        commandGateway.sendAndWait(new CreateAccount(
                accountId,
                request.firstname(),
                request.lastname()
        ));

        return ResponseEntity.ok(accountId);
    }

    @GetMapping("/account/{accountId}/balance")
    public ResponseEntity<Integer> getCurrentBalance(@PathVariable("accountId") String accountId) {
        Integer balance = currentBalanceReadModel.getCurrentBalance(accountId);
        if (balance == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(balance);
    }


    public record CreateAccountRequest(String firstname, String lastname) {
    }
}
