package com.credibanco.cybersourcetokenizacionapi.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Base64;

@Getter
@Setter
public class TmkResponse {

  private Base64 encodedJwe;
}
