INSERT INTO users (id, version, created_at, modified_at, username, password, full_name, birth_date)
VALUES ('user1ID', 0, now(), null, 'username1', 'password1', 'fullName1', null),
       ('user2ID', 0, now(), null, 'username2', 'password2', 'fullName2', null);

INSERT INTO accounts (user_id, start_deposit, current_balance)
VALUES ('user1ID', 100, 100),
       ('user2ID', 100, 100);

INSERT INTO emails (user_id, email)
VALUES ('user1ID', 'user1@gmail.com'),
       ('user2ID', 'user2@gmail.com');

INSERT INTO phone_numbers (user_id, phone_number)
VALUES ('user1ID', '+7 (111) 111-11-11'),
       ('user2ID', '+7 (222) 222-22-22');