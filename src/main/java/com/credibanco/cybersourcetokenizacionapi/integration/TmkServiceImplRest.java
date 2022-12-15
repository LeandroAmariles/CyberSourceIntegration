package com.credibanco.cybersourcetokenizacionapi.integration;

import com.credibanco.cybersourcetokenizacionapi.authenticator.Jwt;
import com.credibanco.cybersourcetokenizacionapi.dto.request.InstrumentIdentifierRequest;
import com.credibanco.cybersourcetokenizacionapi.dto.response.TmkResponse;
import com.google.gson.Gson;
import com.nimbusds.jose.JWEObject;
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
import java.text.ParseException;
import java.util.Base64;

@Slf4j
@Service
public class TmkServiceImplRest implements TmkService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private Jwt jwt;

  @Value("${tms.host}")
  private String host;

  @Value("${tms.url}")
  private String url;

  @Value("${tms.paymentCredentialsUrl}")
  private String paymentCredentialsUrl;

  @Value("${tms.paymentCredentialsEnrollmentUrl}")
  private String paymentCredentialsEnrollmentUrl;

  @Override
  public TmkResponse paymentCredentials(String instrumentIdentifierId)
          throws UnrecoverableEntryException, CertificateException,
          KeyStoreException, IOException, NoSuchAlgorithmException {

    String path = (url + paymentCredentialsUrl)
        .replaceAll("\\{tms-instrumentIdentifier-id\\}",instrumentIdentifierId);
    log.info("Consulting ---->"+ path);
    String jwtToken = jwt.generateJwt(null, "POST");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", jwtToken);
    HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
    ResponseEntity<String> responseEntity =
        restTemplate.exchange(path, HttpMethod.POST,requestEntity,String.class);
    if(!responseEntity.getStatusCode().is2xxSuccessful()){
      return null;
    }
    String response = responseEntity.getBody();
    log.info("Response----> " + response);
    return new Gson().fromJson(response, TmkResponse.class);
  }

  @Override
  public TmkResponse paymentCredentialsExistingToken
      (String instrumentIdentifierId, InstrumentIdentifierRequest instrumentIdentifierRequest)
          throws UnrecoverableEntryException, CertificateException,
          KeyStoreException, IOException, NoSuchAlgorithmException {

    String path  = (url+ paymentCredentialsEnrollmentUrl)
        .replaceAll("\\{tms-instrumentIdentifier-id\\}",instrumentIdentifierId);
    log.info("Consulting --->"+path);
    String requestString = new Gson().toJson(instrumentIdentifierRequest);
    String jwtToken = jwt.generateJwt(requestString,"POST");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", jwtToken);
    headers.set("host",host);
    HttpEntity<String> requestHttpEntity = new HttpEntity<>(requestString,headers);
    ResponseEntity<String> responseEntity =
        restTemplate.exchange(path,HttpMethod.POST,requestHttpEntity, String.class);
    if(!responseEntity.getStatusCode().is2xxSuccessful()){
      return null;
    }
    String response = responseEntity.getBody();
    log.info("Response --> "+ response);
    return new Gson().fromJson(responseEntity.getBody(), TmkResponse.class);
  }

  private void decodeBase64(TmkResponse tmkResponse) throws ParseException {
    String originalString = tmkResponse.getEncodedJwe().toString();

    byte[] bytesDecoded = Base64.getDecoder().decode(originalString);
    String decodedString = new String(bytesDecoded);

    JWEObject object = JWEObject.parse(decodedString);

  }

}
