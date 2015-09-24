CREATE TABLE daemons(
	daemon_ip varchar(75) NOT NULL,
	base_ip varchar(75) NOT NULL
);

CREATE TABLE networks(
	name varchar(75) NOT NULL,
	active boolean NOT NULL,
	base_ip varchar(75) NOT NULL,
	daemon_ip varchar(75) NOT NULL
);

CREATE TABLE motes(
	ip varchar(75) NOT NULL,
	active boolean NOT NULL,
	id varchar(75) NOT NULL,
	network_name varchar(75) NOT NULL,
	latitude double precision NOT NULL,
	longitude double precision NOT NULL
);


