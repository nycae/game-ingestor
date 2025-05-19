package io.nycae.gameingestor.steamapi;

import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Value;
import io.micronaut.context.annotation.Factory;

@Factory
public class SteamClientFactory {
  @Value("${steamapi.key}")
  private String apiKey;

  @Bean
  public SteamWebApiClient steamWebApiClient() {
    return new SteamWebApiClient.SteamWebApiClientBuilder(apiKey).build();
  }
}
