--liquibase formatted sql

--changeset Kondratyev Sergey:1 context:it
INSERT INTO marker_t(marker_title)
VALUES ('News');
INSERT INTO marker_t(marker_title)
VALUES ('Note');

--changeset Kondratyev Sergey:2 context:it
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My First Article',TRUE,'<p>Article content</p>','21.10.15');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (1,1);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','22.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (2,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (3,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (4,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (5,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (6,2);
INSERT INTO article_t( article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (7,2);
INSERT INTO article_t( article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (8,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (9,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (10,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (11,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (12,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (13,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (14,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (15,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (16,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (17,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (18,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (19,2);
INSERT INTO article_t(article_title, article_published, article_content, article_published_date)
VALUES ('My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (20,2);

--changeset Kondratyev Sergey:3 context:it
INSERT INTO user_t(user_username,user_password,user_enabled)
VALUES ('admin@localhost','$2a$10$eQV9Qknz5Gk6t8hJZQtTTOXg0XEid75uPZoX9Ga08dYF6prTODW6K',TRUE);
INSERT INTO authority_t(user_id, authority_title)
VALUES ((SELECT user_id FROM user_t WHERE user_username = 'admin@localhost'),'ROLE_ADMIN');