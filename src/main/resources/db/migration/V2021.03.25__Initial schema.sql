CREATE TABLE account
(
    id      serial not null,
    balance numeric   not null default 0,
    PRIMARY KEY (id)
);