# Projeto de Exemplo de Integração HIVE

Projeto utilizado para se integrar com as duas APIs externas a fim de retornar os dados dos clientes para que seja disponibilizado em uma base de dados normatizada dentro do Hive.

## Desenvolvimento

Projeto feito utilizando Quarkus + Banco de dados interno + Panache.

* Para o Mercado Livre: Os dados são persistidos na tabela utilizando sql nativo como exemplo.
* Para a Shopee: Estes dados não estão em tabelas. São gerados utilizando o Faker
* Para o Hive: Os dados são gravados e buscados utilizando-se do Panache.

Para executar utilize o comando maven abaixo:

```bash
quarkus:dev
```

Ao executar o comando acima, abra o navegador e execute a url `http://localhost:8080/q/swagger-ui/` para ter acesso aos serviços expostos do projeto.

Configuração Docker:
> [Não houve tempo de configurar os arquivos para o Docker.]
---

## Serviços Expostos na API do Hive

Serviços Externos [São os serviços de exemplo simulando a Shopee e Mercado Livre]

Retornam os dados dos clientes cadastrados nestes sistemas. 
* Para o Mercado Livre: Estes dados estão gravados em tabela. [Dados gerados aleaóriamente]
* Para a Shopee: Estes dados não estão em tabelas e são gerados três na hora da execução. [Dados gerados aleaóriamente]

### Mercado Livre

| Tipo | URL | Parâmetros | Retorno | Descrição |
|----------|-----------|-----------|-----------|-----------|
| **GET** | `/mercadolivre/dados/clientes /buscar/{numeroSolicitacaoSequencial}` | numeroSolicitacao | 5 itens por requisição | Retorna os dados dos clientes a partir do número de solicitação |
| **GET** | `/mercadolivre/dados/clientes/gerar` | Nenhum | 20 itens por requisição | Gera uma massa de dados aleatório com 20 itens |

### Shopee 

| Tipo | URL | Parâmetros | Retorno | Descrição |
|----------|-----------|-----------|-----------|-----------|
| **GET** | `/shopee/buscar/novos/cadastros` | Nenhum | 3 itens por requisição | Retorna os dados dos novos cadastros dos clientes [Dados fictícios] |


---

# Serviços do Hive

Serviço disponibilizado no Hive para buscar os dados externamente, padronizar e incluir em uma tabela interna para consulta posterior.

## Motor de Busca Automatizada

Os dados são buscados nos serviços externos do Mercado Livre e Shopee, que é feita de forma automátizada utilizando agendamento da execução configurada por segundo.

Após a busca, os dados são padronizados de acordo com cada resposta dos serviços e estes são incluídos em uma tabela interna.

| Tipo | URL | Execução | Descrição |
|----------|-----------|-----------|-----------|
| **GET** | `/task/mercadolivre/buscar/dados/clientes` | a cada 10 segundos | Busca os dados dos clientes no mercado livre de forma automatica a cada 10 segundos |
| **GET** | `/task/shopee/buscar/dados/clientes` | a cada 30 segundos | Busca os dados dos clientes na Shopee de forma automatica a cada 30 segundos. |

## Consulta dos Dados Padronizados

Após a consulta dos dados nos serviços externos, estes são disponibilizados em uma tabela interna para a consulta destes.

| Tipo | URL | Descrição |
|----------|-----------|-----------|
| **GET** | `/hive/listar/dados/cliente` | Lista os dados dos clientes do Mercado Livre e Shopee gravados no hive padronizados |
| **GET** | `/hive/listar/dados/cliente/mercadolivre` | Lista os dados dos clientes do Mercado Livre gravados no hive padronizados |
| **GET** | `/hive/listar/dados/cliente/shopee` | Lista os dados dos clientes da Shopee gravados no hive padronizados |

---

# Vídeo Explicativo

Vídeo explicativo sobre o projeto.

* [Vídeo Explicativo](https://github.com/javaflores/hive/blob/main/src/main/resources/explicacao-api.mp4)
