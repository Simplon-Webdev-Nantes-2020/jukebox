use JUKEBOX_SCHEMA;

/* users / authorities */
INSERT INTO `user` (`id`, `username`, `password`, `email`, `created_date`) VALUES (1,'admin','$2a$11$MXOOO1JYngri2arcL6Cic.KuBujhqgz.B2ri6szqN2/cfsdiQa7se','admin@jukebox.com','2021-03-08 17:13:54');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `created_date`) VALUES (2,'manu','$2a$11$MXOOO1JYngri2arcL6Cic.KuBujhqgz.B2ri6szqN2/cfsdiQa7se','manu@jukebox.com','2021-03-08 17:13:54');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `created_date`) VALUES (3,'toto','$2a$11$MXOOO1JYngri2arcL6Cic.KuBujhqgz.B2ri6szqN2/cfsdiQa7se','toto@jukebox.com','2021-03-08 17:13:54');


INSERT INTO `authority`(`id`, `name`) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `authority`(`id`, `name`) VALUES (2, 'ROLE_MANAGER');
INSERT INTO `authority`(`id`, `name`) VALUES (3, 'ROLE_USER');

INSERT INTO `user_authority`(`user_id`, `authority_id`) VALUES (1, 1);
INSERT INTO `user_authority`(`user_id`, `authority_id`) VALUES (1, 2);
INSERT INTO `user_authority`(`user_id`, `authority_id`) VALUES (1, 3);
INSERT INTO `user_authority`(`user_id`, `authority_id`) VALUES (2, 2);
INSERT INTO `user_authority`(`user_id`, `authority_id`) VALUES (2, 3);
INSERT INTO `user_authority`(`user_id`, `authority_id`) VALUES (3, 3);


/* artistes */
insert into artist(id, name, bio, fan_number) 
	values(1, 'Celtic woman','En 2004, les producteurs Sharon Browne et David Downes, directeur musical...',31760);
insert into artist(id, name, bio, fan_number) 
	values(2, 'M. Pokora','Né le 26 septembre 1985 à Strasbourg d''un père footballeur et d''une mère fonctionnaire',1310570);
insert into artist(id, name, bio, fan_number) 
	values(3, 'Kendji Girac','Kendji Maillé est né à Périgueux, en Dordogne, le 3 juillet 1996.',1014955);
insert into artist(id, name, bio, fan_number) 
	values(4, 'Julien Doré','Julien Doré est né le 7 juillet 1982 à Alès dans le Gard. Après des études ',708365);
insert into artist(id, name, bio, fan_number) 
	values(5, 'Patrick Fiori','Patrick Fiori, né le 23 septembre 1969 sous le nom de Jean-François Chouchayan',216351);
insert into artist(id, name, bio, fan_number) 
	values(6, 'Trois Cafés Gourmands','',167304);
insert into artist(id, name, bio, fan_number) 
	values (7, 'Nolwenn Leroy','C''est le 28 septembre 1982 que voit le jour, à Saint-Renan (Finistère) que voile jour Nolwenn Le Magueresse',230900);
	
/* album Kendji */
insert into album(id, artist_id, title, release_date) 
	values (1, 3, 'Kendji', '2015-05-04');

insert into track(id, album_id, title, duration, preview)
	values (1, 1, 'Color Gitano',211,'https://cdns-preview-f.dzcdn.net/stream/c-f981754c689d1819ed89bacbf4d53f75-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (2, 1, 'Andalouse', 167, 'https://cdns-preview-d.dzcdn.net/stream/c-d691f59e5193e4b72074698028eb7c0d-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (3, 1, 'Mon univers', 202, 'https://cdns-preview-f.dzcdn.net/stream/c-fb6c4843fb44d64b3a34028f8b784336-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (4, 1, 'Viens chez nous', 209, 'https://cdns-preview-8.dzcdn.net/stream/c-8455bc3d727c0084b0b4e4be4a3022ee-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (5, 1, 'Cool', 197, 'https://cdns-preview-3.dzcdn.net/stream/c-31a535d15c084f905f2c78379f35511d-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (6, 1, 'Mi Amor', 230, 'https://cdns-preview-f.dzcdn.net/stream/c-fadbb472c6dc15e6b7d135abc06fd2ce-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (7, 1, 'Avec toi', 207, 'https://cdns-preview-9.dzcdn.net/stream/c-92bd8dcfbdcef431b640799394610ad1-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (8, 1, 'Baïla Amigo', 196, 'https://cdns-preview-e.dzcdn.net/stream/c-ec1eb5c687359c2f6a007cfd6617bda6-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (9, 1, 'Je m''abandonne', 195, 'https://cdns-preview-a.dzcdn.net/stream/c-aa78a06b61eaf0cb3a6be965c3d23bbf-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (10, 1, 'Gentleman', 180, 'https://cdns-preview-b.dzcdn.net/stream/c-bb51777f868c6dc27a32b63da4dc7453-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (11, 1, 'Au sommet', 214, 'https://cdns-preview-7.dzcdn.net/stream/c-773fb408806b4c9cd6d47b95db43186a-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (12, 1, 'Elle m''a aimé', 206, 'https://cdns-preview-a.dzcdn.net/stream/c-af72a374a928aa248cfc6279b57593b8-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (13, 1, 'Bella', 235, 'https://cdns-preview-0.dzcdn.net/stream/c-07d70cdc73b1746d764ab4268b74a256-4.mp3');
insert into track(id, album_id, title, duration, preview)
	values (14, 1, 'Conmigo', 183, 'https://cdns-preview-5.dzcdn.net/stream/c-5414db4747d93a17fac5759cd19aff5e-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (15, 1, 'Les richesses du coeur', 210, 'https://cdns-preview-b.dzcdn.net/stream/c-b1cb9c19369461b2ca2e01cd05cf0e89-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (16, 1, 'La bohème', 201, 'https://cdns-preview-d.dzcdn.net/stream/c-de22e1ba949c3d261bfaf9e40838c5af-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (17, 1, 'One Last Time (Attends-Moi)', 194, 'https://cdns-preview-2.dzcdn.net/stream/c-2f91f57757d08972491995d9e51861e6-3.mp3');
insert into track(id, album_id, title, duration, preview)
	values (18, 1, 'Elle m''a aimé (Radio Edit)', 208, 'https://cdns-preview-9.dzcdn.net/stream/c-946c9a8a6e4d3fa6c32002684a3e6a2d-3.mp3');
	
/* album Le Meilleur de la Musique Irlandaise */	
insert into album(id, artist_id, title, release_date) 
	values (2, 1, 'Le Meilleur de la Musique Irlandaise - Les Plus Beaux Airs Celtiques', '2014-03-03');

insert into track(id, album_id, title, duration, preview)
	values (19, 2, 'Riverdance', 168, 'https://cdns-preview-2.dzcdn.net/stream/c-25dc19d64662ad1d5a5a5a771a368859-4.mp3');
insert into track(id, album_id, title, duration, preview)
	values (20, 2, 'Give Me Your Hand', 143, 'https://cdns-preview-f.dzcdn.net/stream/c-f2d88538cdbba283fb66e9af7d4a704a-4.mp3');
insert into track(id, album_id, title, duration, preview)
	values (21, 2, 'The Snowy Breasted Pearl', 213, 'https://cdns-preview-4.dzcdn.net/stream/c-46e9aa572cffc205e4fa3aecd2424f37-4.mp3');
	
/* album Le Meilleur de la Musique Irlandaise */	
insert into album(id, artist_id, title, release_date) 
	values (3, 1, 'Ancient Land', '2018-09-28');

insert into track(id, album_id, title, duration, preview)
	values (22, 3, 'Ancient Land', 163, 'https://cdns-preview-1.dzcdn.net/stream/c-10488f595a176878b63b4dc4041959b5-5.mp3');
insert into track(id, album_id, title, duration, preview)
	values (23, 3, 'Homeland', 259, 'https://cdns-preview-1.dzcdn.net/stream/c-1cef1f3ab6ca772b8ec8b04a8858ccfa-7.mp3');
insert into track(id, album_id, title, duration, preview)
	values (24, 3, 'Moorlough Shore', 245, 'https://cdns-preview-e.dzcdn.net/stream/c-eb8433459094c30847866d7042ec91e6-6.mp3');
	
/* playlist */
insert into playlist(id, name)
    values(1,'celtic');
insert into playlist(id, name)
    values(2,'gitan');
insert into playlist_track(playlist_id, track_id)
    values(1,22);
insert into playlist_track(playlist_id, track_id)
    values(1,24);
insert into playlist_track(playlist_id, track_id)
    values(1,20);
insert into playlist_track(playlist_id, track_id)
    values(2,4);
insert into playlist_track(playlist_id, track_id)
    values(2,6);    