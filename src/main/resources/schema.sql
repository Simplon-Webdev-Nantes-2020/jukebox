CREATE SCHEMA JUKEBOX_SCHEMA AUTHORIZATION SA;

use JUKEBOX_SCHEMA;

CREATE TABLE user (
    id   INTEGER   PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255),
    email VARCHAR(50),
    active BOOLEAN DEFAULT true,
    created_date TIMESTAMP
);

CREATE TABLE authority (
    id   INTEGER   PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_authority (
    user_id      INTEGER ,
    authority_id INTEGER,
    PRIMARY KEY (user_id, authority_id),
    foreign key (user_id) references user(id),
    foreign key (authority_id) references authority(id)
);

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
