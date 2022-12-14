CREATE TABLE t_product (
	id bigserial NOT NULL,
	name varchar(100) NOT NULL,
	description varchar(255) NOT NULL,
	price numeric(9, 2) NOT NULL,
	created_at timestamp NOT NULL,
	CONSTRAINT t_product_pk PRIMARY KEY (id)
);