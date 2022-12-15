package com.credibanco.cybersourcetokenizacionapi.model.errors;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NetworkTokenError {

  private String type;

  private String message;
}
