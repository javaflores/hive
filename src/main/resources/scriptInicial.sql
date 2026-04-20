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

-- INSERTS DA TABELA DE CLIENTES DO MERCADO LIVRE
INSERT INTO ClienteMercadoLivre (numeroSolicitacao, nome, codigoTipoDocumento, numeroDocumento, dataNascimento, nomePai, nomeMae, sexo, timestampAtualizacao)
VALUES (20261101510561790, 'João da Silva', 1, '12345678900', '1990-05-15', 'Carlos da Silva', 'Maria da Silva', 'M', '2026-04-20 13:37:47.013348');

INSERT INTO ClienteMercadoLivre (numeroSolicitacao, nome, codigoTipoDocumento, numeroDocumento, dataNascimento, nomePai, nomeMae, sexo, timestampAtualizacao)
VALUES (20261101510561812, 'Ana Souza Pereira', 2, '30843022000184', '1985-10-22', 'José Pereira', 'Lucia Souza', 'F', '2026-04-20 13:38:11.153412');

-- INSERTS DA TABELA DE CLIENTES PADRONIZADAS DO HIVE
INSERT INTO CLIENTES_EXTERNOS (NR_SEQUENCIAL_EXTERNO, CD_EMPRESA_EXTERNO, NM_EMPRESA_EXTERNO, DS_NOME, CD_TIPO_DOCUMENTO, NR_DOCUMENTO, DT_NASCIMENTO, NM_PAI, NM_MAE, TP_SEXO, TS_ULT_ATUALIZACAO)
VALUES (0, 2, 'Shopee LTDA', 'Carlos Eduardo Lima', 1, '12345678900', '1992-03-10', 'Roberto Lima', 'Fernanda Lima', 'M', '2026-04-19 09:08:38.988882');

INSERT INTO CLIENTES_EXTERNOS (NR_SEQUENCIAL_EXTERNO, CD_EMPRESA_EXTERNO, NM_EMPRESA_EXTERNO, DS_NOME, CD_TIPO_DOCUMENTO, NR_DOCUMENTO, DT_NASCIMENTO, NM_PAI, NM_MAE, TP_SEXO, TS_ULT_ATUALIZACAO)
VALUES (20261101510560920, 1, 'Mercado Livre do Brasil', 'Juliana Martins Souza', 3, 'US31220', '2019-07-25', 'Paulo Souza', 'Marcia Martins', 'F', '2026-04-19 10:54:12.625541');


