create table account(
	id identity,
	name varchar(60) not null,
	email varchar(60) not null unique,
	password varchar(255) not null,
	role varchar(40) not null,
	activated boolean default true,
	deleted boolean default false
);