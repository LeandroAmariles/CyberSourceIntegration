package com.credibanco.cybersourcetokenizacionapi.controller;

import com.credibanco.cybersourcetokenizacionapi.dto.request.InstrumentIdentifierRequest;
import com.credibanco.cybersourcetokenizacionapi.dto.response.TmkResponse;
import com.credibanco.cybersourcetokenizacionapi.integration.TmkServiceImplRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping
public class NetworkTokenController {

    @Autowired
    private TmkServiceImplRest tmkServiceImplRest;

    @PostMapping("/tms/v1/instrumentidentifiers/{tmsId}/enrollment")
    ResponseEntity<TmkResponse> paymentCredentialsExistingToken
            (@PathVariable String tmsId,
             @RequestBody InstrumentIdentifierRequest request)
            throws UnrecoverableEntryException, CertificateException,
            KeyStoreException, IOException, NoSuchAlgorithmException {

        TmkResponse response = tmkServiceImplRest.paymentCredentialsExistingToken(tmsId, request);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/tms/v2/tokens/{tmsId}/payment-credentials")
    ResponseEntity<TmkResponse> paymentCredentials(@PathVariable String tmsId)
            throws UnrecoverableEntryException, CertificateException,
            KeyStoreException, IOException, NoSuchAlgorithmException {
        TmkResponse response = tmkServiceImplRest.paymentCredentials(tmsId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
