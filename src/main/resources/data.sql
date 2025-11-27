DELETE FROM star_wars_films;
DELETE FROM film_writers;

INSERT INTO film_writers (writer_first_name, writer_last_name) VALUES
('Джордж', 'Лукас'),
('Пітер', 'Джексон'),
('Френсіс', 'Коппола'),
('Саша', 'Барон Коен');

INSERT INTO star_wars_films (film_title, film_release_year, film_writer_id) VALUES
('Зоряні війни: Нова надія', 1977, 1),
('Зоряні війни: Імперія завдає удару у відповідь', 1980, 1),
('Зоряні війни: Повернення джедая', 1983, 1);

INSERT INTO star_wars_films (film_title, film_release_year, film_writer_id) VALUES
('Володар перснів: Хранителі персня', 2001, 2),
('Володар перснів: Дві вежі', 2002, 2),
('Володар перснів: Повернення короля', 2003, 2);

INSERT INTO star_wars_films (film_title, film_release_year, film_writer_id) VALUES
('Хрещений батько', 1972, 3),
('Хрещений батько 2', 1974, 3),
('Апокаліпсис сьогодні', 1979, 3);

-- Додав ще трішки улюбленного Саші Барона Коєна
INSERT INTO star_wars_films (film_title, film_release_year, film_writer_id) VALUES
('Борат', 2006, 4),
('Бруно', 2009, 4),
('Диктатор', 2012, 4);