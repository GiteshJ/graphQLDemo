package com.example.graphQLDemo.resolver.bank.query;

import com.example.graphQLDemo.connection.CursorUtil;
import com.example.graphQLDemo.context.CustomGraphQLContext;
import com.example.graphQLDemo.domain.bank.BankAccount;
import com.example.graphQLDemo.domain.bank.Client;
import com.example.graphQLDemo.domain.bank.Currency;
import com.example.graphQLDemo.repository.BankAccountRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.Connection;
import graphql.relay.DefaultConnection;
import graphql.relay.DefaultEdge;
import graphql.relay.DefaultPageInfo;
import graphql.relay.Edge;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.SelectedField;
import java.time.Clock;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BankAccountResolver implements GraphQLQueryResolver {

  private final BankAccountRepository bankAccountRepository;
  private final CursorUtil cursorUtil;
  private final Clock clock;

  public BankAccount bankAccount(UUID id, DataFetchingEnvironment environment) {

    log.info("Retrieving Bank Account Id: {}", id);

    var requestedFields = environment.getSelectionSet().getFields().stream()
        .map(SelectedField::getName).collect(Collectors.toUnmodifiableSet());

    log.info("Requested Fields: {}", requestedFields);

    CustomGraphQLContext context = environment.getContext();

    log.info("User Id - {}", context.getUserId());

    return BankAccount.builder().id(id).currency(Currency.USD).build();
  }

  public Connection<BankAccount> bankAccounts(int first, @Nullable String cursor) {
    List<Edge<BankAccount>> edges = getBankAccounts(cursor)
        .stream()
        .map(bankAccount -> new DefaultEdge<>(bankAccount,
            cursorUtil.createCursorWith(bankAccount.getId())))
        .limit(first)
        .collect(Collectors.toUnmodifiableList());

    var pageInfo = new DefaultPageInfo(
        cursorUtil.getFirstCursorFrom(edges),
        cursorUtil.getLastCursorFrom(edges),
        cursor != null,
        edges.size() > first);

    return new DefaultConnection<>(edges, pageInfo);
  }

  public List<BankAccount> getBankAccounts(String cursor) {
    if (cursor == null) {
      return bankAccountRepository.getBankAccounts();
    }
    return bankAccountRepository.getBankAccountsAfter(cursorUtil.decode(cursor));
  }
}
