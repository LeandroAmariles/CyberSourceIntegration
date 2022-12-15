package com.credibanco.cybersourcetokenizacionapi.integration;

import com.credibanco.cybersourcetokenizacionapi.authenticator.Jwt;
import com.credibanco.cybersourcetokenizacionapi.dto.request.InstrumentIdentifierRequest;
import com.credibanco.cybersourcetokenizacionapi.dto.response.InstrumentIdentifierResponse;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Calendar;


@Component
@Log4j2
public class TmsInstrumentIdentifiersServiceRest implements TmsInstrumentIdentifiersService {


  @Value("${tms.url}")
  private String url;

  @Value("${tms.identifierUrl}")
  private String identifierUrl;

  @Value("${tms.host}")
  private String host;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private Jwt jwt;


  @Override
  public InstrumentIdentifierResponse createInstrumentIdentifierCardAndEnrrollForNetworkToken
      (InstrumentIdentifierRequest request)
      throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {

    HttpHeaders headers = new HttpHeaders();
    String stringRequest = new Gson().toJson(request);
    String jwtToken = jwt.generateJwt(stringRequest, "POST");
    headers.set("Authorization", jwtToken);
    headers.set("host", host);
    headers.setContentType(MediaType.APPLICATION_JSON);
    log.info("Sending request---> " + stringRequest);
    String path = url + identifierUrl;
    HttpEntity<String> requestEntity = new HttpEntity<>(stringRequest, headers);
    log.info("Posting --->" + path);
    ResponseEntity<String> responseEntity =
        restTemplate.exchange(path, HttpMethod.POST, requestEntity, String.class);
    if (!responseEntity.getStatusCode().is2xxSuccessful()) {
      return null;
    }
    String response = responseEntity.getBody();
    log.info("Response ---> " + response);
    return new Gson().fromJson(response, InstrumentIdentifierResponse.class);
  }

  @Override
  public InstrumentIdentifierResponse getAnInstrumentIdentifier(String tokenId)
      throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {

    String path = url + identifierUrl + "/" + tokenId;
    log.info("------->" + path);
    HttpHeaders headers = new HttpHeaders();
    String jwtToken = jwt.generateJwt(null, "GET");
    log.info("Token->" + jwtToken);
    headers.set("Authorization", jwtToken);
    headers.set("host", host);
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
    ResponseEntity<String> responseEntity =
        restTemplate.exchange(path, HttpMethod.GET, requestEntity, String.class);
    log.info(responseEntity.getBody());
    if (!responseEntity.getStatusCode().is2xxSuccessful()) {
      return null;
    }
    String response = responseEntity.getBody();
    return new Gson().fromJson(response, InstrumentIdentifierResponse.class);
  }

  @Override
  public void deleteInstrumentIdentifier(String tokenId)
      throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {
    String path = url + identifierUrl + "/" + tokenId;
    log.info("Deleting -->" + path);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", jwt.generateJwt(null, "DELETE"));
    headers.set("host",host);
    HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
    ResponseEntity<String> responseEntity =
        restTemplate.exchange(path, HttpMethod.DELETE, requestEntity, String.class);

    }




}
