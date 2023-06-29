-- Table User
CREATE TABLE IF NOT EXISTS t_user (
    id bigserial NOT NULL,
    username varchar(150) NOT NULL,
    password varchar(255) NOT NULL,
    authorities varchar(255) NOT NULL,
    CONSTRAINT t_user_id_pk PRIMARY KEY (id),
    CONSTRAINT t_user_username_unique UNIQUE (username)
);