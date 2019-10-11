CREATE TABLE user_verify
(
    id BIGINT NOT NULL,
    token CHARACTER VARYING(255),
    user_id BIGINT NOT NULL,
    create_date TIMESTAMP,
    expire_date TIMESTAMP,
    CONSTRAINT user_verify_pkey PRIMARY KEY (id),
    CONSTRAINT user_verify_fk1 FOREIGN KEY (user_id)
            REFERENCES some_user (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

CREATE SEQUENCE user_verify_seq START 1;
