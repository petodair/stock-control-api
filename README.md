# Stock Control API

Sejá bem-vindo ao repositório do projeto **Stock Control API**, o objetivo
deste projeto é criar um aplicativo capaz de facilitar o controle de produtos
em estoque de algum comércio e armazém.

---

## Proposta

Como mencionado no texto de introdução, a principal proposta
do aplicativo é auxiliar no controle de estoque de determinados
tipos de produto. Por conta da ideia ter se formado em decorrência
da necessidade(afinal, é assim que muitas ideias surgem, não?) de
auxílio para controle de datas produtos no meu trabalho, num momento 
o aplicativo se limita a produtos de *talho*, que
por consequência são perecíveis, e isso está refletido direto na
entidade, o que acaba por diminuir a abrangência do uso API.

---

## Futuro

Penso em deixá-lo mais abrangente, para poder 
trabalhar com uma variedade maior de produtos e
atender um limite maior de necessidades, ajudando
um número maior de pessoas

---

## Requests(requisições)

Caso queria testar, aqui vai uma lista de possíveis requisições

### Product(Produto)

- Salvar produto 

POST: `/product`

```
{
  "name": "Coxinha da asa congelada",
  "code": "28930",
  "price": 25,
  "productType": "CONGELADO"
}
```

- Listar todos os produtos

GET: `/product`

### Stock Batch(Lote em estoque)

