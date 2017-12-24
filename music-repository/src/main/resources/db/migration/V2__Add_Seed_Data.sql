INSERT INTO artist (NAME, DESCRIPTION)
VALUES ('U2', 'Irish Rock Band');

INSERT INTO album (NAME, DESCRIPTION, ARTIST_ID)
VALUES ('The Joshua Tree', 'Ground Breaking Album', SELECT id FROM artist WHERE name = 'U2');

INSERT INTO track (NAME, ALBUM_ID)
VALUES ('Where The Streets Have No Name',  SELECT id FROM album WHERE name = 'The Joshua Tree');
