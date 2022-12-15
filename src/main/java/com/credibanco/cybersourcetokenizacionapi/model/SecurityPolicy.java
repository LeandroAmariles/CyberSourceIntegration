package com.credibanco.cybersourcetokenizacionapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityPolicy {

  private String SecurityType;

  private String proxyType;

  private Config config;
}
