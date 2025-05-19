package io.nycae.gameingestor.games;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.nycae.gameingestor.proto.Games;

@KafkaClient(id = "game-ingestor")
public interface GameProducer {

  @Topic("games")
  void sendGame(Games.Game game);
}
