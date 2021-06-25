create table user_accounts
(
	id integer not null
		constraint user_accounts_pkey
			primary key
		constraint users_fk
			references users,
	amount integer
);

