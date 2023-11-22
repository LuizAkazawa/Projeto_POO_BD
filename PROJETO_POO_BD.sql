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
    
    PRIMARY KEY (Conta_codConta, Acoes_siglaAcoes),
    
	CONSTRAINT fk_Conta_has_Acoes_Conta
    FOREIGN KEY (Conta_codConta) REFERENCES Conta (cod_conta),
    
	CONSTRAINT fk_Conta_has_Acoes_Acoes
    FOREIGN KEY (Acoes_siglaAcoes) REFERENCES Acoes(sigla)  
    
);

CREATE TABLE IF NOT EXISTS Fundo_Imobiliario(
	sigla VARCHAR(6) NOT NULL,
    cotacao DOUBLE NOT NULL,
    tipo VARCHAR(45) NULL,
    
    PRIMARY KEY (sigla)
);

CREATE TABLE IF NOT EXISTS Conta_has_FIIs (
	Conta_codConta INT NOT NULL,
    FIIs_siglaFIIs VARCHAR(6) NOT NULL,
    
    PRIMARY KEY (Conta_codConta, FIIs_siglaFIIs),
    
	CONSTRAINT fk_Conta_has_FIIs_Conta
    FOREIGN KEY (Conta_codConta) REFERENCES Conta (cod_conta),
    
	CONSTRAINT fk_Conta_has_FIIs_FIIs
    FOREIGN KEY (FIIs_siglaFIIs) REFERENCES Fundo_Imobiliario(sigla)  
    
);

select * from conta;
select * from usuario;
select * from homebroker;
select * from usuario where cpf = '123';