
-- creating dummy account 
insert into account(id,player_name,date_created,version) values (100,'Nils',now(),0);
insert into account(id,player_name,date_created,version) values (200,'Frida',now(),0);
insert into account(id,player_name,date_created,version) values (300,'Jonas',now(),0);

-- performing wallet update for account with id 100
insert into wallet(id,account_id,amount,description,transaction_date,transaction_reference,version) values (600,100,3000,'opening balance',now(),1001,0);
insert into wallet(id,account_id,amount,description,transaction_date,transaction_reference,version) values (601,100,-800,'debit',now(),1002,0);
insert into wallet(id,account_id,amount,description,transaction_date,transaction_reference,version) values (602,100,2500,'credit',now(),1003,0);


