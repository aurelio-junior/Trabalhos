#include <stdlib.h>
#include <stdio.h>
#include <math.h>

#include "simulated_annealing.h"
#include "partition_problem.h"

#define INITIAL_TEMPERATURE 1000.0
#define COOLING_RATE 0.995
#define MIN_TEMPERATURE 0.001
#define MAX_ITERATIONS 1000

/* =========================================================
   Gera vizinho
========================================================= */
void generateNeighbor(int current[], int neighbor[], int n) {

    for (int i = 0; i < n; i++) {
        neighbor[i] = current[i];
    }

    int pos = rand() % n;

    neighbor[pos] = 1 - neighbor[pos];
}

/* =========================================================
   Simulated Annealing
========================================================= */
long long simulatedAnnealing(int values[], int n, int solution[]) {

    double temperature = INITIAL_TEMPERATURE;

    int *current = malloc(n * sizeof(int));
    int *best = malloc(n * sizeof(int));
    int *neighbor = malloc(n * sizeof(int));

    if (current == NULL || best == NULL || neighbor == NULL) {
        free(current);
        free(best);
        free(neighbor);
        return 1e9;
    }

    generateRandomSolution(current, n);

    long long currentFitness = evaluateFitness(values, current, n);
    long long bestFitness = currentFitness;

    for (int i = 0; i < n; i++) {
        best[i] = current[i];
    }

    while (temperature > MIN_TEMPERATURE) {

        for (int i = 0; i < MAX_ITERATIONS; i++) {

            generateNeighbor(current, neighbor, n);

            long long neighborFitness = evaluateFitness(values, neighbor, n);
            long long delta = neighborFitness - currentFitness;

            if (delta < 0) {

                for (int k = 0; k < n; k++)
                    current[k] = neighbor[k];

                currentFitness = neighborFitness;

            } else {

                double probability = exp(-(double)delta / temperature);
                double randomValue = (double)rand() / RAND_MAX;

                if (randomValue < probability) {

                    for (int k = 0; k < n; k++)
                        current[k] = neighbor[k];

                    currentFitness = neighborFitness;
                }
            }

            if (currentFitness < bestFitness) {

                bestFitness = currentFitness;

                for (int k = 0; k < n; k++)
                    best[k] = current[k];
            }
        }

        temperature *= COOLING_RATE;
    }

    for (int i = 0; i < n; i++)
        solution[i] = best[i];

    free(current);
    free(best);
    free(neighbor);

    return bestFitness;
}