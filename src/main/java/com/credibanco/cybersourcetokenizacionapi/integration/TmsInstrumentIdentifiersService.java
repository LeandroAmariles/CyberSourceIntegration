package com.credibanco.cybersourcetokenizacionapi.integration;


import com.credibanco.cybersourcetokenizacionapi.dto.request.InstrumentIdentifierRequest;
import com.credibanco.cybersourcetokenizacionapi.dto.response.InstrumentIdentifierResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public interface TmsInstrumentIdentifiersService {

InstrumentIdentifierResponse
createInstrumentIdentifierCardAndEnrrollForNetworkToken (InstrumentIdentifierRequest request) throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException;

InstrumentIdentifierResponse
getAnInstrumentIdentifier (String tokenId) throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException;

void deleteInstrumentIdentifier(String tokenId) throws URISyntaxException, UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException;



}
