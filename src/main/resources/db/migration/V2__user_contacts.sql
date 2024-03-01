ALTER TABLE users
    RENAME COLUMN login TO username;

ALTER TABLE users
    DROP COLUMN phone_numbers;

ALTER TABLE users
    DROP COLUMN emails;

CREATE UNIQUE INDEX username_unique_index ON users (username);

CREATE UNIQUE INDEX user_id_unique_index ON accounts (user_id);

CREATE TABLE phone_numbers
(
    user_id      varchar(255) not null,
    phone_number varchar(255) not null,
    foreign key (user_id) references users (id) on delete cascade
);

CREATE UNIQUE INDEX phone_number_unique_index ON phone_numbers (phone_number);

CREATE TABLE emails
(
    user_id varchar(255) not null,
    email   varchar(255) not null,
    foreign key (user_id) references users (id) on delete cascade
);

CREATE UNIQUE INDEX email_unique_index ON emails (email);