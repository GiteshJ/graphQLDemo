package com.example.graphQLDemo.resolver.bank.mutation;

import com.example.graphQLDemo.domain.bank.BankAccount;
import com.example.graphQLDemo.domain.bank.Client;
import com.example.graphQLDemo.domain.bank.Currency;
import com.example.graphQLDemo.domain.bank.input.CreateBankAccountInput;
import com.example.graphQLDemo.publisher.BankAccountPublisher;
import graphql.kickstart.tools.GraphQLMutationResolver;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Slf4j
@RequiredArgsConstructor
@Validated
public class BankAccountMutationResolver implements GraphQLMutationResolver {

  private final Clock clock;
  private final BankAccountPublisher bankAccountPublisher;
  public BankAccount createBankAccount(@Valid CreateBankAccountInput input){
    log.info("Creating Bank Account For -{}", input.toString());
    BankAccount bankAccount = BankAccount.builder()
        .id(UUID.randomUUID())
        .currency(Currency.USD)
        .client(Client.builder()
            .id(UUID.randomUUID())
            .firstName(input.getFirstName())
            .build())
        .createdAt(ZonedDateTime.now(clock))
        .createdOn(LocalDate.now())
        .build();
    bankAccountPublisher.publish(bankAccount);

    return bankAccount;
  }

  public BankAccount updateBankAccount(UUID id, String name, int age) {
    log.info("Updating bank account for {}. Name: {}, age: {}", id, name, age);
    return getBankAccount(id);
  }

  private BankAccount getBankAccount(UUID id) {
    var bankAccount = BankAccount.builder()
        .id(id)
        .currency(Currency.USD)
        .createdAt(ZonedDateTime.now(clock))
        .createdOn(LocalDate.now(clock))
        .build();

    /**
     * Subscription
     */
    bankAccountPublisher.publish(bankAccount);

    return bankAccount;
  }

}
