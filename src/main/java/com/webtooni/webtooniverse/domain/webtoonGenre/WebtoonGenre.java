package com.webtooni.webtooniverse.domain.webtoonGenre;

import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class WebtoonGenre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "toon_genre_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "genre_id")
  private Genre genre;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "toon_id")
  private Webtoon webtoon;

  @Builder
  public WebtoonGenre(Genre genre, Webtoon webtoon) {
    this.genre = genre;
    this.webtoon = webtoon;
  }
}
