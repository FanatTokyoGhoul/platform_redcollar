CREATE TABLE company
(
    id   serial primary key,
    name character varying(255)
);

CREATE TABLE platform
(
    id        serial primary key,
    name      character varying(255),
    genres    character varying(255),
    url       character varying(255),
    id_company bigint REFERENCES company(id) NOT NULL
);
