package io.nycae.gameingestor.games;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.applist.GetAppList;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetAppListRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;
import io.micronaut.scheduling.annotation.Scheduled;
import io.nycae.gameingestor.proto.Games.Game;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class GamePolling {
  private static final Logger log = LoggerFactory.getLogger(GamePolling.class);
  private final SteamWebApiClient client;
  private final GameProducer producer;

  public GamePolling(SteamWebApiClient client, GameProducer producer) {
    this.client = client;
    this.producer = producer;
  }

  @Scheduled(fixedRate = "20s", initialDelay = "2s")
  public void run() {
    log.info("polling games");
    GetAppListRequest request = SteamWebApiRequestFactory.createGetAppListRequest();
    try {
      client.<GetAppList>processRequest(request)
        .getApplist()
        .getApps().forEach(app -> {
          producer.sendGame(Game.newBuilder()
            .setSteamAppId(app.getAppid().longValue())
            .setName(app.getName())
            .build());
        });
    } catch (SteamApiException e) {
      log.error("error polling games", e);
    }
  }
}
