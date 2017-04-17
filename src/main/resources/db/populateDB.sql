DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2017-03-25 07:00', 'Простой завтрак', 500);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2017-03-25 12:00', 'Простой обед', 1000);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2017-03-25 19:00', 'Простой ужин', 500);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2017-03-26 07:30', 'Простой завтрак', 500);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2017-03-26 12:30', 'Простой обед', 1250);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2017-03-26 19:00', 'Простой ужин', 250);


INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100001, '2017-03-25 07:30', 'Админский завтрак', 500);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100001, '2017-03-25 13:00', 'Админский обед', 1000);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100001, '2017-03-25 18:30', 'Админский ужин', 500);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100001, '2017-03-26 07:30', 'Админский завтрак', 500);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100001, '2017-03-26 13:00', 'Админский обед', 1000);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100001, '2017-03-26 18:30', 'Админский ужин', 500);


INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);
