drop table if exists users CASCADE;
drop table if exists customers CASCADE;
drop table if exists administrators CASCADE;
drop table if exists transactions CASCADE;
drop table if exists accounts CASCADE;



--customers table
create  table if not exists customers(
	userId numeric(8) primary key,
	firstName VARCHAR(50) not null,
	middleInitial VARCHAR(2),
	lastName VARCHAR(50) not null,
	phoneNumber VARCHAR(18),
	address VARCHAR(200),
	city VARCHAR(50),
	state varchar(2),
	zipCode numeric (8),
	username varchar(20) not null, -- default username
	passwd varchar(20) not null, -- default password
	superUser bool not null default false,
	accountNumber int unique
);

-- adminstarators table
--create  table if not exists administrators(
--	adminId numeric(8) primary key,
--	firstName VARCHAR(50) not null,
--	middleInitial VARCHAR(2),
--	lastName VARCHAR(50) not null,
--	phoneNumber VARCHAR(18),
--	address VARCHAR(200),
--	city VARCHAR(50),
--	state varchar(2),
--	zipCode numeric (8),	
--	username varchar(20) not null, -- default username
--	passwd varchar(20) not null, -- default password
--	accountNumber int unique
--);

create table if not exists accounts(
	-- maybe account name
	accountId numeric(12) primary key,
	runningBalance decimal(20,2),
	accountNumber int references customers(accountNumber)
);

create table if not exists transactions(
	accountId numeric(12) references accounts(accountId),
	transactionId varchar(10) primary key,
	transactionDate timestamp without time zone not null default (current_timestamp at time zone 'CST'),
	transactionType varchar(20) not null,
	amount decimal(20,2) not null
);



select * from accounts a ;

-- truncate customers cascade;

