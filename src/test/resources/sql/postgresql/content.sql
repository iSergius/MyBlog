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