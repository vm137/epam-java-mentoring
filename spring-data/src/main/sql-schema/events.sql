create table events
(
	id bigint not null
		constraint events_pkey
			primary key,
	date timestamp,
	ticket_price integer,
	title varchar(255)
);

