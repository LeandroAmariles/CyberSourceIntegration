package com.credibanco.cybersourcetokenizacionapi.dto.response;

import com.credibanco.cybersourcetokenizacionapi.model.ClientReferenceInformation;
import com.credibanco.cybersourcetokenizacionapi.model.KeyInformation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateWebHookSecurityKeyResponse {

  private String submitTimeUtc;

  private String status;

  private ClientReferenceInformation clientReferenceInformation;

  private KeyInformation keyInformation;
}
