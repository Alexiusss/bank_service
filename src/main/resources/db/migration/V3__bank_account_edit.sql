ALTER TABLE accounts
    ADD CONSTRAINT positive_account_balance CHECK ( accounts.current_balance >= 0 ),
    ADD COLUMN version int4 not null default 0;