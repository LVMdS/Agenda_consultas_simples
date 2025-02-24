# Agenda de Consultas

Este é um sistema de agendamento de consultas desenvolvido em Java utilizando a biblioteca Swing para criar uma interface gráfica de fácil uso. A aplicação permite o cadastro de clientes, agendamento de consultas e gestão das consultas pelo administrador.

## Funcionalidades

- **Cadastro de Clientes:** Permite cadastrar clientes com nome, telefone, endereço e e-mail.
- **Agendamento de Consultas:** Permite agendar consultas para clientes cadastrados, com horários disponíveis de segunda a sexta-feira, das 08:00 às 11:30 e das 13:30 às 18:00.
- **Consulta de Agendamentos:** Permite consultar as consultas agendadas para uma data específica.
- **Área de Administrador:** Permite ao administrador desmarcar consultas, protegida por senha.

## Requisitos

- Java 8 ou superior
- Eclipse IDE (ou qualquer outra IDE Java)

## Como Rodar o Projeto

1. Clone o repositório ou faça o download dos arquivos.
2. Abra o projeto no Eclipse ou em outra IDE de sua preferência.
3. Execute a classe `AgendaConsultas.java`.
4. A interface gráfica será exibida e você poderá realizar as operações de cadastro, agendamento, consulta e administração.

## Estrutura do Código

O código é dividido em métodos principais que realizam as seguintes operações:

- `main`: Inicia a aplicação e chama o método que cria a interface gráfica.
- `criarTelaPrincipal`: Configura a interface principal com botões e campos para interação com o usuário.
- `agendarConsulta`: Permite agendar uma consulta para um cliente, verificando se o horário já está ocupado.
- `atualizarListaConsultas`: Atualiza a lista de consultas agendadas para uma data específica.
- `cadastrarCliente`: Cadastra novos clientes na base de dados interna.
- `areaAdministrador`: Permite ao administrador desmarcar consultas, após a autenticação da senha.

## Senha de Administrador

A senha para acessar a área do administrador é: `admin123`

## Licença

Este projeto está licenciado sob a Licença MIT

## Contribuições

Contribuições são bem-vindas! Se você deseja contribuir, por favor, faça um fork deste repositório, crie uma branch com suas modificações e submeta um pull request.
