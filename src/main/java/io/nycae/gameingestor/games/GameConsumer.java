package io.nycae.gameingestor.games;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.nycae.gameingestor.proto.Games;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class GameConsumer {
  private static final Logger log = LoggerFactory.getLogger(GameConsumer.class);
  private final GameRepo gameRepo;

  public GameConsumer(GameRepo gameRepo) {
    this.gameRepo = gameRepo;
  }

  @Topic("games")
  public void receive(Games.Game game) {
    Game gameDao = new Game();
    gameDao.setSteamAppId(game.getSteamAppId());
    gameDao.setTitle(game.getName());
    gameRepo.save(gameDao);
    log.info("received game {}", game.getName());
  }
}
