package io.nycae.gameingestor.games;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {
}
