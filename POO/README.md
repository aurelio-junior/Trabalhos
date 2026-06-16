# Projeto

Este repositório está organizado em duas subpastas principais:

```text
📂 Projeto
├── 📂 MODIFICAR
└── 📂 FINAL
```

## Estrutura do Projeto

### `MODIFICAR/`
Esta pasta é destinada ao **desenvolvimento e alterações do código**.

Todas as modificações, testes, correções e implementações **devem ser feitas exclusivamente nesta pasta**.

Objetivo:
- Desenvolver novas funcionalidades
- Corrigir bugs
- Testar alterações
- Validar comportamento do algoritmo

### `FINAL/`
Esta pasta contém a **versão estável do algoritmo**.

O código presente aqui deve ser considerado a versão consolidada do projeto e **não deve ser alterado diretamente**.

Somente após:

- concluir as implementações;
- validar o funcionamento;
- passar por todos os testes necessários;

as modificações da pasta `MODIFICAR` devem ser transferidas para a pasta `FINAL`.

## Fluxo de Trabalho Recomendado

1. Faça todas as alterações em `MODIFICAR/`;
2. Execute e valide os testes;
3. Verifique se o comportamento está correto;
4. Apenas depois disso, copie as mudanças para `FINAL/`.

> **Importante:** Nunca implemente alterações diretamente na pasta `FINAL`, pois ela representa a versão validada do algoritmo.

## Objetivo da Organização

Essa separação foi criada para:

- evitar alterações acidentais na versão estável;
- facilitar testes e experimentações;
- manter uma versão final confiável do algoritmo;
- melhorar o controle das modificações realizadas.
