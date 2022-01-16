package com.example.graphQLDemo.resolver.bank.subscription;


import com.example.graphQLDemo.domain.bank.BankAccount;
import com.example.graphQLDemo.publisher.BankAccountPublisher;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BankAccountSubscription implements GraphQLSubscriptionResolver {

  private final BankAccountPublisher bankAccountPublisher;

  public Publisher<BankAccount> bankAccounts() {
    return bankAccountPublisher.getBankAccountPublisher();
  }

  public Publisher<BankAccount> bankAccount(UUID id) {
    return bankAccountPublisher.getBankAccountPublisherFor(id);
  }
}
