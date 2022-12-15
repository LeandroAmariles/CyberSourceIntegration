package com.credibanco.cybersourcetokenizacionapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Config {

  private String oAuthTokenExpiry;

  private String oAuthURL;

  private String oAuthTokenType;
}
