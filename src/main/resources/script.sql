CREATE TABLE IF NOT EXISTS cliente (
	id INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(80) NOT NULL,
	cpf VARCHAR NOT NULL,
	endereco VARCHAR(100) NOT NULL,
	UNIQUE KEY cpf_UNIQUE (cpf)
);

CREATE TABLE IF NOT EXISTS conta (
	id INT AUTO_INCREMENT PRIMARY KEY,
	id_cliente INT NOT NULL,
	agencia INT NOT NULL,
	conta INT NOT NULL,
	tipo_conta ENUM ('poupanca', 'corrente') NOT NULL,
	saldo DECIMAL(8,2) NOT NULL,
	FOREIGN KEY (id_cliente) REFERENCES public.cliente(id),
	UNIQUE KEY agencia_conta_UNIQUE (agencia, conta)
);

