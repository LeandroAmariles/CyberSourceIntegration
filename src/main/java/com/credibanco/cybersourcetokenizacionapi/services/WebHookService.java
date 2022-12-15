package com.credibanco.cybersourcetokenizacionapi.services;

import com.credibanco.cybersourcetokenizacionapi.dto.response.CreateWebHookSecurityKeyResponse;
import com.credibanco.cybersourcetokenizacionapi.dto.response.SubscribeWebHookResponse;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;


public interface WebHookService {

  CreateWebHookSecurityKeyResponse createWebHookSecurityKeys ()
          throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException;

  SubscribeWebHookResponse createWebhookSubscription ();
}
