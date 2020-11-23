CREATE SCHEMA JUKEBOX_SCHEMA AUTHORIZATION SA;

use JUKEBOX_SCHEMA;

CREATE TABLE artist (
    id   INTEGER   PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
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
    foreign key (album_id) references album(id)
);

CREATE TABLE play_list (
    id   INTEGER   PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);
