CREATE TABLE users
(
    id varchar(255) not null,
    version int4 not null,
    created_at timestamp not null,
    modified_at timestamp,
    login varchar not null,
    password varchar(255) not null,
    full_name varchar(255),
    phone_numbers varchar(255),
    emails varchar(255),
    birth_date timestamp,
    primary key (id)
);

CREATE TABLE accounts
(
    user_id varchar(255) not null,
    start_deposit float not null,
    current_balance float,
    foreign key (user_id) references users (id) on delete cascade
)