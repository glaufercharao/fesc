CREATE TABLE produto(
	codigo BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(1000) NOT NULL,
	tipo_produto VARCHAR(250) NOT NULL,
	valor_fornecedor DECIMAL(10,2) NOT NULL,
	quantidade_estoque INT NOT NULL
	 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO produto(descricao, tipo_produto, valor_fornecedor, quantidade_estoque) VALUES ('Computador','Eletrônico', 2799.99, 1);
INSERT INTO produto(descricao, tipo_produto, valor_fornecedor, quantidade_estoque) VALUES ('Líquidificador','Eletrodoméstico', 1599.99, 5);