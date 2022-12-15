package com.credibanco.cybersourcetokenizacionapi.controller;

import com.credibanco.cybersourcetokenizacionapi.dto.response.CreateWebHookSecurityKeyResponse;
import com.credibanco.cybersourcetokenizacionapi.services.WebHookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

@RestController
@RequestMapping("/webhook")
public class WebHookController {

    @Autowired
    private WebHookService webHookService;


    @PostMapping("/webhook-security-keys")
    ResponseEntity<CreateWebHookSecurityKeyResponse> webHookSecurityKeys()
            throws UnrecoverableEntryException, CertificateException, KeyStoreException,
            IOException, NoSuchAlgorithmException {

        CreateWebHookSecurityKeyResponse response =
                webHookService.createWebHookSecurityKeys();

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
