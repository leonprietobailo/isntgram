CREATE TABLE posts
(
    id          BIGINT PRIMARY KEY,
    user_id     VARCHAR(12)  NOT NULL REFERENCES users,
    posted_date TIMESTAMP    NOT NULL,
    url         varchar(255) NOT NULL UNIQUE
);
