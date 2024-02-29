CREATE TABLE users
(
    id varchar(255) not null,
    version int4 not null,
    created_at timestamp not null,
    modified_at timestamp,
    login varchar not null,
    password varchar(255) not null,
    full_name varchar(255),
    phone_numbers text[],
    emails text[],
    birth_date timestamp,
    primary key (id)
);

CREATE TABLE accounts
(
    user_id varchar(255) not null,
    start_deposit double precision not null,
    current_balance double precision,
    foreign key (user_id) references users (id) on delete cascade
)