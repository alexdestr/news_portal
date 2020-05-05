CREATE TABLE users
(
    user_id BIGSERIAL NOT NULL PRIMARY KEY,
    login VARCHAR UNIQUE NOT NULL,
    hash_password VARCHAR  NOT NULL,
    user_name VARCHAR  NOT NULL,
    user_last_name VARCHAR  NOT NULL,
    registration_date TIMESTAMP NOT NULL,
    role_id INTEGER NOT NULL REFERENCES public."roles"(role_id),
    enabled BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE roles
(
    role_id INTEGER NOT NULL PRIMARY KEY,
    role_name TEXT NOT NULL
);

CREATE TABLE news
(
    news_id BIGSERIAL NOT NULL PRIMARY KEY,
    author_id BIGINT NOT NULL REFERENCES "users"(user_id),
    author_name TEXT NOT NULL,
    title VARCHAR  NOT NULL,
    news_text TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL
);

CREATE TABLE comments
(
    comments_id BIGSERIAL NOT NULL PRIMARY KEY,
    news_id BIGINT NOT NULL REFERENCES "news"(news_id),
    author_id BIGINT NOT NULL REFERENCES "users"(user_id),
    author_name TEXT NOT NULL,
    comment_text TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL
);

CREATE TABLE tags
(
    news_id BIGINT NOT NULL REFERENCES "news"(news_id),
    tag_name VARCHAR  NOT NULL
);

