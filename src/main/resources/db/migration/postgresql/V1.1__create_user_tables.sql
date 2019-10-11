CREATE TABLE some_user
(
    id BIGINT NOT NULL,
    email CHARACTER VARYING(255),
    name CHARACTER VARYING(255),
    password CHARACTER VARYING(255),
    CONSTRAINT some_user_pkey PRIMARY KEY (id)
);

CREATE TABLE some_role
(
    id BIGINT NOT NULL,
    name CHARACTER VARYING(255),
    CONSTRAINT some_role_pkey PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT users_roles_fk1 FOREIGN KEY (role_id)
        REFERENCES some_role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT users_roles_fk2 FOREIGN KEY (user_id)
        REFERENCES some_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE some_user_seq START 100;
CREATE SEQUENCE some_role_seq START 100;

INSERT INTO some_role (id, name)
VALUES
    (1, 'ROLE_SOME_ADMIN'),
    (2, 'ROLE_SOME_USER');

INSERT INTO some_user (id, name, email, password)
VALUES
    (1, 'some1', 'some1@gmail.com', '$2a$11$8Y0YDeveSkuqQ5vuxlnKdOOpbiPXkoF9OMfM5iVBzqYSh5RwHlmOq'),
    (2, 'some2', 'some2@gmail.com', '$2a$11$zkifoChstkHpkyobApwUN.3Maznw4XX63t2/gAgtdBvH1XYAb5yQu'),
    (3, 'some3', 'some3@gmail.com', '$2a$11$Ry/taAHsNgYZLlVTfwjs5.YCk7YcYCEZRprRcjosJOsy0eHQ/2u12');

INSERT INTO users_roles (user_id, role_id)
VALUES
    (1, 2),
    (2, 2),
    (3, 1),
    (3, 2);

