CREATE TABLE user_profile
(
    id          VARCHAR(12) PRIMARY KEY REFERENCES users,
    name        varchar(50),
    description varchar(255),
    posts       INT NOT NULL DEFAULT 0,
    followers   INT NOT NULL DEFAULT 0,
    following   INT NOT NULL DEFAULT 0
);
