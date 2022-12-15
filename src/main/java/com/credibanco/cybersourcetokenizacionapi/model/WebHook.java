package com.credibanco.cybersourcetokenizacionapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebHook {

  private String name;
  private String description;
  private String organizationId;
  private String productId;
  private List<String> eventTypes;
  private String webhookUrl;
  private String healthCheckUrl;
  private NotificationScope notificationScope;
  private RetryPolicy retryPolicy;
  private SecurityPolicy securityPolicy;
  private String createdOn;
  private String updatedOn;
  private ArrayList<String> additionalAttributes;

}
