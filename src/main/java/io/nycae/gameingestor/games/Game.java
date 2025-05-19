package io.nycae.gameingestor.games;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Game {
  @Id
  @GeneratedValue
  private long id;
  private long steamAppId;
  private String title;

  public long getId() {
    return id;
  }

  public long getSteamAppId() {
    return steamAppId;
  }

  public String getTitle() {
    return title;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setSteamAppId(long steamAppId) {
    this.steamAppId = steamAppId;
  }
}
