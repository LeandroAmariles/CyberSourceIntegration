package com.credibanco.cybersourcetokenizacionapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Card {

  private String number;

  private String expirationMonth;

  private String expirationYear;

  private String securityCode;

}
