package io.nycae.gameingestor.games;

import io.micronaut.core.type.Argument;
import io.micronaut.serde.Deserializer;
import io.micronaut.serde.Decoder;
import io.micronaut.serde.Encoder;
import io.micronaut.serde.Serde;
import io.micronaut.serde.exceptions.SerdeException;
import io.nycae.gameingestor.proto.Games.Game;
import jakarta.inject.Singleton;

import java.io.IOException;

@Singleton
public class GameSerdes implements Serde<Game> {
  @Override
  public Game deserialize(Decoder decoder, Deserializer.DecoderContext context, Argument<? super Game> type)
    throws IOException {
    // Decode from JSON or binary (Kafka uses binary via Confluent serializer)
    byte[] bytes = decoder.decodeBinary();
    try {
      return Game.parseFrom(bytes);
    } catch (Exception e) {
      throw new SerdeException("Failed to deserialize Game: " + e.getMessage(), e);
    }
  }

  @Override
  public void serialize(Encoder encoder, EncoderContext context, Argument<? extends Game> type, Game value)
    throws IOException {
    encoder.encodeBinary(value.toByteArray());
  }

  @Override
  public boolean isEmpty(EncoderContext context, Game value) {
    return value == null;
  }
}