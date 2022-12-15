package com.credibanco.cybersourcetokenizacionapi.dto.response;

import com.credibanco.cybersourcetokenizacionapi.model.NotificationScope;
import com.credibanco.cybersourcetokenizacionapi.model.RetryPolicy;
import com.credibanco.cybersourcetokenizacionapi.model.SecurityPolicy;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SubscribeWebHookResponse {

  @SerializedName("webhookId")
  private String webhookId;
  @SerializedName("organizationI")
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
  @SerializedName("status")
  private String status;
  @SerializedName("name")
  private String name;
  @SerializedName("description")
  private String description;
  @SerializedName("retryPolicy")
  private RetryPolicy retryPolicy;
  @SerializedName("securityPolicy")
  private SecurityPolicy securityPolicy;
  @SerializedName("createdOn")
  private String createdOn;
  @SerializedName("updatedOn")
  private String updatedOn;
  @SerializedName("additionalAttribute")
  private ArrayList<String> additionalAttributes;

}
