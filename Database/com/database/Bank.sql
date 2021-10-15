drop table if exists users CASCADE;
drop table if exists customers CASCADE;
drop table if exists administrators CASCADE;
drop table if exists transactions CASCADE;
drop table if exists accounts CASCADE;

-- users table
create  table if not exists users(
	firstName VARCHAR(50) not null,
	middleInitial VARCHAR(2),
	lastName VARCHAR(50) not null,
	phoneNumber VARCHAR(10),
	address VARCHAR(200),
	userId numeric
);

--customers table
create  table if not exists customers(
	firstName VARCHAR(50) not null,
	middleInitial VARCHAR(2),
	lastName VARCHAR(50) not null,
	phoneNumber VARCHAR(10),
	address VARCHAR(200),
	userId numeric
);

-- adminstarators table
create  table if not exists administrators(
	firstName VARCHAR(50) not null,
	middleInitial VARCHAR(2),
	lastName VARCHAR(50) not null,
	phoneNumber VARCHAR(10),
	address VARCHAR(200),
	userId numeric
);

create table if not exists accounts(
	userId numeric,
	runningBalance decimal(20,2),
	accountNumber int	
);
