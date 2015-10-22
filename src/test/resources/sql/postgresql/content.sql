--liquibase formatted sql

--changeset Kondratyev Sergey:1 context:it
UPDATE article_t SET article_content = '<p>Article content</p>' WHERE article_id = 1;

--changeset Kondratyev Sergey:2 context:it
UPDATE article_t SET article_published_date = '21.10.15' WHERE article_id = 1;