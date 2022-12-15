package com.credibanco.cybersourcetokenizacionapi.model.errors;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorInformation {

  private String reason;

  private List<String> details;
}
