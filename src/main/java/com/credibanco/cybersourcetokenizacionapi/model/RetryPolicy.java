package com.credibanco.cybersourcetokenizacionapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetryPolicy {

  private String algorithm;

  private int firstRetry;

  private int interval;

  private int numberOfRetries;

  private boolean deactivateFlag;

  private int repeatSequenceCount;

  private int repeatSequenceWaitTime;



}
