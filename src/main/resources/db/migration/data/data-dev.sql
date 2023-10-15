SET CHARACTER SET utf8;

INSERT INTO `role` (name) VALUES ('ADMIN'), ('USER');

INSERT INTO `user` (name, username, email, profile_image_reference, password_hash, status, created_at) VALUES
('Dimitrije Knežević', 'Knez', 'admin1@goonies.com', null, '$2a$10$MlbgTybjKGRelxlyu5Jvze9YGKTiM9ytvFw0b/qE/.ARZ/HFeqNjO', 'ACTIVE', '2023-10-14 9:42:11.350941'),
('Nathan McKingsly', 'McKing', 'admin2@goonies.com', null, '$2a$10$MlbgTybjKGRelxlyu5Jvze9YGKTiM9ytvFw0b/qE/.ARZ/HFeqNjO', 'ACTIVE', '2023-10-14 9:42:11.350941'),
('John Doe', 'John', 'john.doe@yahoo.com', null, '$2a$10$sTgcUmNaBup6RPNJzNz.VehSddiKgUbfrgvOr/tunLRFP0NfYnEoG', 'ACTIVE', '2023-10-15 22:11:31.059916'),
('Narendra Damodardas Modi', 'NDM', 'narendra.modi@google.com', null, '$2a$10$sTgcUmNaBup6RPNJzNz.VehSddiKgUbfrgvOr/tunLRFP0NfYnEoG', 'DEACTIVATED', '2023-10-15 23:04:55.123843'),
('Carl Johannesburg', 'Carl Johan', 'carl123@google.com', null, '$2a$10$sTgcUmNaBup6RPNJzNz.VehSddiKgUbfrgvOr/tunLRFP0NfYnEoG', 'ACTIVE', '2023-10-16 12:34:16.849082'),
('Volodomyr Volodomirsky', 'vlad14', 'vlad14@yandex.com', null, '$2a$10$sTgcUmNaBup6RPNJzNz.VehSddiKgUbfrgvOr/tunLRFP0NfYnEoG', 'PENDING', '2023-10-17 16:50:58.700933');

INSERT INTO `user_role` (user_id, role_id) VALUES
(1, 1), (2, 1), -- admins
(3, 2), (4, 2), (5, 2), (6, 2); -- users

INSERT INTO `country` (name) VALUES
('USA'), ('France'), ('Serbia');

INSERT INTO `movie` (title, original_title, description, cover_image_reference, release_date, origin_country_id, imdb_link, imdb_rating, rotten_tomatoes_link, rotten_tomatoes_rating, status) VALUES
('Oppenheimer', null, 'The story of American scientist, J. Robert Oppenheimer, and his role in the development of the atomic bomb.', null, '2023-07-21', 1, 'tt15398776', 85, 'oppenheimer_2023', 93, 'RELEASED'),
('Barbie', null, 'Barbie suffers a crisis that leads her to question her world and her existence.', null, '2023-07-21', 1, 'tt1517268/', 71, 'barbie', 88, 'RELEASED'),
('Sugar and stars', 'À la belle étoile', 'Since he was a kid, Yazid has one big passion, pastry making. Raised between foster homes and group homes, the young man will try to make his dream come true: to work with the greatest pastry chefs and become the best.', null, '2023-02-22', 2, 'tt19815386', 66, 'sugar_and_stars', 90, 'RELEASED'),
('Guardians of the Formula', 'Čuvari formule', 'In 1958, during the Cold War - two scientists, two worlds, and two ideologies faced in a race for survival at the Vinca Scientific Institute near Belgrade.', null, '2023-10-26', 3, 'tt20365920', 84, null, null, 'ANNOUNCED');

INSERT INTO `genre` (name) VALUES
('Action'), -- 1
('Adventure'), -- 2
('Biography'), -- 3
('Comedy'), -- 4
('Drama'), -- 5
('Fantasy'), -- 6
('History'), -- 7
('Horror'), -- 8
('Sports'), -- 9
('Sci-Fi'), -- 10
('Thriller'); -- 11

INSERT INTO `movie_genre` VALUES
(1, 3), (1, 5), (1, 7),
(2, 2), (2, 4), (2, 6),
(3, 5),
(4, 5), (4, 7), (4, 11);

INSERT INTO `review` (user_id, movie_id, rating, text, already_watched, watch_date, watch_platform, status, created_at) VALUES
(3, 1, 89, 'Great movie, although a bit lengthy for my liking. Superb feeling watching it in cinema.', 0, '2023-07-29', 'CINEMA', 'ACTIVE', '2023-10-16 11:34:20.049719'),
(3, 2, 66, 'It was alright I guess. Watched it with my girlfriend just after Oppenheimer so we were probably just exhausted. Will give it a rewatch.', 0, '2023-07-29', 'CINEMA', 'ACTIVE', '2023-10-16 11:39:53.984712'),
(4, 1, 92, 'Another brilliant movie by Nolan. Props to lead actor for carrying out such a dramatic role. Iconic character.', 1, '2023-10-15', 'TV', 'DEACTIVATED', '2023-10-15 23:41:48.049891'),
(5, 3, 32, 'I honestly don\'t remember the last time I\'ve watched such a horrendously boring movie. Big thumbs down.', 0, '2023-10-11', 'PC', 'ACTIVE', '2023-10-17 08:11:45.857495');