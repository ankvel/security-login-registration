CREATE TABLE user_verification
(
    id BIGINT NOT NULL,
    token CHARACTER VARYING(255),
    type CHARACTER VARYING(20),
    user_id BIGINT NOT NULL,
    create_date TIMESTAMP,
    expire_date TIMESTAMP,
    CONSTRAINT user_verification_pkey PRIMARY KEY (id),
    CONSTRAINT user_verification_fk1 FOREIGN KEY (user_id)
            REFERENCES some_user (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

CREATE SEQUENCE user_verification_seq
    START 1
    INCREMENT BY 25;
