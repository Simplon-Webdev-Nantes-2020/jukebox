CREATE SCHEMA JUKEBOX_SCHEMA AUTHORIZATION SA;

use JUKEBOX_SCHEMA;

CREATE TABLE artist (
    id   INTEGER   PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    bio VARCHAR(255),
    fan_number INTEGER
);

CREATE TABLE album (
    id   INTEGER   PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(70) NOT NULL,
    release_date DATE,
    artist_id INT,
    foreign key (artist_id) references artist(id)
);

CREATE TABLE track (
    id   INTEGER   PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    duration INTEGER,
    preview VARCHAR(200),
    album_id INT,
    foreign key (album_id) references album(id),
    constraint uc_title unique (title, album_id)
);

CREATE TABLE playlist (
    id   INTEGER   PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE playlist_track (
    playlist_id   INT,
    track_id   INT,
    primary key (playlist_id, track_id),
    foreign key (playlist_id) references playlist(id),
    foreign key (track_id) references track(id)
);
