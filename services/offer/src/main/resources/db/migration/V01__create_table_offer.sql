CREATE TABLE t_offer (
	id bigint NOT NULL AUTO_INCREMENT,
	value decimal(9, 2) NOT NULL,
	status varchar(50) NOT NULL,
	created_at timestamp NOT NULL,
	CONSTRAINT t_offer_pk PRIMARY KEY (id)
);