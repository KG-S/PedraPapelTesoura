# Supimpa Jokenpô

Projeto desenvolvido para a disciplina de **Redes de Computadores**, com o objetivo de aplicar conceitos de comunicação em rede utilizando **Sockets TCP** e arquitetura **Cliente-Servidor**.

## Participantes

- Érick Rosdachimo Ribeiro
- Kauã Gabriel de Oliveira Silva
- Letícia Kaori Yabuuti
- Valéria da Silva Sartorio

## Sobre o Projeto

O **Supimpa Jokenpô** é uma implementação digital do clássico jogo **Pedra, Papel e Tesoura**, permitindo que dois jogadores disputem partidas através de uma conexão de rede.

O sistema foi desenvolvido para demonstrar conceitos fundamentais de Redes de Computadores, como:

- Comunicação entre processos;
- Arquitetura Cliente-Servidor;
- Utilização de Sockets;
- Troca de mensagens em tempo real;
- Protocolo TCP.

## Objetivo

Desenvolver uma aplicação distribuída capaz de permitir a interação entre dois jogadores em tempo real por meio de uma rede de computadores, utilizando sockets para comunicação entre clientes e servidor.

## Arquitetura do Sistema

O sistema segue o modelo **Cliente-Servidor**.

### Servidor

Responsável por:

- Aceitar conexões dos jogadores;
- Gerenciar a comunicação;
- Receber as jogadas;
- Processar as regras do jogo;
- Enviar os resultados para os clientes.

### Cliente

Responsável por:

- Conectar ao servidor;
- Permitir que o jogador escolha sua jogada;
- Enviar a jogada ao servidor;
- Receber o resultado da partida.

### Fluxo de Comunicação

```text
Cliente 1 ──► Servidor ◄── Cliente 2
Cliente 1 ◄── Servidor ──► Cliente 2
```

Toda a lógica da partida é centralizada no servidor.

## Funcionamento

1. O servidor é iniciado e fica aguardando conexões.
2. Dois clientes se conectam ao servidor.
3. Cada jogador escolhe uma jogada:
   - Pedra
   - Papel
   - Tesoura
4. As escolhas são enviadas ao servidor.
5. O servidor processa as regras do jogo.
6. O resultado é enviado para ambos os jogadores.
7. Novas rodadas podem ser iniciadas ou o jogo pode ser encerrado.

### Regras

| Jogada | Vence |
|---------|---------|
| Pedra | Tesoura |
| Tesoura | Papel |
| Papel | Pedra |

## Protocolo Utilizado

O projeto utiliza o protocolo **TCP (Transmission Control Protocol)**.

### Motivos da escolha

- Conexão confiável;
- Garantia de entrega dos dados;
- Ordem correta das mensagens;
- Menor probabilidade de perda de informações.

Essas características garantem a integridade das jogadas e dos resultados transmitidos entre os participantes.

## Linguagem Utilizada

O projeto foi desenvolvido em **Java**, utilizando suporte nativo para programação em rede através das classes:

- `Socket`
- `ServerSocket`
- `InputStream`
- `OutputStream`

## Demonstração

Vídeo de demonstração do projeto:

[Assista no YouTube](https://youtu.be/47Lj4Asa8C4?is=GInsCt7uycpIcEAC)

## Como Executar

### Pré-requisitos

- Java instalado na máquina;
- Conexão de rede ativa.

### Passo 1

Execute o servidor.

O servidor ficará aguardando conexões dos clientes.

### Passo 2

Execute os dois clientes.

Cada cliente representará um jogador.

### Passo 3

Realize as jogadas escolhendo entre:

- Pedra
- Papel
- Tesoura

### Passo 4

Aguarde o processamento do servidor e a exibição do resultado da partida.

## Conceitos Aplicados

- Redes de Computadores
- Comunicação Cliente-Servidor
- Sockets TCP
- Troca de Mensagens
- Programação Distribuída
- Java Networking API
