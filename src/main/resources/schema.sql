CREATE TABLE manufacturer
(
    id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) NOT NULL unique
);

CREATE TABLE drugs
(
    id               INTEGER PRIMARY KEY AUTO_INCREMENT,
    date_of_approval datetime     NOT NULL,
    description      varchar(255) NOT NULL,
    name             varchar(255) NOT NULL unique,
    treatment_for    varchar(255) NOT NULL,
    manufacturer_id  INTEGER      NOT NULL,
    FOREIGN KEY (manufacturer_id) REFERENCES manufacturer (id)
);


CREATE TABLE admin
(
    id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    name     varchar(255) NOT NULL unique,
    password varchar(255) NOT NULL,
    role     varchar(255) NOT NULL
);