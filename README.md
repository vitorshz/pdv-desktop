# pdv juliane e bosin (parte desktop)

### Eduardo Issao Nakai Frasson RA: 239648-1

### Vitor Shimizu RA:  240397-1

## Objetivo do projeto

Desenvolver um aplicativo de Ponto de Venda (PDV) que integre uma aplicação desktop com uma aplicação web, permitindo que os usuários acessem um banco de dados na aplicação web para gerenciar produtos e clientes.

**Objetivos Específicos DESKTOP:**

- A tela de venda deve exibir a lista de clientes e produtos obtidos a partir da API.
- Atualizar as listas de clientes e produtos a cada 5 minutos.
- Cada vez que a aplicação consumir dados da API de clientes e produtos, deve ser registrado um log da operação em um arquivo.txt na pasta raiz da aplicação.
- O log deve conter as seguintes informações: data e hora da operação, o tipo de operação (obtenção de clientes ou produtos) e o status da operação.

Ao confirmar a venda na tela de venda, a operação de salvar a venda deve ser realizada de forma assíncrona. Evitando bloquear a interface do usuário enquanto a operação é executada. Traga um feedback quando o servidor der o OK da inserção. Atenção, o armazenamento será feito na base de dados da aplicação WEB, só iremos armazenar localmente os arquivos de log.


## requisitos funcionais

- [x]  deve ser possível realizar uma venda;
- [x]  deve ser possível listar os clientes;
- [x]  deve ser possível listar os produtos;
- [x]  deve ser possível listar os itemvenda de uma venda;
- [x]  deve ser possível buscar o produto por id;
- [x]  deve ser possível criar um produto;
- [x]  deve ser possivel buscar o cliente por id;
- [x]  deve ser possível criar um cliente;
- [x]  deve ser possível listar os itemvenda;
- [x]  deve ser possível criar um itemvenda;
- [x]  deve ser possível atualizar o itemvenda;
- [x]  deve ser possível deletar o itemvenda;
- [x]  deve ser possível criar um venda;
- [x]  deve ser possível atualizar a venda;


## regras de negocio
- [x] cada venda so deve ter um cliente;
- [x] a venda não deve ser confirmada sem um cliente definido;
- [x] as informações de valortotal deve ser definido no sistema;
- [x] o itemvenda extende as informações do produto;


## requisitos não funcionais 
- [x] a aplicação deve ser feita em spring boot;

- [x] deve ser matido os dados em um banco de dados postgresql;
- [x] quem vai consumir a aplicação é uma aplicação java swing desktop;
