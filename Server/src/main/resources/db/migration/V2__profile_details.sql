CREATE TABLE user_profile
(
    user_id     VARCHAR(12) PRIMARY KEY REFERENCES users,
    name        varchar(50),
    description varchar(255),
    posts       INT NOT NULL,
    followers   INT NOT NULL,
    following   INT NOT NULL
);

INSERT INTO user_profile(user_id, name, description, posts, followers, following)
SELECT id,
       null,
       null,
       0,
       0,
       0
FROM USERS
