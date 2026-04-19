-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

create table ClienteMercadoLivre (
	id int AUTO_INCREMENT PRIMARY KEY,
	numeroSolicitacao decimal(17, 0) NOT NULL,
    nome varchar(255) NOT NULL,
    codigoTipoDocumento integer,
    numeroDocumento varchar(255),
    dataNascimento date,
    nomePai varchar(255),
    nomeMae varchar(255),
    sexo varchar(1) NOT NULL,
    timestampAtualizacao timestamp(6),
    primary key (id)
);

create table CLIENTES_EXTERNOS (
    ID int AUTO_INCREMENT PRIMARY KEY,
    NR_SEQUENCIAL_EXTERNO decimal(17, 0),
    CD_EMPRESA_EXTERNO integer,
    NM_EMPRESA_EXTERNO varchar(255),
    DS_NOME varchar(255) NOT NULL,
    CD_TIPO_DOCUMENTO integer,
    NR_DOCUMENTO varchar(255),
    DT_NASCIMENTO date,
    NM_PAI varchar(255),
    NM_MAE varchar(255),
    TP_SEXO varchar(1) NOT NULL,
    TS_ULT_ATUALIZACAO timestamp(6),
    primary key (ID)
);
