package com.example.graphQLDemo.domain.bank;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

  UUID id;
}