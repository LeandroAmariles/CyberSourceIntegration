package com.credibanco.cybersourcetokenizacionapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties
public class RestTemplateConfiguration {

  @Value("${restTemplate.connection.timeOut}")
  private String connectionTimeOut;
  @Value("${restTemplate.read.timeOut}")
  private String readTimeOut;

  @Bean
  public RestTemplate restTemplate(){
    var factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout(Integer.parseInt(connectionTimeOut));
    factory.setReadTimeout(Integer.parseInt(readTimeOut));
    return new RestTemplate(factory);
  }
}
