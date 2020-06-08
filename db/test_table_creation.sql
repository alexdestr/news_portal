CREATE ROLE testuser;
CREATE SCHEMA IF NOT EXISTS tests;
grant usage on schema public to postgres;
grant create on schema public to postgres;
revoke ALL on SCHEMA public FROM public;
grant usage on schema tests to testuser;
grant SELECT, INSERT, UPDATE, DELETE on ALL tables in schema tests TO testuser;
grant SELECT, USAGE on ALL sequences in schema tests TO testuser;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT SELECT,USAGE ON SEQUENCES  TO testuser;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT SELECT,INSERT,DELETE,UPDATE ON TABLES  TO testuser;

CREATE TABLE tests.roles
(
    role_id INTEGER NOT NULL PRIMARY KEY,
    role_name TEXT NOT NULL
);

CREATE TABLE tests.users
(
    user_id BIGSERIAL NOT NULL PRIMARY KEY,
    login VARCHAR UNIQUE NOT NULL,
    hash_password VARCHAR  NOT NULL,
    user_name VARCHAR  NOT NULL,
    user_last_name VARCHAR  NOT NULL,
    registration_date TIMESTAMP NOT NULL,
    role_id INTEGER NOT NULL REFERENCES tests."roles"(role_id),
    enabled BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE tests.news
(
    news_id BIGSERIAL NOT NULL PRIMARY KEY,
    author_id BIGINT NOT NULL REFERENCES tests."users"(user_id),
    author_name TEXT NOT NULL,
    title VARCHAR  NOT NULL,
    news_text TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL
);

CREATE TABLE tests.comments
(
    comments_id BIGSERIAL NOT NULL PRIMARY KEY,
    news_id BIGINT NOT NULL REFERENCES tests."news"(news_id),
    author_id BIGINT NOT NULL REFERENCES tests."users"(user_id),
    author_name TEXT NOt NUll,
    comment_text TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL
);

CREATE TABLE tests.tags
(
    news_id BIGINT NOT NULL REFERENCES tests."news"(news_id),
    tag_name VARCHAR  NOT NULL
);

