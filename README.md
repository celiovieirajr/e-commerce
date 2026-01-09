# E-Commerce Core API

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.x-brightgreen?style=flat-square&logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.9+-blue?style=flat-square&logo=apachemaven)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-success?style=flat-square&logo=swagger)
![CI](https://img.shields.io/github/actions/workflow/status/your-org/ecommerce-core/ci.yml?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-lightgrey?style=flat-square)

> *Projeto de E-commerce desenvolvido com Java e Spring Boot, focado em múltiplos CRUDs, arquitetura limpa, documentação com Swagger e pipeline de CI/CD.*

---

## Visão Geral

O **E-Commerce Core API** é um projeto backend que simula o núcleo de um sistema de vendas online, abordando cenários reais do mercado como:

- Cadastro e gerenciamento de produtos
- Organização por categorias
- Gestão de clientes
- Fluxo de pedidos
- Itens de pedido
- Evolução contínua com boas práticas

O projeto foi pensado para **aprendizado, portfólio e base para projetos maiores**.

---

## Sumário

- [Stack & Versões](#-stack--versões)
- [Arquitetura](#-arquitetura)
- [Domínios do Sistema](#-domínios-do-sistema)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Documentação Swagger](#-documentação-swagger)
- [Configuração](#-configuração)
- [Execução Local](#-execução-local)
- [CI & CD](#-ci--cd)
- [Quality Gates](#-quality-gates)
- [Roadmap](#-roadmap)
- [Licença](#-licença)

---

## 🛠 Stack & Versões

| Camada | Tecnologia |
|------|-----------|
| Linguagem | Java 17 (LTS) |
| Framework | Spring Boot 3.3.x |
| API | Spring Web (REST) |
| Persistência | Spring Data JPA |
| Banco de Dados | H2 (dev) / PostgreSQL (prod) |
| Documentação | Springdoc OpenAPI |
| Build | Maven |
| CI/CD | GitHub Actions |

---

## 🏗 Arquitetura

flowchart TD
    Client[Client / Frontend] --> Controller[Controllers]
    Controller --> Service[Services]
    Service --> Repository[Repositories]
    Repository --> Database[(Database)]

    Controller --> Swagger[Swagger UI]

🧱 Domínios do Sistema
CRUDs Implementados
Entidade	Descrição
Product	Produtos disponíveis para venda
Category	Categorias dos produtos
Customer	Dados do cliente
Order	Pedido realizado
OrderItem	Itens do pedido
Funcionalidades Gerais

CRUD completo

Validações com Bean Validation

Paginação e ordenação

Tratamento global de exceções

Padrões REST

```text
ecommerce-core/
├─ .github/
│  └─ workflows/
│     ├─ ci.yml
│     └─ cd.yml
├─ src/main/java/com/example/ecommerce/
│  ├─ controller/
│  ├─ service/
│  ├─ repository/
│  ├─ domain/
│  ├─ dto/
│  ├─ mapper/
│  └─ config/
├─ src/main/resources/
│  ├─ application.yml
│  ├─ application-dev.yml
│  └─ application-prod.yml
├─ src/test/java/
├─ pom.xml
└─ README.md
```

📑 Documentação Swagger

A API é documentada utilizando Swagger / OpenAPI.

Acesso
```
http://localhost:8080/swagger-ui.html
```
ou
```
http://localhost:8080/swagger-ui/index.html
```
Recursos Documentados
Endpoints REST
Métodos HTTP
Parâmetros

Exemplos de request/response
Códigos de status HTTP

OpenAPI JSON
http://localhost:8080/v3/api-docs

Pré-requisitos

Java 17
Maven Wrapper

🔄 CI & CD
```text
CI – Continuous Integration
Executado automaticamente em:
Push para main e develop
Pull Requests

Etapas:
Build do projeto
Execução de testes
Análise de qualidade
Geração do artefato
CD – Continuous Delivery
Deploy automatizado após aprovação
Suporte a ambientes cloud
Preparado para Docker e EC2
```
