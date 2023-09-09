set foreign_key_checks = 0;

truncate table account;
truncate table ledger;
truncate table transaction;
truncate table transaction_item;

set foreign_key_checks = 1;