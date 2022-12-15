package com.credibanco.cybersourcetokenizacionapi.integration;

import com.credibanco.cybersourcetokenizacionapi.dto.request.InstrumentIdentifierRequest;
import com.credibanco.cybersourcetokenizacionapi.dto.response.TmkResponse;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public interface TmkService {

TmkResponse paymentCredentials(String instrumentIdentifierId) throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException;

TmkResponse paymentCredentialsExistingToken
    (String instrumentIdentifierId, InstrumentIdentifierRequest instrumentIdentifierRequest) throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException;
}
