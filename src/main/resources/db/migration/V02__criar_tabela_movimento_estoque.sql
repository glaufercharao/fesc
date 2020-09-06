CREATE TABLE estoque_movimento(
	codigo BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_produto BIGINT NOT NULL,
	tipo_movimentacao  INT NOT NULL,
	valor_venda DECIMAL(10,2) NOT NULL,
	data_venda Date NOT NULL,
	quantidade_movimentada INT NOT NULL,
	
	CONSTRAINT FK_Produto_Estoque FOREIGN KEY (id_produto)
    REFERENCES produto(codigo)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;