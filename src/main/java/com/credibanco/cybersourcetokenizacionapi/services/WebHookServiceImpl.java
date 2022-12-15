package com.credibanco.cybersourcetokenizacionapi.services;

import com.credibanco.cybersourcetokenizacionapi.authenticator.Jwt;
import com.credibanco.cybersourcetokenizacionapi.dto.response.CreateWebHookSecurityKeyResponse;
import com.credibanco.cybersourcetokenizacionapi.dto.response.SubscribeWebHookResponse;
import com.credibanco.cybersourcetokenizacionapi.model.KeyInformation;
import com.credibanco.cybersourcetokenizacionapi.model.NotificationScope;
import com.credibanco.cybersourcetokenizacionapi.model.RetryPolicy;
import com.credibanco.cybersourcetokenizacionapi.model.SecurityPolicy;
import com.credibanco.cybersourcetokenizacionapi.model.WebHook;
import com.credibanco.cybersourcetokenizacionapi.model.WebHookSecurityKey;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.List;

@Slf4j
@Service
public class WebHookServiceImpl implements WebHookService{

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private Jwt jwt;

  @Value("${tms.url}")
  private String url;

  @Value("${webhook.createWebHook}")
  private String createWebhookUrl;

  @Value("${webhook.retryPolicy.firstRetry}")
  private int firstRetry;

  @Value("${webhook.retryPolivy.interval}")
  private int interval;

  @Value("${webhook.retryPolicy.numberOfRetries}")
  private int numberOfRetries;

  @Value("${webhook.retryPolicy.deactivateFlag}")
  private boolean deactivateFlag;

  @Value("${webhook.retryPolicy.repeatSequenceCount}")
  private int repeatSequenceCount;

  @Value("${webhook.retryPolicy.repeatSequenceWaitTime}")
  private int repeatSequenceWaitTime;

  @Value("${webhook.securityPolicy.securityType}")
  private String securityType;

  @Value("${webhook.securityPolicy.proxyType}")
  private String proxyType;

  @Value("${webhook.retryPolicy.algorithm}")
  private String algorithm;

  @Value("${webhook.productId}")
  private String productId;

  @Value("${webhook.createKeys.url}")
  private String pathKeys;

  @Value("${webhook.events.type}")
  private List<String> eventsType;

  @Value("${webhook.name}")
  private String webHookName;

  @Value("${webhook.notificationScope}")
  private String scope;

  @Value("${webhook.provider}")
  private String provider;

  @Value("${webhook.tenant}")
  private String tenant;

  @Value("${merchantId}")
  private String organizationId;




  @Override
  public CreateWebHookSecurityKeyResponse createWebHookSecurityKeys()
          throws UnrecoverableEntryException, CertificateException,
          KeyStoreException, IOException, NoSuchAlgorithmException {

    KeyInformation keyInformation = KeyInformation.builder()
        .provider(provider)
        .tenant(tenant)
        .keyType("sharedSecret")
        .organizationId(organizationId)
        .build();
    WebHookSecurityKey webHookSecurityKey = WebHookSecurityKey.builder()
        .clientRequestAction("CREATE")
        .keyInformation(keyInformation)
        .build();


    String jsonRequest = new Gson().toJson(webHookSecurityKey);
    HttpHeaders headers = new HttpHeaders();
    String jwtToken = jwt.generateJwt(jsonRequest,"POST");
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", jwtToken);
    log.info("Sending request ---> "+ jsonRequest);
    HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequest, headers);
    String path = url + pathKeys;
    ResponseEntity<String> responseEntity =
        restTemplate.exchange(path, HttpMethod.POST,requestEntity,String.class );

    if(!responseEntity.getStatusCode().is2xxSuccessful()) {
      return null;
    }
    String response = responseEntity.getBody();
    log.info("Response --->"+ response);
    return new Gson().fromJson(response, CreateWebHookSecurityKeyResponse.class);
  }

  @Override
  public SubscribeWebHookResponse createWebhookSubscription() {
    RetryPolicy retryPolicy = RetryPolicy.builder()
        .algorithm(algorithm)
        .firstRetry(firstRetry)
        .interval(interval)
        .numberOfRetries(numberOfRetries)
        .deactivateFlag(deactivateFlag)
        .repeatSequenceCount(repeatSequenceCount)
        .repeatSequenceWaitTime(repeatSequenceWaitTime)
        .build();

    NotificationScope notificationScope = NotificationScope.builder()
        .scope(scope)
        .build();

    SecurityPolicy securityPolicy = SecurityPolicy.builder()
        .SecurityType(securityType)
        .proxyType(proxyType)
        .build();

    WebHook webHook = WebHook.builder()
        .name(webHookName)
        .description("")
        .organizationId("TMS Vault Owner excluding _acct")
        .productId(productId)
        .eventTypes(eventsType)
        .webhookUrl("")
        .healthCheckUrl("")
        .notificationScope(notificationScope)
        .retryPolicy(retryPolicy)
        .securityPolicy(securityPolicy)
        .build();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<WebHook> requestEntity = new HttpEntity<>(webHook, headers);
    String path = url+createWebhookUrl;
    log.info("Creating new WebHook Subcribe---->"+path);
    ResponseEntity<SubscribeWebHookResponse> responseEntity =
        restTemplate.exchange(path,HttpMethod.POST,requestEntity, SubscribeWebHookResponse.class);
    if(!responseEntity.getStatusCode().is2xxSuccessful()){
      return null;
    }
    return responseEntity.getBody();
  }
}
