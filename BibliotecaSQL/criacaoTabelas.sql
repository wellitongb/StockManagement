/** Criando tabela Cliente */
CREATE TABLE Cliente(
	idCliente SERIAL NOT NULL UNIQUE,
	nome VARCHAR,
	PRIMARY KEY(idCliente)
);

/** Criando tabela Livro */
CREATE TABLE Livro(
	idLivro SERIAL NOT NULL UNIQUE,
	nomeLivro VARCHAR,
	PRIMARY KEY (idLivro)
);

/** Criando tabela Funcionario */
CREATE TABLE Funcionario(
	idFuncionario SERIAL NOT NULL UNIQUE,
	nomeFuncionario VARCHAR,
	cpf VARCHAR,
	salario FLOAT,
	dataDeNascimento DATE
	PRIMARY KEY(idFuncionario)
);
