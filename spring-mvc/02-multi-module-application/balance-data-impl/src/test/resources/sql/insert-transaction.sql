insert into transaction (account_id, ledger_id, issue_at, remark, deleted) values (3, 1, '2023-09-01', 'Test 1', FALSE);
insert into transaction (account_id, ledger_id, issue_at, remark, deleted) values (3, 2, '2023-09-02', 'Test 2', FALSE);
insert into transaction (account_id, ledger_id, issue_at, remark, deleted) values (5, 4, '2023-09-03', 'Test 3', FALSE);
insert into transaction (account_id, ledger_id, issue_at, remark, deleted) values (5, 5, '2023-09-04', 'Test 4', FALSE);
insert into transaction (account_id, ledger_id, issue_at, remark, deleted) values (5, 5, '2023-09-05', 'Test 4', FALSE);
insert into transaction (account_id, ledger_id, issue_at, remark, deleted) values (5, 5, '2023-09-06', 'Test 4', FALSE);
insert into transaction (account_id, ledger_id, issue_at, remark, deleted) values (3, 1, '2023-09-07', 'Test 1', FALSE);

insert into transaction_item(transaction_id, item, unit_price, quantity) values (1, 'Item 1', 1000, 3);
insert into transaction_item(transaction_id, item, unit_price, quantity) values (1, 'Item 2', 1500, 4);
insert into transaction_item(transaction_id, item, unit_price, quantity) values (2, 'Item 1', 12500, 2);
insert into transaction_item(transaction_id, item, unit_price, quantity) values (3, 'Item 1', 130000, 1);
insert into transaction_item(transaction_id, item, unit_price, quantity) values (4, 'Item 1', 123049, 3);
insert into transaction_item(transaction_id, item, unit_price, quantity) values (5, 'Item 1', 133456, 5);
insert into transaction_item(transaction_id, item, unit_price, quantity) values (6, 'Item 1', 13404847, 1);
insert into transaction_item(transaction_id, item, unit_price, quantity) values (7, 'Item 1', 22345, 1);
insert into transaction_item(transaction_id, item, unit_price, quantity) values (7, 'Item 2', 304848, 2);
insert into transaction_item(transaction_id, item, unit_price, quantity) values (7, 'Item3', 103847, 4);