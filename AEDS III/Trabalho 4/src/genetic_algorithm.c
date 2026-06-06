#include <stdlib.h>
#include <time.h>

#include "genetic_algorithm.h"
#include "partition_problem.h"

#define POP_SIZE 50
#define GENERATIONS 200
#define MUTATION_RATE 0.01

typedef struct {
    int *chromosome;
    long long fitness;
} Individual;

/* =========================
   Fitness
========================= */
static void evaluate(Individual *ind, int values[], int n) {
    ind->fitness = evaluateFitness(values, ind->chromosome, n);
}

/* =========================
   Inicializa população
========================= */
static void initPopulation(Individual pop[], int values[], int n) {

    for (int i = 0; i < POP_SIZE; i++) {

        pop[i].chromosome = malloc(n * sizeof(int));

        for (int j = 0; j < n; j++)
            pop[i].chromosome[j] = rand() % 2;

        evaluate(&pop[i], values, n);
    }
}

/* =========================
   Torneio
========================= */
static int tournament(Individual pop[]) {

    int a = rand() % POP_SIZE;
    int b = rand() % POP_SIZE;

    return (pop[a].fitness < pop[b].fitness) ? a : b;
}

/* =========================
   Crossover
========================= */
static void crossover(int p1[], int p2[], int child[], int n) {

    int cut = rand() % n;

    for (int i = 0; i < n; i++) {
        child[i] = (i < cut) ? p1[i] : p2[i];
    }
}

/* =========================
   Mutação
========================= */
static void mutate(int chrom[], int n) {

    for (int i = 0; i < n; i++) {

        double r = (double)rand() / RAND_MAX;

        if (r < MUTATION_RATE)
            chrom[i] = 1 - chrom[i];
    }
}

/* =========================
   GA principal
========================= */
long long geneticAlgorithm(int values[], int n, int solution[]) {

    Individual pop[POP_SIZE];
    Individual newPop[POP_SIZE];

    initPopulation(pop, values, n);

    Individual best = pop[0];

    for (int g = 0; g < GENERATIONS; g++) {

        for (int i = 0; i < POP_SIZE; i++) {

            int p1 = tournament(pop);
            int p2 = tournament(pop);

            newPop[i].chromosome = malloc(n * sizeof(int));

            crossover(pop[p1].chromosome, pop[p2].chromosome, newPop[i].chromosome, n);

            mutate(newPop[i].chromosome, n);

            evaluate(&newPop[i], values, n);

            if (newPop[i].fitness < best.fitness)
                best = newPop[i];
        }

        for (int i = 0; i < POP_SIZE; i++)
            pop[i] = newPop[i];
    }

    for (int i = 0; i < n; i++)
        solution[i] = best.chromosome[i];

    return best.fitness;
}