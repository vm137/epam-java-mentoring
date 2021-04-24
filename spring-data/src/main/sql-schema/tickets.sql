create table tickets
(
	id bigint not null
		constraint tickets_pkey
			primary key,
	category integer,
	event_id bigint,
	place integer not null,
	user_id bigint
);

