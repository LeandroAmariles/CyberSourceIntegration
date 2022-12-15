package com.credibanco.cybersourcetokenizacionapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BillTo {

  private String address1;

  private String address2;

  private String locality;

  private String administrativeArea;

  private String postalCode;

  private String country;

}
