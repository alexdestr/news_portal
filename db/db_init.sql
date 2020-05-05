INSERT INTO roles (role_id, role_name)
VALUES (1, 'ROLE_USER');
INSERT INTO roles (role_id, role_name)
VALUES (2, 'ROLE_MOD');
INSERT INTO roles (role_id, role_name)
VALUES (3, 'ROLE_ADMIN');
INSERT INTO roles (role_id, role_name)
VALUES (4, 'ROLE_SUPER_ADMIN');
INSERT INTO roles (role_id, role_name)
VALUES (0, 'ROLE_BANNED');


INSERT INTO users (login, hash_password, user_name, user_last_name, registration_date, role_id)
VALUES ('Test1', 'smthhashpass1', 'ab', 'abc', '2019-11-11 00:00:09.959', '1');
INSERT INTO users (login, hash_password, user_name, user_last_name, registration_date, role_id)
VALUES ('Test2', 'smthhashpass2', 'dca', 'qvbasdasd', '2019-11-11 00:00:09.959', '1');

INSERT INTO news (author_id, title, news_text, creation_date)
VALUES (1, 'news_title1', 'smthtext1', '2019-11-11 00:00:09.959');
INSERT INTO news (author_id, title, news_text, creation_date)
VALUES (1, 'news_title2', 'smthtext2', '2019-11-11 00:00:09.959');
INSERT INTO news (author_id, title, news_text, creation_date)
VALUES (2, 'news_title1', 'smthtext1', '2019-11-11 00:00:09.959');

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
VALUES (2, 5, 'Cool!', '2019-11-11 00:00:09.959');
INSERT INTO comments (news_id, author_id, comment_text, creation_date)
VALUES (4, 1, 'Hah', '2019-11-11 00:00:09.959');
INSERT INTO comments (news_id, author_id, comment_text, creation_date)
VALUES (4, 1, 'qwerty', '2019-11-11 00:00:09.959');