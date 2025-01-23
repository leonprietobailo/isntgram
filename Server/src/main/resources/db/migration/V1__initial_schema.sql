CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(12)  NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    email    varchar(100) NOT NULL
)
