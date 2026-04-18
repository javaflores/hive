-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

create table ClienteShopee (
	id int AUTO_INCREMENT PRIMARY KEY,
	numeroSolicitacao decimal(17, 0) NOT NULL,
    nome varchar(255) NOT NULL,
    codigoTipoDocumento integer,
    numeroDocumento varchar(255),
    dataNascimento date,
    nomePai varchar(255),
    nomeMae varchar(255),
    sexo char(1) NOT NULL,
    timestampAtualizacao timestamp(6),
    primary key (id)
);