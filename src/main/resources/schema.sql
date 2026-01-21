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

CREATE TABLE IF NOT EXISTS products (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  codProduct VARCHAR(50),
  description VARCHAR(255),
  price NUMERIC(38,2)
);

CREATE TABLE IF NOT EXISTS sales (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  customer_id BIGINT,
  totalAmount NUMERIC(38,2),
  CONSTRAINT fk_sales_customers FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE IF NOT EXISTS itemSales (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  sale_id BIGINT,
  product_id BIGINT,
  quantity INTEGER,
  amount NUMERIC(38,2),
  totalAmount NUMERIC(38,2),
  CONSTRAINT fk_itemSales_sales FOREIGN KEY (sale_id) REFERENCES sales(id),
  CONSTRAINT fk_itemSales_products FOREIGN KEY (product_id) REFERENCES products(id)
);

MERGE INTO customers (id, name, cpf, bairro, cep, complemento, estado, ibge, localidade, logradouro, regiao, uf, unidade) KEY(id)
VALUES (1, 'Cliente Exemplo', '00000000000', 'Centro', '00000000', '', 'SP', '00000000', 'Sao Paulo', 'Rua Exemplo', 'Sul', 'SP', 'Unidade 1');

MERGE INTO products (id, codProduct, description, price) KEY(id) VALUES (1, 'P001', 'Produto Exemplo 1', 10.00);
MERGE INTO products (id, codProduct, description, price) KEY(id) VALUES (2, 'P002', 'Produto Exemplo 2', 20.00);

MERGE INTO sales (id, customer_id, totalAmount) KEY(id) VALUES (1, 1, 30.00);

MERGE INTO itemSales (id, sale_id, product_id, quantity, amount, totalAmount) KEY(id) VALUES (1, 1, 1, 1, 10.00, 10.00);
MERGE INTO itemSales (id, sale_id, product_id, quantity, amount, totalAmount) KEY(id) VALUES (2, 1, 2, 1, 20.00, 20.00);

