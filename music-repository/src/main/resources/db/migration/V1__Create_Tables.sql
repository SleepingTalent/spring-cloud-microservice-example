CREATE TABLE artist (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    CONSTRAINT ARTIST_UQ UNIQUE(name)
);

CREATE TABLE album (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    artist_id BIGINT,
    foreign key (artist_id) references artist(id),
    CONSTRAINT ALBUM_UQ UNIQUE(name)
);

CREATE TABLE track (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    album_id BIGINT,
    foreign key (album_id) references album(id)
);

