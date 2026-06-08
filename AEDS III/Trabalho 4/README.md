# Problema da Partição (Partition Problem) – Análise Comparativa de Heurísticas

Este projeto consiste na implementação e análise comparativa de algoritmos para a resolução do **Problema da Partição (Partition Problem)**, desenvolvido para a disciplina de Algoritmos e Estruturas de Dados III (AEDS III).

O objetivo é dividir um conjunto de $N$ números inteiros positivos em dois subconjuntos, $A$ e $B$, de modo que a diferença entre as somas dos elementos de cada subconjunto seja a menor possível (o mais próxima de zero). A qualidade de uma solução (fitness) é medida por:
$$\text{Fitness} = \left| \sum_{i \in A} x_i - \sum_{j \in B} x_j \right|$$

## Algoritmos Implementados

O sistema avalia três abordagens distintas para resolver o problema:
1. **Partition Puro (Abordagem Base):** Solução determinística padrão onde todos os elementos são inicialmente alocados no mesmo grupo. Serve como linha de base.
2. **Simulated Annealing (Arrefecimento Simulado):** Meta-heurística inspirada no processo de têmpera de metais. Utiliza o critério de aceitação de Boltzmann para escapar de mínimos locais, aceitando soluções piores temporariamente com base em uma temperatura decrescente.
3. **Algoritmo Genético (AG):** Abordagem evolutiva baseada na seleção natural. Utiliza população de soluções, seleção por torneio, crossover de um ponto, mutação por flip bit e elitismo para preservar a melhor solução de cada geração.

## Estrutura do Projeto

```text
├── include/                  # Ficheiros de cabeçalho (.h)
│   ├── leitor.h
│   ├── partition.h
│   ├── simulatedAnnealing.h
│   └── genetico.h
├── src/                      # Código-fonte (.c)
│   ├── leitor.c
│   ├── main.c
│   ├── partition.c
│   ├── simulatedAnnealing.c
│   └── genetico.c
├── Resultados/               # Pasta gerada automaticamente com as saídas
│   ├── PartitionPuro.txt     # Log detalhado das runs do algoritmo Base
│   ├── SimulatedAnnealing.txt# Log detalhado das runs do SA
│   ├── AlgoritmoGenetico.txt # Log detalhado das runs do AG
│   ├── resultadoMedia.txt    # Resumo das médias consolidado por tamanho N
│   └── resultadosTabela.txt  # Dados estruturados em CSV para geração de gráficos
├── instancias/               # Pasta contendo os ficheiros de testes (.txt)
├── Makefile                  # Script de automação da compilação
└── README.md                 # Documentação do projeto
```
## Metodologia de Testes

Para garantir a estabilidade estatística e mitigar o efeito da estocasticidade (aleatoriedade) das meta-heurísticas, o ambiente de testes foi projetado com a seguinte metodologia:
- **Instâncias**: Testes executados em 7 tamanhos de problemas ($N \in \{100, 200, 500, 1000, 2500, 5000, 10000\}$)
- **Variabilidade**: Para cada tamanho $N$, existem 5 variações de instâncias diferentes (tipos).
- **Execuções (runs)**: Cada algoritmo executa 5 runs independentes para cada uma das 35 instâncias totais.
- **Métricas**: O programa monitora o tempo de execução exato (via `<time.h>`) e a qualidade da solução (Fitness).

## Como Compilar e Executar
Este projeto utiliza um `Makefile` configurado para descobrir os ficheiros dinamicamente e que já inclui a flag matemática `-lm` necessária para o Linux/GCC.

### Pré-requisitos
Certifique-se de que tem o compilador `cc` e a ferramenta `make` instalados no seu sistema (Linux/Ubuntu/macOS ou ambiente MinGW no Windows).

### Passos para Execução:
1. Limpar compilações anteriores (Opcional):
```bash
make clean
```
2. Compilar o projeto:
```bash
make
```
3. Executar a bateria de testes:
```bash
make run
```
Ao executar `make run`,o terminal exibirá o progresso em tempo real do processamento de cada bloco de tamanho por algoritmo, acompanhado do tempo total gasto:
```bash
========================================================================
   Iniciando Bateria Geral de Experimentos (5 runs por instancia)
========================================================================

[PartitionPuro]      - Finalizado para 100   - Tempo total: 0.000854 s 
[SimulatedAnnealing] - Finalizado para 100   - Tempo total: 0.054120 s
[AlgoritmoGenetico]  - Finalizado para 100   - Tempo total: 0.124512 s
----------------------------------------------------------------------------
...
```

## Análise de Dados e Relatórios

Após a finalização, a pasta `Resultados/` conterá os relatórios gerados. O arquivo mais importante para a elaboração do relatório do trabalho é:
```text
Resultados/resultadosTabela.txt
```
Este arquivo é gerado no formato CSV padrão com o seguinte cabeçalho:
```csv
Algoritmo,Instancia,Tamanho,Qualidade,TempoExecucao
```
Ele guarda a média consolidada das 5 execuções (runs) de cada algoritmo para cada instância.