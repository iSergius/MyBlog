--liquibase formatted sql

--changeset Kondratyev Sergey:1 context:it
INSERT INTO marker_t(marker_title)
VALUES ('News');
INSERT INTO marker_t(marker_title)
VALUES ('Note');

--changeset Kondratyev Sergey:2 context:it
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My First Article',TRUE,'<p>Article content</p>','21.10.15','<p>Article content</p>');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (1,(SELECT marker_id FROM marker_t WHERE marker_title = 'News'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note',TRUE,'Test Note','22.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (2,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note1',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (3,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note2',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (4,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note3',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (5,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note4',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (6,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t( article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note5',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (7,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t( article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note6',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (8,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note7',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (9,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note8',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (10,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note9',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (11,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note10',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (12,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note11',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (13,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note12',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (14,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note13',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (15,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note14',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (16,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note15',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (17,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note16',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (18,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note17',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (19,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));
INSERT INTO article_t(article_title, article_published, article_content, article_published_date, article_disclaimer)
VALUES ('My Note18',TRUE,'Test Note','26.10.2015','Test Note');
INSERT INTO article_marker_t(article_id, marker_id)
VALUES (20,(SELECT marker_id FROM marker_t WHERE marker_title = 'Note'));

--changeset Kondratyev Sergey:3 context:it
INSERT INTO user_t(user_username,user_password,user_enabled,user_fullname,user_about,user_email)
VALUES ('admin@localhost','$2a$10$eQV9Qknz5Gk6t8hJZQtTTOXg0XEid75uPZoX9Ga08dYF6prTODW6K',TRUE,'Ivanov Petr Vasilevich','Writer','admin@localhost');
INSERT INTO authority_t(user_id, authority_title)
VALUES ((SELECT user_id FROM user_t WHERE user_username = 'admin@localhost'),'ROLE_ADMIN');

--changeset Kondratyev Sergey:4 context:it
INSERT INTO file_t(file_name, file_mime)
VALUES ('picture.jpg','image/jpg');

--changeset Kondratyev Sergey:5 context:it
INSERT INTO setting_t(setting_name, setting_title, setting_value, setting_type)
VALUES ('name.isergius.learn.myblog.ui.IndexController.pageLength.Long',
        'Count Articles on Index Page',
        '10',
        'Long');
INSERT INTO setting_t(setting_name, setting_title, setting_type)
VALUES ('name.isergius.learn.myblog.ui.NoteController.articlesPageLength.Long',
        'Number Articles Strings on Note Page',
        'Long');
INSERT INTO setting_t(setting_name, setting_title, setting_type)
VALUES ('name.isergius.learn.myblog.ui.NoteController.markersPageLength.Long',
        'Number Markers Strings on Note Page',
        'Long');
INSERT INTO setting_t(setting_name, setting_title, setting_type)
VALUES ('name.isergius.learn.myblog.ui.FileController.fileMetadataPageLength.Long',
        'Number File Strings on Files Page',
        'Long');
INSERT INTO setting_t(setting_name, setting_title, setting_type)
VALUES ('name.isergius.learn.myblog.domain.BlogService.blogTitle.String',
        'Title of the Blog',
        'String');
INSERT INTO setting_t(setting_name, setting_title, setting_type)
VALUES ('name.isergius.learn.myblog.ui.ArticleController.markersCloudPageLength.Long',
        'Count markers in cloud',
        'Long');