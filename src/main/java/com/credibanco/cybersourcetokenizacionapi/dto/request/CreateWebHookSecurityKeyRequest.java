package com.credibanco.cybersourcetokenizacionapi.dto.request;

import com.credibanco.cybersourcetokenizacionapi.model.KeyInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateWebHookSecurityKeyRequest {

  private String clientRequestAction;

  private KeyInformation keyInformation;
}
