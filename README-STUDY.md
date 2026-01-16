# README de Estudos – Módulo de Vendas (Sale, ItemSale, Product, Customer)

Este documento foi criado para apoiar seus estudos de Spring Boot, JPA/Hibernate e modelagem de agregados usando o contexto de **vendas**.

## 1. Objetivo do projeto de estudo

Praticar:

- Modelagem de entidades relacionadas:
  - `Sale` (venda)
  - `Customer` (cliente)
  - `ItemSale` (itens da venda)
  - `Product` (produtos)
- Uso de **relações JPA** (`@ManyToOne`, `@OneToMany`) com `cascade` e `orphanRemoval`.
- Atualização de agregados complexos (atualizar a venda inteira, atualizar apenas um item, remover item, etc.).
- Boas práticas com **Hibernate** para evitar erros como:
  - `org.hibernate.HibernateException: A collection with orphan deletion was no longer referenced by the owning entity instance`

---

## 2. Conceito de agregado: Sale como raiz

Neste projeto, a entidade **Sale** é tratada como **agregado raiz**:

- Uma `Sale` possui:
  - Um `Customer` (cliente da venda)
  - Uma lista de `ItemSale` (itens da venda)
  - Um campo `totalAmount` (total da venda)
- Cada `ItemSale` possui:
  - Um `Product`
  - Quantidade, valor unitário e valor total do item.

Treine sempre pensando que:

> A venda (`Sale`) é quem **manda** nos itens (`ItemSale`). Itens não existem "soltos" sem uma venda.

---

## 3. Mapeamento importante: `orphanRemoval = true`

Na entidade `Sale`:

```java
@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemSale> itemSale;
```

### O que isso significa na prática?

- Quando você remove um item da lista `sale.getItemSale()` e salva a venda, o Hibernate **deleta o registro** do banco.
- Quando você faz `sale.getItemSale().clear()` e depois adiciona novos itens, todos os itens antigos são apagados e substituídos pelos novos.

### Regras para evitar erros

- **Não** troque a lista inteira por outra instância em entidades gerenciadas:
  - Evitar: `sale.setItemSale(novaLista)` em updates.
- **Sempre** trabalhe na lista que já existe:
  - `sale.getItemSale().clear();`
  - `sale.getItemSale().add(novoItem);`

Isso evita a exceção:

> `A collection with orphan deletion was no longer referenced by the owning entity instance`

---

## 4. Casos de uso para treinar

### 4.1. Criar venda completa – `POST /sales`

Recebe um DTO com:

- `idCustomer`
- Lista de itens (`productId`, `quantity`, `amount`)

Fluxo sugerido para treinar:

1. Buscar `Customer` por `idCustomer`.
2. Criar `Sale` e setar o `customer`.
3. Para cada item do DTO:
   - Buscar `Product` por `productId`.
   - Criar `ItemSale` e setar: produto, quantidade, amount, totalAmount.
   - Setar `sale` dentro do `ItemSale`.
   - Adicionar na lista: `sale.getItemSale().add(item)`.
4. Calcular `totalAmount` da venda somando o `totalAmount` de cada item.
5. Salvar a `Sale` com `saleRepository.save(sale)`.

### 4.2. Atualizar venda inteira – `PUT /sales/{idSale}`

Objetivo: substituir completamente os itens e, se necessário, o cliente.

Fluxo sugerido:

1. Buscar `Sale` por `idSale`.
2. Atualizar o `customer`, se enviado.
3. Limpar a lista de itens atual: `sale.getItemSale().clear();`.
4. Criar novos itens a partir do DTO e adicionar na mesma lista.
5. Recalcular `totalAmount`.
6. Salvar a venda.

> Aqui você treina diretamente o uso correto de `orphanRemoval`.

### 4.3. Atualizar apenas um item – `PUT /sales/{idSale}/items/{idItem}`

Objetivo: mudar apenas um item específico de uma venda.

Fluxo sugerido:

1. Buscar `Sale` por `idSale`.
2. Encontrar o `ItemSale` na lista: `sale.getItemSale().stream().filter(i -> i.getId().equals(idItem))...`.
3. Atualizar produto, quantidade e valores do item.
4. Recalcular `totalAmount` da venda.
5. Salvar a venda.

> Treine manipular o filho sempre através da raiz `Sale`, não via `ItemSaleRepository` direto.

### 4.4. Remover item da venda – `DELETE /sales/{idSale}/items/{idItem}`

Fluxo sugerido:

1. Buscar `Sale` por `idSale`.
2. Encontrar o item na lista.
3. Remover da lista: `sale.getItemSale().remove(item)`.
4. Recalcular `totalAmount`.
5. Salvar a venda.

---

## 5. Serviço de ItemSale (ItemSaleService)

O arquivo `ItemSaleService.java` existe para operações diretas sobre `ItemSale` por ID, por exemplo:

```java
public ItemSaleResponseDto updateItemSaleById(Long id, ItemSaleRequestDto itemSaleRequestDto) {
    ItemSale model = itemSaleRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemSales nots exits"));

    model.setAmount(itemSaleRequestDto.getAmount());
    model.setQuantity(itemSaleRequestDto.getQuantity());
    model.setTotalAmount(itemSaleRequestDto.getTotalAmount());

    if (model.getProduct() != null) {
        Product product = productRepository.findById(itemSaleRequestDto.getProductId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Products nots exits"));
        model.setProduct(product);
    }

    ItemSale modelSaved = itemSaleRepository.save(model);

    return itemSaleMapper.toResponse(modelSaved);
}
```

Para fins de **estudo de agregados**, é interessante comparar:

- Atualizar `ItemSale` diretamente (como acima).
- Atualizar `ItemSale` através de `SaleService`, encontrando o item pela lista dentro da venda e recalculando o total da venda.

---

## 6. Sugestão de exercícios práticos

1. Implementar o endpoint `PUT /sales/{idSale}` que **recria todos os itens** usando `clear()` + `add()` na lista da venda.
2. Implementar o endpoint `PUT /sales/{idSale}/items/{idItem}` que atualiza apenas um item e recalcula o total.
3. Implementar o endpoint `DELETE /sales/{idSale}/items/{idItem}` que remove um item da venda.
4. Criar testes simples (manuais ou automatizados) para verificar:
   - Se remover item realmente deleta no banco (`orphanRemoval = true`).
   - Se atualizar a venda não gera o erro de coleção órfã.

---

## 7. Como usar este README

- Você pode abrir este arquivo (`README-STUDY.md`) durante o desenvolvimento para relembrar as regras e os fluxos.
- Sinta-se à vontade para ir anotando dúvidas, decisões de design e exemplos de código que funcionaram bem ou geraram erros. Isso ajuda a fixar os conceitos.



