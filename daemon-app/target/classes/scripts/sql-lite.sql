-- Tabla para almacenar las medidas de los sensores

CREATE TABLE measure(
	mote_ip varchar(75) NOT NULL,
	date  bigint(20) NOT NULL,
	value decimal(10,5) NOT NULL,
	retries integer NOT NULL
);

