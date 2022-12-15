package com.credibanco.cybersourcetokenizacionapi.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentIdentifier {

  private Long id;

  private String type;

  private Card card;

  private BillTo billTo;


}
