INSERT INTO artist (NAME, DESCRIPTION)
VALUES ('U2', 'Irish Rock Band');

INSERT INTO album (NAME, DESCRIPTION, ARTIST_ID)
VALUES ('The Joshua Tree', 'Ground Breaking Album', SELECT id FROM artist WHERE name = 'U2');

INSERT INTO track (NAME, ALBUM_ID)
VALUES ('Where The Streets Have No Name',  SELECT id FROM album WHERE name = 'The Joshua Tree');

INSERT INTO track (NAME, ALBUM_ID)
VALUES ('With or Without You',  SELECT id FROM album WHERE name = 'The Joshua Tree');

INSERT INTO track (NAME, ALBUM_ID)
VALUES ('One Tree Hill',  SELECT id FROM album WHERE name = 'The Joshua Tree');

INSERT INTO album (NAME, DESCRIPTION, ARTIST_ID)
VALUES ('Achtung Baby', '90s Album', SELECT id FROM artist WHERE name = 'U2');

INSERT INTO track (NAME, ALBUM_ID)
VALUES ('Even Better Than The Real Thing',  SELECT id FROM album WHERE name = 'Achtung Baby');

INSERT INTO track (NAME, ALBUM_ID)
VALUES ('The Fly',  SELECT id FROM album WHERE name = 'Achtung Baby');

INSERT INTO track (NAME, ALBUM_ID)
VALUES ('One',  SELECT id FROM album WHERE name = 'Achtung Baby');
