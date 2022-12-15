package com.credibanco.cybersourcetokenizacionapi.authenticator;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.MessageDigest;
import java.security.interfaces.RSAPrivateKey;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.StringTokenizer;


@Slf4j
@Component
public class Jwt {


  private String keyPassword;

  @Value("${merchantId}")
  private String merchantId;


  @Autowired
  private JwtCryptoProcessor jwtCryptoProcessor;

  public String generateJwt(String request, String method)
      throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableEntryException {

    String token = "";
    try {

      KeyStore merchantKeyStore = KeyStore.getInstance("PKCS12", new BouncyCastleProvider());

      FileInputStream keyFile = new FileInputStream("src/main/resources/credibanco_tms.p12");
      merchantKeyStore.load(keyFile, merchantId.toCharArray());

      String merchantKeyAlias = null;
      Enumeration<String> enumKeyStore = merchantKeyStore.aliases();
      ArrayList<String> array = new ArrayList<String>();

      while (enumKeyStore.hasMoreElements()) {
        merchantKeyAlias = (String) enumKeyStore.nextElement();
        log.info(merchantKeyAlias);
        array.add(merchantKeyAlias);
        if (merchantKeyAlias.contains(merchantId)) {
          break;
        }
      }

     KeyStore.PrivateKeyEntry e = (KeyStore.PrivateKeyEntry) merchantKeyStore.getEntry(merchantKeyAlias,
          new KeyStore.PasswordProtection(merchantId.toCharArray()));


      RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) e.getPrivateKey();

      log.info("privateKey"+rsaPrivateKey.toString());

      merchantKeyAlias = keyAliasValidator(array, merchantId);

      log.info("----> "+merchantKeyAlias);

      e = (KeyStore.PrivateKeyEntry) merchantKeyStore.getEntry(merchantKeyAlias,
          new KeyStore.PasswordProtection(merchantId.toCharArray()));

      X509Certificate certificate = (X509Certificate) e.getCertificate();

      String encryptedSigMessage = "";

      if (request != null && !request.isEmpty()) {
        MessageDigest jwtBody = MessageDigest.getInstance("SHA-256");
        byte[] jwtClaimSetBody = jwtBody.digest(request.getBytes());
        encryptedSigMessage = Base64.getEncoder().encodeToString(jwtClaimSetBody);
      }
      String claimSet = null;
      String gmtDateTime = DateTimeFormatter.RFC_1123_DATE_TIME.format(
          ZonedDateTime.now(ZoneId.of("GMT")));

      if (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("DELETE")) {
        claimSet = "{\n            \"iat\":\"" + gmtDateTime
            + "\"\n} \n\n";
        log.info(claimSet.toString());
      } else if (method.equalsIgnoreCase("POST")) {
        claimSet = "{\n            \"digest\":\"" + encryptedSigMessage
            + "\",\n            \"digestAlgorithm\":\"SHA-256\",\n            \"iat\":\""
            + ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT) + "\"\n} \n\n";
        log.info(claimSet.toString());
      }

      // Creating the Signature Header
      HashMap<String, Object> customHeaders = new HashMap<String, Object>();
      if (merchantId != null) {
        customHeaders.put("v-c-merchant-id", merchantId);
      }

      log.info("\t JWT Body : " + claimSet);

      token = jwtCryptoProcessor.sign(claimSet, rsaPrivateKey, certificate, customHeaders);
    }
    catch (Exception e){
      log.info("ERROR: "+ e.getMessage());
    }

        return "Bearer " + token;

  }

  private static String keyAliasValidator(ArrayList<String> array, String merchantID) {
    int size = array.size();
    String tempKeyAlias, merchantKeyAlias, result;
    StringTokenizer str;
    for (int i = 0; i < size; i++) {
      merchantKeyAlias = array.get(i).toString();
      str = new StringTokenizer(merchantKeyAlias, ",");
      while (str.hasMoreTokens()) {
        tempKeyAlias = str.nextToken();
        if (tempKeyAlias.contains("CN")) {
          str = new StringTokenizer(tempKeyAlias, "=");
          while (str.hasMoreElements()) {
            result = str.nextToken();
            if (result.equalsIgnoreCase(merchantID)) {
              return merchantKeyAlias;
            }
          }
        }
      }
    }

    return null;
  }

}
