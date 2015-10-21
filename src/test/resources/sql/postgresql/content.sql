--liquibase formatted sql

--changeset Kondratyev Sergey:1 context:it
UPDATE article_t SET article_content = '<p>Article content</p>' WHERE article_id = 1;

