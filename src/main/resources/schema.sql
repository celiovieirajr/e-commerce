CREATE TABLE IF NOT EXISTS customers (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  bairro VARCHAR(100),
  cep VARCHAR(9),
  complemento VARCHAR(100),
  estado VARCHAR(100),
  ibge VARCHAR(10),
  localidade VARCHAR(100),
  logradouro VARCHAR(150),
  regiao VARCHAR(100),
  uf VARCHAR(2),
  unidade VARCHAR(50)
);

INSERT INTO customers (name, cpf, bairro, cep, complemento, estado, ibge, localidade, logradouro, regiao, uf, unidade)
VALUES ('Cliente Exemplo','00000000000','Centro','00000000','','SP','00000000','Sao Paulo','Rua Exemplo','Sul','SP','Unidade 1');
