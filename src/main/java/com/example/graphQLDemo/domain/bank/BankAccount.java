package com.example.graphQLDemo.domain.bank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BankAccount {

  UUID id;
  Client client;
  Currency currency;
  List<Asset> assets;
  ZonedDateTime createdAt;
  LocalDate createdOn;
  BigDecimal balance;
}
