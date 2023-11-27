#Nome : Luiz Eduardo Akazawa Nora
#Matricula : 220

DROP SCHEMA IF EXISTS Investimentos;
CREATE SCHEMA IF NOT EXISTS Investimentos;
use Investimentos;

CREATE TABLE IF NOT EXISTS Homebroker(
	ip VARCHAR(45) NOT NULL,
	url TEXT NOT NULL,
    
    PRIMARY KEY (ip)
);

CREATE TABLE IF NOT EXISTS Usuario (
	nome VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    data_nascimento DATE,
    Homebroker_ipHomebroker VARCHAR(45) NOT NULL,
    
    PRIMARY KEY (cpf),
	CONSTRAINT fk_Usuario_Homebroker
    FOREIGN KEY (Homebroker_ipHomebroker) REFERENCES Homebroker (ip)
    ON DELETE CASCADE
     
);

CREATE TABLE IF NOT EXISTS Conta (
	cod_conta INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(45) NOT NULL,
    senha VARCHAR(45) NOT NULL,
    tipo_conta VARCHAR(8) NOT NULL,
    saldo DOUBLE DEFAULT 0,
    data_criacao DATE NULL,
    Usuario_cpfUsuario VARCHAR(11) NOT NULL,
    
    PRIMARY KEY (cod_conta),
    CONSTRAINT fk_Conta_Usuario
    FOREIGN KEY (Usuario_cpfUsuario) REFERENCES Usuario (cpf)
    ON DELETE CASCADE
   
);

CREATE TABLE IF NOT EXISTS Acoes(
	sigla VARCHAR(6) NOT NULL,
    cotacao DOUBLE NOT NULL,
    empresa_proprietaria VARCHAR(45) NULL,
    
    PRIMARY KEY (sigla)
);

CREATE TABLE IF NOT EXISTS Conta_has_Acoes (
	Conta_codConta INT NOT NULL,
    Acoes_siglaAcoes VARCHAR(6),
    quantidade INT,
    
    PRIMARY KEY (Conta_codConta, Acoes_siglaAcoes),
    
	CONSTRAINT fk_Conta_has_Acoes_Conta
    FOREIGN KEY (Conta_codConta) REFERENCES Conta (cod_conta),
    
	CONSTRAINT fk_Conta_has_Acoes_Acoes
    FOREIGN KEY (Acoes_siglaAcoes) REFERENCES Acoes(sigla)  
    
);

#CRIANDO HOMEBROKER
INSERT INTO Homebroker (url, ip) values ("www.naosei.com", "1");

#CRIANDO USUARIOS
INSERT INTO Usuario (nome,email,cpf,data_nascimento,Homebroker_ipHomebroker) values 
("Luiz Akazawa", "luiz@gmail.com","1234", "2023-11-20", "1"),
("Maria Seilá", "maria@gmail.com", "3123","2022-09-10", "1"),
("Joaquina José", "joaquina@hotmail.com", "4567","2021-04-14", "1");

#CRIANDO CONTAS
INSERT INTO Conta (username,senha,tipo_conta,saldo,data_criacao,Usuario_cpfUsuario) values 
("Luiz", "1", "Black", 12345.20, "2023-11-12", "1234"),
("Marie", "2", "Gold", 145.20, "2023-11-30", "3123"),
("Joaquina", "3", "Gratuita", 348.90, "2023-11-30", "4567");

#CRIANDO AÇÕES
INSERT INTO Acoes (sigla,cotacao,empresa_proprietaria) values
("FLRY3", 20.34, "Fleury"),
("MGLU3", 2.22, "Magazine Luiza"),
("BBAS3", 50.66, "Banco do Brasil"),
("GOLL4", 9.10, "Gol"),
("LREN3", 14.60, "Lojas Renner"),
("CMIG4", 10.56, "Cemig"),
("ITUB4", 30.68, "Itau");

select * from conta;
select * from usuario;
select * from homebroker;
select * from acoes;
select * from conta_has_acoes;
