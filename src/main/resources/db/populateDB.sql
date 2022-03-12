DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT  INTO meals (description, calories, date_time, user_id)
VALUES ('завтрак', 600, '2022-03-03 20:20:10', 100000),
       ('обед', 1000, '2022-03-03 20:20:15' , 100000),
       ('завтрак', 300, '2022-03-03 20:20:10' , 100001),
       ('завтрак', 4000, '2022-03-03 20:20:13' , 100001);
