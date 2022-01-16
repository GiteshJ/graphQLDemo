package com.example.graphQLDemo.domain.bank.input;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateBankAccountInput {

  @NotBlank
  String firstName;
  int age;
}
