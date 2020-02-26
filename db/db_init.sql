INSERT INTO users (login, hash_password, user_name, user_last_name, registration_date)
VALUES ('Test1', 'smthhashpass1', 'ab', 'abc', '2019-11-11 00:00:09.959');
INSERT INTO users (login, hash_password, user_name, user_last_name, registration_date)
VALUES ('Test2', 'smthhashpass2', 'dca', 'qvbasdasd', '2019-11-11 00:00:09.959');

INSERT INTO news (author_id, tittle, news_text, creation_date)
VALUES (1, 'news_tittle1', 'smthtext1', '2019-11-11 00:00:09.959');
INSERT INTO news (author_id, tittle, news_text, creation_date)
VALUES (1, 'news_tittle2', 'smthtext2', '2019-11-11 00:00:09.959');
INSERT INTO news (author_id, tittle, news_text, creation_date)
VALUES (2, 'news_tittle1', 'smthtext1', '2019-11-11 00:00:09.959');

INSERT INTO tags (news_id, tag_name)
VALUES (1, 'Funny');
INSERT INTO tags (news_id, tag_name)
VALUES (2, 'Story');
INSERT INTO tags (news_id, tag_name)
VALUES (2, 'my');
INSERT INTO tags (news_id, tag_name)
VALUES (2, 'Picture');
INSERT INTO tags (news_id, tag_name)
VALUES (2, 'Funny');

INSERT INTO comments (news_id, author_id, comment_text, creation_date)
VALUES (1, 4, 'nice', '2019-11-11 00:00:09.959');
INSERT INTO comments (news_id, author_id, comment_text, creation_date)
VALUES (2, 5, 'Cool story!', '2019-11-11 00:00:09.959');
INSERT INTO comments (news_id, author_id, comment_text, creation_date)
VALUES (4, 1, 'Hah', '2019-11-11 00:00:09.959');