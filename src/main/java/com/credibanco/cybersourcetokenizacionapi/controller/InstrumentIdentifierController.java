package com.credibanco.cybersourcetokenizacionapi.controller;

import com.credibanco.cybersourcetokenizacionapi.dto.request.InstrumentIdentifierRequest;
import com.credibanco.cybersourcetokenizacionapi.dto.response.InstrumentIdentifierResponse;
import com.credibanco.cybersourcetokenizacionapi.integration.TmsInstrumentIdentifiersServiceRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

@RestController
@RequestMapping("/tms/v1/instrumentidentifiers")
public class InstrumentIdentifierController {

  @Autowired
  private TmsInstrumentIdentifiersServiceRest tmsInstrumentIdentifiersServiceRest;

  @PostMapping
  ResponseEntity<InstrumentIdentifierResponse> createInstrumentIdentifiers
      (@RequestBody InstrumentIdentifierRequest instrumentIdentifierRequest)
      throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {
    InstrumentIdentifierResponse response = tmsInstrumentIdentifiersServiceRest
        .createInstrumentIdentifierCardAndEnrrollForNetworkToken(instrumentIdentifierRequest);
    return new ResponseEntity(response, HttpStatus.CREATED);
  }
  @GetMapping("/{instrumentIdentifierTokenId}")
  ResponseEntity<InstrumentIdentifierResponse> getInstrumentIdentifier
      (@PathVariable String instrumentIdentifierTokenId)
      throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {
    InstrumentIdentifierResponse response = tmsInstrumentIdentifiersServiceRest
        .getAnInstrumentIdentifier(instrumentIdentifierTokenId);
    return new ResponseEntity(response, HttpStatus.OK);
  }

  @DeleteMapping("/{instrumentIdentifierTokenId}")
  ResponseEntity<?> deleteInstrumentIdentifier
      (@PathVariable String instrumentIdentifierTokenId)
      throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {
    tmsInstrumentIdentifiersServiceRest.deleteInstrumentIdentifier(instrumentIdentifierTokenId);
    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
  }
}
