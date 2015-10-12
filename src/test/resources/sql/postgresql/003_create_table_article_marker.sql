CREATE TABLE article_marker_t (
  article_marker_id SERIAL,
  article_id INT,
  marker_id INT,
  CONSTRAINT article_marker_pk PRIMARY KEY (article_marker_id),
  CONSTRAINT article_marker_article_fk FOREIGN KEY (article_id) REFERENCES article_t(article_id),
  CONSTRAINT article_marker_marker_fk FOREIGN KEY (marker_id) REFERENCES marker_t(marker_id)
);
