package com.credibanco.cybersourcetokenizacionapi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WebHookSecurityKey {

private ClientReferenceInformation clientReferenceInformation;

private String clientRequestAction;

private KeyInformation keyInformation;
}
