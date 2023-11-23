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


INSERT INTO Homebroker (url, ip) values ("www.naosei.com", "1");

INSERT INTO Usuario (nome,email,cpf,data_nascimento,Homebroker_ipHomebroker) values 
("Luiz Akazawa", "luiz@gmail.com","123", "2023-11-20", "1"),
("Maria Seilá", "maria@gmail.com", "3123","2023-11-20", "1");

INSERT INTO Conta (username,senha,tipo_conta,saldo,data_criacao,Usuario_cpfUsuario) values 
("Luiz", "1", "Black", 12345.20, "2023-11-12", "123"),
("Marie", "54321", "Gold", 145.20, "2023-11-30", "3123");

INSERT INTO Acoes (sigla,cotacao,empresa_proprietaria) values
("FLRY3", 20.34, "Fleury"),
("MGLU3", 2.22, "Magazine Luiza"),
("BBAS3", 50.66, "Banco do Brasil")
;

select * from conta;
select * from usuario;
select * from homebroker;
select * from acoes;
select * from conta_has_acoes;
