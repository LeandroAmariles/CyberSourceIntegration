package com.credibanco.cybersourcetokenizacionapi.model;

import com.credibanco.cybersourcetokenizacionapi.model.errors.ErrorInformation;
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
public class KeyInformation {


  private String provider;

  private String tenant;

  private String keyType;

  private String organizationId;

  private String clientKeyId;

  private String keyId;

  private String key;

  private String status;

  private String expiryDuration;

  private String message;

  private ErrorInformation errorInformation;

}
