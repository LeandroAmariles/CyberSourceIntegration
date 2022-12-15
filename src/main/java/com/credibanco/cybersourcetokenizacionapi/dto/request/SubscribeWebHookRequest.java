package com.credibanco.cybersourcetokenizacionapi.dto.request;

import com.credibanco.cybersourcetokenizacionapi.model.NotificationScope;
import com.credibanco.cybersourcetokenizacionapi.model.RetryPolicy;
import com.credibanco.cybersourcetokenizacionapi.model.SecurityPolicy;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscribeWebHookRequest {
  
  @SerializedName("name")
  private String name;
  @SerializedName("description")
  private String description;
  @SerializedName("organizationId")
  private String organizationId;
  @SerializedName("productId")
  private String productId;
  @SerializedName("eventTypes")
  private List<String> eventTypes;
  @SerializedName("webhookUrl")
  private String webhookUrl;
  @SerializedName("healthCheckUrl")
  private String healthCheckUrl;
  @SerializedName("notificationScope")
  private NotificationScope notificationScope;
  @SerializedName("retryPolicy")
  private RetryPolicy retryPolicy;
  @SerializedName("securityPolicy")
  private SecurityPolicy securityPolicy;


}
