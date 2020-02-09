CREATE TABLE tests.users
(
    user_id BIGSERIAL NOT NULL PRIMARY KEY,
    login VARCHAR UNIQUE NOT NULL,
    hash_password VARCHAR  NOT NULL,
    user_name VARCHAR  NOT NULL,
    user_last_name VARCHAR  NOT NULL,
    registration_date TIMESTAMP NOT NULL
);

CREATE TABLE tests.news
(
    news_id BIGSERIAL NOT NULL PRIMARY KEY,
    author_id BIGINT NOT NULL REFERENCES tests."users"(user_id),
    tittle VARCHAR  NOT NULL,
    news_text TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL
);

CREATE TABLE tests.comments
(
    comments_id BIGSERIAL NOT NULL PRIMARY KEY,
    news_id BIGINT NOT NULL REFERENCES tests."news"(news_id),
    author_id BIGINT NOT NULL REFERENCES tests."users"(user_id),
    comment_text TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL
);

CREATE TABLE tests.tags
(
    news_id BIGINT NOT NULL REFERENCES tests."news"(news_id),
    tag_name VARCHAR  NOT NULL
);

