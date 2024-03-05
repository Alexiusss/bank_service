ALTER TABLE accounts
    ADD COLUMN version int not null default 0;

ALTER TABLE accounts
    ADD CONSTRAINT positive_account_balance CHECK ( accounts.current_balance >= 0 );