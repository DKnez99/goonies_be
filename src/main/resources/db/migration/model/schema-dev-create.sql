CREATE TABLE IF NOT EXISTS `role` (
    id                                  TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name                                VARCHAR(32) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS `user` (
    id                                  BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name                                VARCHAR(64) NOT NULL,
    username                            VARCHAR(32) UNIQUE NOT NULL,
    email                               VARCHAR(64) UNIQUE NOT NULL,
    profile_image_reference             VARCHAR(128),
    password_hash                       VARCHAR(64) NOT NULL,
    status                              ENUM('ACTIVE','DEACTIVATED','PENDING') NOT NULL DEFAULT 'PENDING',
    created_at                          DATETIME NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS `user_role` (
    user_id                             BIGINT UNSIGNED NOT NULL REFERENCES `user`(id),
    role_id                             TINYINT UNSIGNED NOT NULL REFERENCES `role`(id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS `country` (
    id                                  TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name                                VARCHAR(32) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS `movie` (
    id                                  BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    title                               VARCHAR(64) NOT NULL,
    original_title                      VARCHAR(64),
    description                         TEXT,
    cover_image_reference               VARCHAR(128),
    release_date                        DATE,
    origin_country_id                   TINYINT UNSIGNED NOT NULL REFERENCES `country`(id),
    imdb_link                           VARCHAR(128),
    imdb_rating                         TINYINT UNSIGNED,
    rotten_tomatoes_link                VARCHAR(128),
    rotten_tomatoes_rating              TINYINT UNSIGNED
);

CREATE INDEX movie_origin_country_id_idx ON `movie`(origin_country_id);

CREATE TABLE IF NOT EXISTS `genre` (
    id                                  TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name                                VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS `movie_genre` (
    movie_id                            BIGINT UNSIGNED NOT NULL REFERENCES `movie`(id),
    genre_id                            TINYINT UNSIGNED NOT NULL REFERENCES `genre`(id),
    PRIMARY KEY (movie_id, genre_id)
);

CREATE TABLE IF NOT EXISTS `review` (
    id                                  BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    user_id                             BIGINT UNSIGNED NOT NULL REFERENCES `user`(id),
    movie_id                            BIGINT UNSIGNED NOT NULL REFERENCES `movie`(id),
    rating                              TINYINT UNSIGNED NOT NULL,
    text                                TEXT,
    already_watched                     BOOLEAN DEFAULT 0 NOT NULL,
    watch_date                          DATE,
    watch_platform                      ENUM('CINEMA','TV','PC','MOBILE') NOT NULL DEFAULT 'TV',
    status                              ENUM('ACTIVE','DEACTIVATED') NOT NULL DEFAULT 'ACTIVE',
    created_at                          DATETIME NOT NULL DEFAULT NOW()
);

CREATE INDEX review_user_id_idx ON `review`(user_id);
CREATE INDEX review_movie_id_idx ON `review`(movie_id);