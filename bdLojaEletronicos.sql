--DROP TABLE Cliente;
--DROP TABLE Admin1;
--DROP TABLE Produto;

CREATE TABLE Cliente (
  cod_Cliente BIGINT NOT NULL AUTO_INCREMENT,
  nome_Cliente VARCHAR(255),
  address VARCHAR(255),
  tel VARCHAR(255),
  username VARCHAR(255),
  password VARCHAR(50),
  PRIMARY KEY(cod_Cliente)
);

CREATE TABLE Admin1 (
  id_Admin1 BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(50),
  username VARCHAR(255),
  password VARCHAR(50),
  PRIMARY KEY(id_Admin1)
);

CREATE TABLE Produto (
  id_Produto BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255),
  preco VARCHAR(50),
  regiao VARCHAR(50),
  cod_Produto VARCHAR(12),
  PRIMARY KEY(id_Produto)
);

CREATE TABLE Pedido(
  num_Pedido BIGINT NOT NULL AUTO_INCREMENT,
  valor DOUBLE,
  PRIMARY KEY(num_Pedido)
);


INSERT INTO Cliente (cod_Cliente, nome_Cliente, username, password, address, tel) VALUES (1, "Joao", "joao", "123", "R. da Consolação", "1199876-3490");
INSERT INTO Admin1 (nome, username, password) VALUES ("Pedro", "admin", "admin");
INSERT INTO Produto (cod_Produto, nome, preco, regiao) VALUES ("12a7", "Fone de ouvido JBL", "R$ 218,94", "Sul");

SELECT * FROM Admin1;
SELECT * FROM Cliente;
SELECT * FROM Produto;
SELECT * FROM Pedido;