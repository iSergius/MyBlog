--liquibase formatted sql

--changeset Kondratyev Sergey:1 context:it
UPDATE article_t SET article_content = '<p>Article content</p>' WHERE article_id = 1;

--changeset Kondratyev Sergey:2 context:it
UPDATE article_t SET article_published_date = '21.10.15' WHERE article_id = 1;

--changeset Kondratyev Sergey:3 context:it
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (2,'My Note',TRUE,'Test Note','22.10.2015');
INSERT INTO marker_t(marker_id, marker_title)
VALUES (2,'Note');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (2,2,2);

--changeset Kondratyev Sergey:4 context:it
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (3,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (3,3,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (4,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (4,4,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (5,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (5,5,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (6,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (6,6,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (7,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (7,7,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (8,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (8,8,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (9,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (9,9,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (10,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (10,10,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (11,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (11,11,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (12,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (12,12,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (13,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (13,13,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (14,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (14,14,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (15,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (15,15,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (16,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (16,16,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (17,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (17,17,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (18,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (18,18,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (19,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (19,19,2);
INSERT INTO article_t(article_id, article_title, article_published, article_content, article_published_date)
VALUES (20,'My Note',TRUE,'Test Note','26.10.2015');
INSERT INTO article_marker_t(article_marker_id,article_id, marker_id)
VALUES (20,20,2);
