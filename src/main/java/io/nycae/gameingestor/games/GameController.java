package io.nycae.gameingestor.games;

import io.grpc.stub.StreamObserver;
import io.nycae.gameingestor.proto.GameManagerGrpc;
import io.nycae.gameingestor.proto.Games.GameCreateResponse;
import io.nycae.gameingestor.proto.Games.GameCreateRequest;
import jakarta.inject.Singleton;

@Singleton
public class GameController extends GameManagerGrpc.GameManagerImplBase {
  final GameProducer producer;

  public GameController(GameProducer producer) {
    this.producer = producer;
  }

  @Override
  public void createGame(GameCreateRequest request, StreamObserver<GameCreateResponse> responseObserver) {
    producer.sendGame(request.getGame());
    responseObserver.onNext(GameCreateResponse.newBuilder().setGame(request.getGame()).build());
    responseObserver.onCompleted();
  }
}
